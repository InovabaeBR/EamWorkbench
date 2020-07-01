package br.com.gesc.jobs;

import br.com.gesc.model.CatalogEnum;
import br.com.gesc.model.GroupCode;
import br.com.gesc.model.UserAdmData;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple10;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.shaded.netty4.io.netty.util.internal.logging.InternalLogger;
import org.apache.flink.shaded.netty4.io.netty.util.internal.logging.Log4JLoggerFactory;

import java.util.*;

public class FilterCatalogs {
    static final InternalLogger log = Log4JLoggerFactory.getInstance(FilterCatalogs.class);

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<GroupCode> groupCodesDs = getGroupCodes(args, env);
        env.execute();

        List<GroupCode> groupCodes = groupCodesDs.collect();

        log.info("Group codes list......");
        for (GroupCode gc: groupCodes) {
            log.info(gc.toString());
        }
        log.info("End of Group codes list......");

    }

    private static DataSet<GroupCode> getGroupCodes(String[] args, ExecutionEnvironment env) {
        final String input = getParametersFromParams(args, "INPUT");
        log.info(input);

        DataSet<Tuple10<String, String, String, String,
                String, String, String, String, String, Integer>>
                lines = env.readCsvFile(input)
                .ignoreFirstLine()
                .parseQuotedStrings('"')
                .ignoreInvalidLines()
                .types(String.class, String.class, String.class, String.class,
                        String.class, String.class, String.class, String.class, String.class,
                        Integer.class);


        DataSet<GroupCode> groupCodes = lines.map(new MapFunction<Tuple10<String, String,
                String, String, String, String, String, String, String, Integer>,
                GroupCode>() {
            @Override
            public GroupCode map(Tuple10<String, String,
                    String, String, String, String, String, String, String, Integer> csvLine)
                    throws Exception {
                String catalog = csvLine.f0;
                String codeGroup = csvLine.f1;
                String shortText = csvLine.f2;
                String createdBy = csvLine.f3;
                Date createdOn = DateUtils.parseDate(csvLine.f4, new String[]{"dd/MM/yyyy"});
                String changedBy = csvLine.f5;
                Date changedOn = DateUtils.parseDate(csvLine.f6, new String[]{"dd/MM/yyyy"});
                Boolean isUsed = Boolean.valueOf(csvLine.f7);
                Boolean isInactive = Boolean.valueOf(csvLine.f8);
                int status = Integer.valueOf(csvLine.f9);
                UserAdmData userAdmData = new UserAdmData(createdOn, createdBy,
                        changedBy,changedOn);
                return new GroupCode(CatalogEnum.valueOf(catalog), codeGroup, shortText,
                        userAdmData, isUsed, isInactive, status);
            }
        });
        return groupCodes;
    }

    private static String getParametersFromParams(String[] args, String whichParam) {
        ParameterTool params = ParameterTool.fromArgs(args);
        final String input = params.getRequired("input");
        final String output = params.getRequired("output");
        String param = "";
        switch (whichParam.toUpperCase()) {
            case "INPUT":
                param = input;
                break;
            case "OUTPUT":
                param = output;
                break;
            default:
                System.out.println("Params Error!");
        }
        return param;
    }
}

