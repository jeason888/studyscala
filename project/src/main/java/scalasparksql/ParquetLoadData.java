package scalasparksql;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.Dataset;
import javax.xml.crypto.Data;
import java.util.List;

public class ParquetLoadData {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("Parque")
                .setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        SQLContext sqlContext=new SQLContext(sc);
        Dataset parqueDF=sqlContext.read().parquet("e://users.parquet");
        parqueDF.registerTempTable("user");
        Dataset usenamesDF=sqlContext.sql("select name from user");
        List<String> usernames=usenamesDF.javaRDD().map(new Function<Row,String>(){
            @Override
            public String call(Row row) throws Exception {
                return "Name:"+row.toString();
            }
        }).collect();
         for(String userName:usernames){
             System.out.printf(userName);
         }
    }
}
