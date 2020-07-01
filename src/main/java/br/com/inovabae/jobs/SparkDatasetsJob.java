package br.com.inovabae.jobs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;
import scala.Tuple3;

import java.util.Collections;

public class SparkDatasetsJob extends SparkSessionJob {
    private static Logger log = LoggerFactory.getLogger(SparkDatasetsJob.class);
    private Dataset<Row> groupCodeDataset;

    public static void main(String[] args) {
        SparkDatasetsJob job = new SparkDatasetsJob();
        JobsUtilz utilz = new JobsUtilz();
        //TODO LER TODOS OS CSV

        Dataset<Row> notifTypeDs = job.openDataset("file:///@LSMW//TipoDeNota.csv");
        Dataset<Row> catalogProfileGroupCodeDs = job.openDataset("file:///@LSMW//CatalogProfileGroupCode.csv");
        Dataset<Row> groupCodeDs = job.openDataset("file:///@LSMW//GrupoCodes.csv");

        groupCodeDs
                .toJavaRDD()
                .mapToPair(
                        new PairFunction<Row, String[], String[]>() {
                            @Override
                            public Tuple2<String[], String[]> call(Row row) throws Exception {
                                String[] keys = {row.getString(0), row.getString(1)};
                                String[] values = new String[row.length()];
                                for (int i = 2; i < row.length(); i++) {
                                    values[i - 2] = row.getString(i);
                                }
                                return new Tuple2(keys, values);
                            }
                        }
                );

        //TODO MONTAR O JOIN ENTRE OS CSV
        final JavaRDD<Tuple3> catalogProfileGroupCodeTuples = catalogProfileGroupCodeDs.toJavaRDD()
                .map(line -> {
                    String parts[] = line.getString(0).split(";");
                    String catalogProfile = parts[0];
                    String catalog = parts[1];
                    String groupCode = parts[2];

                    return new Tuple3(catalogProfile, catalog, groupCode);
                });

        catalogProfileGroupCodeTuples.filter(record -> groupCodeDs.collectAsList().get(0).get(0).equals(record._1()));

        //TODO Filtrar os grupoCodes com base no catalog e o groupCode em groupCodeDs


    }


}
