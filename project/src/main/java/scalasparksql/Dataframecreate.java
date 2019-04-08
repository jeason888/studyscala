package scalasparksql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.Dataset;

public class Dataframecreate {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("DataFrameCtreate")
                .setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        SQLContext sqlContext=new SQLContext(sc);
        Dataset df=sqlContext.read().json("E://students.json");
        df.show();
        df.printSchema();
        df.select("name").show();
        df.select(df.col("name"),df.col("age").plus(1)).show();
        df.filter(df.col("age").gt(18)).show();
        df.groupBy("age").count().show();
    }
}

