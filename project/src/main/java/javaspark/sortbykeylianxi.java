//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.VoidFunction;
//import scala.Tuple2;
//import java.util.Arrays;
//import java.util.List;
//
//public class sortbykeylianxi {
//    public static void main(String[] args) {
//
//    }
//    private static void sortbykey(){
//        SparkConf conf=new SparkConf()
//                .setAppName("sortbykey")
//                .setMaster("local");
//        JavaSparkContext sc=new JavaSparkContext(conf);
//        List<Tuple2<Integer,String>> sortList= Arrays.asList(
//                new Tuple2<Integer,String>(12,"leo"),
//                new Tuple2<Integer,String>(23,"tom"),
//                new Tuple2<Integer,String>(43,"le"),
//                new Tuple2<Integer,String>(54,"lo"),
//                new Tuple2<Integer,String>(45,"javk"),
//                new Tuple2<Integer,String>(3,"marry"),
//                new Tuple2<Integer,String>(43,"jeson"));
//        JavaPairRDD<Integer,String> scoresort=sc.parallelizePairs(sortList);
//        JavaPairRDD<Integer,String> sortScore=scoresort.sortByKey();
//        sortScore.foreach(new VoidFunction <Tuple2 <Integer, String>>() {
//            @Override
//            public void call(Tuple2 <Integer, String> t) throws Exception {
//                System.out.println(t._1+":"+t._2);
//            }
//        });
//        sc.close();
//    }
//}
