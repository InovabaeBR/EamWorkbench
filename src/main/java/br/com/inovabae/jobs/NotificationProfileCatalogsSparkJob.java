package br.com.inovabae.jobs;

import br.com.inovabae.model.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

public class NotificationProfileCatalogsSparkJob {
    public static void main(String[] args) {
        //List<NotificationCatalog> collect = getNotificationCatalogs();
//        for (NotificationCatalog nc : collect) {
//            System.out.println(nc.toString());
//        }
    }

    private static void addIfTrue(List<CatalogProfile> catalogs, Boolean condition,
                                  CatalogProfile catalogProfile) {
        if (condition) {
            catalogs.add(catalogProfile);
        }
    }

    protected static SparkSession getSparkSession() {
        SparkConf conf = new SparkConf()
                .setAppName("SapPmSparkJobs")
                .setMaster("local[*]");

        return SparkSession.builder()
                .config(conf)
                .getOrCreate();
    }

    private JavaRDD<NotificationCatalog> getNotificationCatalogsGroupCodesRdd(String path) {
        SparkSession spark = getSparkSession();


        JavaRDD<NotificationCatalog> notifCatalogsRDD = spark.read()
                .option("header", "true")
                .option("encoding", "ISO-8859-1")
                .csv(path)
                .toJavaRDD()
                .map(line -> {
                    String parts[] = line.getString(0).split(";");
                    String notifType = parts[0];
                    String notifName = parts[1];
                    Boolean hasCoding = !parts[2].trim().isEmpty();
                    Boolean hasDamage = !parts[3].trim().isEmpty();
                    Boolean hasCause = !parts[4].trim().isEmpty();
                    Boolean hasTask = !parts[5].trim().isEmpty();
                    Boolean hasActivity = !parts[6].trim().isEmpty();
                    Boolean hasObjectPart = !parts[7].trim().isEmpty();
                    String catalogProfile = parts[8].trim();

                    NotificationCatalog notifCatalog = new NotificationCatalog();
                    notifCatalog.setNotification(new Notification(notifType, notifName));

                    List<CatalogProfile> catalogProfiles = new ArrayList<CatalogProfile>();
                    addIfTrue(catalogProfiles, hasCoding, new CatalogProfile(catalogProfile, CatalogEnum.CODING));
                    addIfTrue(catalogProfiles, hasDamage, new CatalogProfile(catalogProfile, CatalogEnum.DAMAGE));
                    addIfTrue(catalogProfiles, hasCause, new CatalogProfile(catalogProfile, CatalogEnum.CAUSE));
                    addIfTrue(catalogProfiles, hasTask, new CatalogProfile(catalogProfile, CatalogEnum.TASK));
                    addIfTrue(catalogProfiles, hasActivity, new CatalogProfile(catalogProfile, CatalogEnum.ACTIVITY));
                    addIfTrue(catalogProfiles, hasObjectPart, new CatalogProfile(catalogProfile, CatalogEnum.OBJECT_PARTS));
                    notifCatalog.setCatalogProfiles(catalogProfiles);
                    return notifCatalog;
                });

        return notifCatalogsRDD;
    }

    protected List<NotificationCatalog> getNotificationCatalogs() {
        SparkSession spark = getSparkSession();

        JavaRDD<NotificationCatalog> groupCodesRdd = this.
                getNotificationCatalogsGroupCodesRdd("file:///@LSMW//NotificationCatalogHeader.csv");










//        JavaRDD<String> notifCatalogsRDD = spark.read()
//                .option("header", "true")
//                .option("encoding", "ISO-8859-1")
//                .csv("xxx")
//                .toJavaRDD()
//                .map(new Tuple

        Dataset<Row> socket = spark.readStream()
                .format("socket")
                .option("host", "localhost")
                .option("port", "9999")
                .load();


        GroupCodesSparkJob groupCodesSparkJob = new GroupCodesSparkJob();
        JavaRDD<GroupCode> groupCodeJavaRDD = groupCodesSparkJob.getGroupCodeJavaRDD(spark);


        groupCodeJavaRDD.filter(new Function<GroupCode, Boolean>() {
            @Override
            public Boolean call(GroupCode groupCode) throws Exception {
                return null;
            }
        });




        groupCodesRdd.filter(new Function<NotificationCatalog, Boolean>() {
            @Override
            public Boolean call(NotificationCatalog notificationCatalog) throws Exception {
                return null;
            }
        });

//        JavaRDD<Integer> result = squared.filter(
//                new Function<Integer, Boolean>() { public Boolean call(Integer x) { return x != 1; }});


        JavaRDD<NotificationCatalog> notifCatalogsRDD = spark.read()
                .option("header", "true")
                .option("encoding", "ISO-8859-1")
                .csv("file:///@LSMW//NotificationCatalog.csv")
                .toJavaRDD()
                .map(line -> {
                    String parts[] = line.getString(0).split(";");
                    String notifType = parts[0];
                    String notifName = parts[1];
                    Boolean hasCoding = !parts[2].trim().isEmpty();
                    Boolean hasDamage = !parts[3].trim().isEmpty();
                    Boolean hasCause = !parts[4].trim().isEmpty();
                    Boolean hasTask = !parts[5].trim().isEmpty();
                    Boolean hasActivity = !parts[6].trim().isEmpty();
                    Boolean hasObjectPart = !parts[7].trim().isEmpty();
                    String catalogProfile = parts[8].trim();

                    NotificationCatalog notifCatalog = new NotificationCatalog();
                    notifCatalog.setNotification(new Notification(notifType, notifName));

                    List<CatalogProfile> catalogProfiles = new ArrayList<CatalogProfile>();
                    addIfTrue(catalogProfiles, hasCoding, new CatalogProfile(catalogProfile, CatalogEnum.CODING));
                    addIfTrue(catalogProfiles, hasDamage, new CatalogProfile(catalogProfile, CatalogEnum.DAMAGE));
                    addIfTrue(catalogProfiles, hasCause, new CatalogProfile(catalogProfile, CatalogEnum.CAUSE));
                    addIfTrue(catalogProfiles, hasTask, new CatalogProfile(catalogProfile, CatalogEnum.TASK));
                    addIfTrue(catalogProfiles, hasActivity, new CatalogProfile(catalogProfile, CatalogEnum.ACTIVITY));
                    addIfTrue(catalogProfiles, hasObjectPart, new CatalogProfile(catalogProfile, CatalogEnum.OBJECT_PARTS));
                    notifCatalog.setCatalogProfiles(catalogProfiles);
                    return notifCatalog;
                });

        return notifCatalogsRDD.collect();
    }
}
