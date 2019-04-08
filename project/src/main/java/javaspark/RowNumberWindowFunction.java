//package javaspark;
//import org.apache.hadoop.hive.conf.HiveConf;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.hive.HiveContext;
//public class RowNumberWindowFunction {
//    public static void main(String[] args) {
//        SparkConf conf=new SparkConf()
//                .setAppName("row_number")
//                .setMaster("local");
//        JavaSparkContext sc=new JavaSparkContext(conf);
//        HiveContext hivecontext=new HiveContext(sc.sc());
//        hivecontext.sql("DROP TABLE IF EXISTS sales");
//        hivecontext.sql("CREATE TABLE IF NOT EXISTS sales("
//                +"product STRING,"
//                +"category STRING,"
//                +"revenue BIGINT)");
//        hivecontext.sql("LOAD DATA "
//        +" LOCAL INPATH 'E://sales.txt'"
//        +"INTO TABLE sales");
//        Dataset top3SalesDF=hivecontext.sql(""
//                +"select product，category,reverue"
//                        +"FROM("
//                +"SELECT"
//                        +"product,"
//                        +"category"
//                        +"revenue,"
//                        +"row_numer()over(partition by category order by revenue desc) rank"
//                        +"from sales"
//                        +")tmp"
//                +"where rank<=3");
//        hivecontext.sql("DROP TABLE IF EXISTS top3_sales");
//        sc.close();
//    }
//}
