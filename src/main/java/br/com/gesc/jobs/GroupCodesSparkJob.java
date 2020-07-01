package br.com.gesc.jobs;

import br.com.gesc.model.CatalogEnum;
import br.com.gesc.model.GroupCode;
import br.com.gesc.model.UserAdmData;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.spark.api.java.JavaRDD;

import org.apache.spark.sql.SparkSession;

import java.util.Date;

public class GroupCodesSparkJob {
//    public static void main(String[] args) {
//        SparkConf conf = new SparkConf()
//                .setAppName("CatalogSparkJob")
//                .setMaster("local[*]");
//
//        SparkSession spark = SparkSession.builder()
//                .config(conf)
//                .getOrCreate();
//
//        JavaRDD<GroupCode> groupCodesRDD = getGroupCodeJavaRDD(spark);
//
//        List<GroupCode> collect = groupCodesRDD.collect();
//        for (GroupCode gc : collect) {
//            System.out.println(gc.toString());
//        }
//    }

    protected JavaRDD<GroupCode> getGroupCodeJavaRDD(SparkSession spark) {
        return spark.read()
                    .option("header", "true")
                    .option("encoding", "ISO-8859-1")
                    .csv("file:///@LSMW//GrupoCodes.csv")
                    .toJavaRDD()
                    .map(line -> {
                        String parts[] = line.getString(0).split(";");
                        String catalog = parts[0];
                        String codeGroup = parts[1];
                        String shortText = parts[2];
                        String createdBy = parts[3];
                        Date createdOn = DateUtils.parseDate(parts[4], new String[]{"dd/MM/yyyy"});
                        String changedBy = parts[5];

                        Date changedOn = !parts[6].isEmpty() ? DateUtils.parseDate(parts[6],
                                new String[]{"dd/MM/yyyy"}) : null;

                        Boolean isUsed = parts[7].equalsIgnoreCase("X");
                        Boolean isInactive = parts[8].equalsIgnoreCase("X");
                        int status = Integer.valueOf(parts[9]);
                        UserAdmData userAdmData = new UserAdmData(createdOn, createdBy,
                                changedBy, changedOn);

                        return new GroupCode(CatalogEnum.fromCatalog(catalog), codeGroup, shortText,
                                userAdmData, isUsed, isInactive, status);
                    });
    }

}
