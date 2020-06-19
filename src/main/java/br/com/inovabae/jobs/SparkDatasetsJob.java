package br.com.inovabae.jobs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

public class SparkDatasetsJob {
    private static Logger log = LoggerFactory.getLogger(SparkDatasetsJob.class);
    private Dataset<Row> groupCodeDataset;

    public static void main(String[] args) {
        SparkDatasetsJob job = new SparkDatasetsJob();
        //TODO LER TODOS OS CSV
        Dataset<Row> groupCodeDataset = job.openDataset("file:///@LSMW//GrupoCodes.csv");

        //TODO MONTAR O JOIN ENTRE OS CSV
        groupCodeDataset
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
                ).countByKey()
                .forEach((key, value) -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append(key);
                    builder.append("|").append(value);
                    log.info(builder.toString());
                });
    }

    //TODO SINGLETON
    private SparkSession getSparkSession() {
        SparkConf conf = new SparkConf()
                .setAppName("EamSparkJobs")
                .setMaster("local[*]");

        return SparkSession.builder()
                .config(conf)
                .getOrCreate();
    }

    private Dataset<Row> openDataset(String csvFile) {
        SparkSession sparkSession = this.getSparkSession();
        return sparkSession
                .read()
                .option("header", "true")
                .option("encoding", "ISO-8859-1")
                .option("sep", ";")
                .csv(csvFile);
    }
}
