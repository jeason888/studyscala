package scalasparksql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;

public class GenERIC {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("genric")
                .setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        SQLContext sqlContext=new SQLContext(sc);
        Dataset usersDF=sqlContext.read().load("e://users.parquet");
        usersDF.show();
        usersDF.schema();
        usersDF.select("name").write().save("e://wjx.txt");
    }
}
