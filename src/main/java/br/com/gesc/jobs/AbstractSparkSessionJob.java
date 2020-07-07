package br.com.gesc.jobs;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * Filter with lists
 * https://stackoverflow.com/questions/46860413/filter-javardd-based-on-a-arraylist-of-index-ids
 */
public abstract class AbstractSparkSessionJob {
    public String csvPath = "//resource";

    //TODO SINGLETON
    protected SparkSession getSparkSession() {
        SparkConf conf = new SparkConf()
                .setAppName("EamSparkJobs")
                .setMaster("local[*]");

        return SparkSession.builder()
                .config(conf)
                .getOrCreate();
    }

    protected Dataset<Row> openDataset(String csvFile) {
        SparkSession sparkSession = this.getSparkSession();
        return sparkSession
                .read()
                .option("header", "true")
                .option("encoding", "ISO-8859-1")
                .option("sep", ";")
                .csv(csvFile);
    }



}
