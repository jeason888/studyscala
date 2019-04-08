//import io.netty.handler.codec.http.HttpContentEncoder.Result;
//
//import java.awt.image.RescaleOp;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//
//import scala.Tuple2;
//
///**
// * Spark二次排序的具体实现步骤：
// * 第一步: 自定义key 实现scala.math.Ordered接口，和Serializeable接口
// * 第二步：将要进行二次排序的数据加载，按照<key，value>格式的RDD
// * 第三步：使用sortByKey 基于自定义的key进行二次排序
// * 第四步：去掉排序的key,只保留排序的结果
// * @author 12285
// *
// */
//public class SecordSortTest {
//    public static void main(String[] args) {
//        SparkConf conf = new SparkConf().setMaster("local").setAppName("WordCount");
//        //内部实际调用的SparkContext
//        JavaSparkContext jsc = new JavaSparkContext(conf);
//        //读取文件，将每行数据转换为
//        JavaRDD<String> lines = jsc.textFile("C:\\Users\\12285\\Desktop\\test");//hadoopRDD
//        //第二步：将要进行二次排序的数据加载，按照<key，value>格式的RDD
//        JavaPairRDD<MyKey, String> mykeyPairs = lines.mapToPair(new PairFunction<String, MyKey, String>() {
//
//            private static final long serialVersionUID = 1L;
//
//            public Tuple2<MyKey, String> call(String line) throws Exception {
//                int firstKey = Integer.valueOf(line.split(" ")[0]);
//                MyKey mykey = new MyKey(firstKey, secondKey);
//                return new Tuple2<MyKey, String>(mykey, line);
//            }
//        });
//        //第三步：使用sortByKey 基于自定义的key进行二次排序
//        JavaPairRDD<MyKey, String> sortPairs = mykeyPairs.sortByKey();
//
//        //第四步：去掉排序的key,只保留排序的结果
//
//        JavaRDD<String> result = sortPairs.map(new Function<Tuple2<MyKey,String>, String>() {
//            private static final long serialVersionUID = 1L;
//
//            public String call(Tuple2<MyKey, String> tuple) throws Exception {
//                return tuple._2;//line
//            }
//        });
//        //打印排序好的结果
//        result.foreach(new VoidFunction<String>() {
//
//            private static final long serialVersionUID = 1L;
//
//            public void call(String line) throws Exception {
//                System.out.println(line);
//            }
//        });
//
//
//
//
//    }
//}
