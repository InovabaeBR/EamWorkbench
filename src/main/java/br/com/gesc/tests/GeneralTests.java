package br.com.gesc.tests;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;
import scala.collection.Seq;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class GeneralTests {

    public static void main(String[] args) {
        String str = "MC-*";

        for (String s : StringUtils.split(str, "\\*")) {
            System.out.println(s);
        }

        SparkSession sc = getSparkSession();
        JavaRDD<String> ds = sc.read().textFile("/Users/evandroneves/spark.txt")
                .toJavaRDD();

        JavaPairRDD<String, Integer> counts = ds
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);

        for (Tuple2<String, Integer> tuple2 : counts.collect()) {
            StringBuilder builder = new StringBuilder();
            builder.append(tuple2._1).append("-").append(tuple2._2);
            System.out.println(builder.toString());
        }

    }

    private static SparkSession getSparkSession() {
        SparkConf conf = new SparkConf()
                .setAppName("EamSparkJobs")
                .setMaster("local[*]");

        return SparkSession.builder()
                .config(conf)
                .getOrCreate();
    }


}
