//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.FlatMapFunction;
//import org.apache.spark.api.java.function.Function2;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//import scala.Tuple2;
//import java.sql.SQLOutput;
//import java.lang.Iterable;
//import java.util.Arrays;
//public class Wordcountsort {
//    public static void main(String[] args) {
//
//    }
//    private static void sort(){
//        SparkConf conf=new SparkConf()
//                .setAppName("wordcountsort")
//                .setMaster("local");
//        JavaSparkContext sc=new JavaSparkContext(conf);
//        JavaRDD<String> lines =sc.textFile("E://1.txt");
//        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
//            @Override
//            public Iterable<String> call(String s) throws Exception {
//                return Arrays.asList(s.split(" "));
//            }
//        });
//        JavaPairRDD<String,Integer> pairs=words.mapToPair(new PairFunction <String, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(String s) throws Exception {
//                return new Tuple2 <String, Integer>(s,1);
//            }
//        });
//        final JavaPairRDD<String,Integer> wordcount=pairs.reduceByKey(
//                new Function2<Integer, Integer, Integer>() {
//                    @Override
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1 + v2;
//            }
//        });
//        //
//        JavaPairRDD<Integer,String>countwords=wordcount.mapToPair(new PairFunction <Tuple2 <String, Integer>, Integer, String>() {
//            @Override
//            public Tuple2 <Integer, String> call(Tuple2 <String, Integer> t) throws Exception {
//                return new Tuple2<Integer,String>(t._2,t._1);
//            }
//        });
//        //安装key进行排序
//        JavaPairRDD<Integer,String>countwordssort=countwords.sortByKey(false);
//        JavaPairRDD<String,Integer>wordcountsort=countwordssort.mapToPair(
//                new PairFunction <Tuple2 <Integer, String>, String, Integer>() {
//                    @Override
//                    public Tuple2 <String, Integer> call(Tuple2 <Integer, String>t) throws Exception {
//                        return new Tuple2<String,Integer>(t._2,t._1);
//                    }});
//        wordcountsort.foreach(new VoidFunction <Tuple2 <String, Integer>>() {
//            @Override
//            public void call(Tuple2 <String, Integer> t) throws Exception {
//                System.out.println(t._1+"appears"+t._2+"times");
//            }
//        });
//        sc.close();
//    }
//}
