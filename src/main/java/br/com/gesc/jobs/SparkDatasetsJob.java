package br.com.gesc.jobs;

import br.com.gesc.model.GroupCode;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SparkDatasetsJob extends AbstractSparkSessionJob {
    private static Logger log = LoggerFactory.getLogger(SparkDatasetsJob.class);
    private Dataset<Row> groupCodeDataset;

    public static void main(String[] args) {
        SparkDatasetsJob job = new SparkDatasetsJob();
        JobsUtilz utilz = new JobsUtilz();

        Dataset<Row> notifTypeDs = job.openDataset("1-NotaPerfilCatalogo.csv");
        Dataset<Row> catalogProfileGroupCodeDs = job.openDataset("2-PerfilCatalogoGrupoCode.csv");
        Dataset<Row> codesDs = job.openDataset("4-Codes.csv");

        JavaRDD<Tuple4> notifTypeRdd = notifTypeDs.toJavaRDD()
                .map(record -> {
                    String[] parts = record.getString(0).split(";");
                    String notifType = parts[0];
                    String notifDesc = parts[1];
                    String catalogProfile = parts[9];

                    List<String> catalogs = Arrays.asList(parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
                    return new Tuple4(notifType, notifDesc, catalogs, catalogProfile);
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
