package br.com.gesc.jobs;

import br.com.gesc.model.GroupCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SparkDatasetsJob extends AbstractSparkSessionJob {
    private static Logger log = LoggerFactory.getLogger(SparkDatasetsJob.class);
    private Dataset<Row> groupCodeDataset;

    public static void main(String[] args) {
        SparkDatasetsJob job = new SparkDatasetsJob();
        JobsUtilz utilz = new JobsUtilz();

        Dataset<Row> notifTypeDs = job.openDataset("1-NotaPerfilCatalogo.csv");
        Dataset<Row> catalogProfileGroupCodeDs = job.openDataset("2-PerfilCatalogoGrupoCode.csv");
        Dataset<Row> codesDs = job.openDataset("4-Codes.csv");


        JavaPairRDD<String, Tuple8> notifTypeRdd = notifTypeDs.toJavaRDD()
                .mapToPair(
                        new PairFunction<Row, String, Tuple8>() {
                            @Override
                            public Tuple2<String, Tuple8> call(Row row) throws Exception {
                                String notifType = row.getString(0);
                                String notifDesc = row.getString(1);
                                String catalogProfile = row.getString(9);

                                return new Tuple2(notifType, new Tuple8(notifDesc, row.getString(2), row.getString(3),
                                        row.getString(4), row.getString(5), row.getString(6), row.getString(7),
                                        catalogProfile));
                            }
                        }
                );

//        JavaRDD<Tuple9> notifTypeRdd = notifTypeDs.toJavaRDD()
//                .map(new Function<Row, Tuple9>() {
//                    @Override
//                    public Tuple9 call(Row row) throws Exception {
//                        String notifType = row.getString(0);
//                        String notifDesc = row.getString(1);
//                        String catalogProfile = row.getString(9);
//
//                        return new Tuple9(notifType, notifDesc, row.getString(2), row.getString(3),
//                                row.getString(4), row.getString(5), row.getString(6), row.getString(7),
//                                catalogProfile);
//                    }
//                });

        notifTypeDs.show(10);
        notifTypeRdd.foreach(record ->
        {

            String joined = StringUtils.join(record._1(), " ", record._2());
            

//            String joined = StringUtils.join(record._1(), record._3(), record._4().toString());
//            Map<String, List<String>> notifCatalogs = new HashMap<String, List<String>>();
//
//
//            List<String> catalogs = Arrays.asList(row.getString(2), row.getString(3),
//                    row.getString(4), row.getString(5), row.getString(6), row.getString(7));

//            for (String str : catalogs) {
//                System.out.println(str);
//            }
//
            System.out.println(joined);

        });

        //TODO MONTAR O JOIN ENTRE OS CSV
        final JavaRDD<Tuple3> catalogProfileGroupCodeRdd = catalogProfileGroupCodeDs.toJavaRDD()
                .map(line -> {
                    String parts[] = line.getString(0).split(";");
                    String catalogProfile = parts[0];
                    String catalog = parts[1];
                    String groupCode = parts[2];

                    return new Tuple3(catalogProfile, catalog, groupCode);
                });

        GroupCodesSparkJob grupoCodesJob = new GroupCodesSparkJob();
        JavaRDD<GroupCode> groupCodeJavaRDD = grupoCodesJob.getGroupCodeJavaRDD("//3-GrupoCode.csv");

        JavaRDD<Tuple4> codesRdd = codesDs.toJavaRDD().map(record -> {
            String[] parts = record.getString(0).split(";");
            String catalog = parts[0];
            String groupCode = parts[1];
            String code = parts[2];
            String text = parts[4];

            return new Tuple4(catalog, groupCode, code, text);
        });

//        catalogProfileGroupCodeRdd.filter(record -> groupCodesDs.collectAsList().get(0).get(0).equals(record._1()));

        //TODO Filtrar os grupoCodes com base no catalog e o groupCode em groupCodeDs

        //
//
//        notifTypeDs.toJavaRDD()
//                .mapToPair(
//                        new PairFunction<Row, String[], String[]>() {
//                            @Override
//                            public Tuple2<String[], String[]> call(Row row) throws Exception {
//                                String[] keys = {row.getString(0), row.getString(1)};
//                                String[] values = new String[row.length()];
//                                for (int i = 2; i < row.length(); i++) {
//                                    values[i - 2] = row.getString(i);
//                                }
//                                return new Tuple2(keys, values);
//                            }
//                        }
//                );


    }


}
