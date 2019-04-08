//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.VoidFunction;
//import scala.Tuple2;
//import java.util.List;
//import java.util.Arrays;
//public class joinlianxi {
//    public static void main(String[] args) {
//
//    }
//    private static void join(){
//        SparkConf conf=new SparkConf()
//                .setAppName("join")
//                .setMaster("local");
//        JavaSparkContext sc=new JavaSparkContext(conf);
//        List<Tuple2<Integer,String>> scoreList1= Arrays.asList(
//                new Tuple2<Integer,String>(1,"tom"),
//                new Tuple2<Integer,String>(2,"momo"),
//                new Tuple2<Integer,String>(3,"tentent"),
//                new Tuple2<Integer,String>(4,"didi")
//        );
//        List<Tuple2<Integer,Integer>> scoreList2= Arrays.asList(
//                new Tuple2<Integer,Integer>(1,10),
//                new Tuple2<Integer,Integer>(2,20),
//                new Tuple2<Integer,Integer>(3,30),
//                new Tuple2<Integer,Integer>(4,40)
//        );
//        JavaPairRDD<Integer,String> scorelistrdd1=sc.parallelizePairs(scoreList1);
//        JavaPairRDD<Integer,String> scorelistrdd2=sc.parallelizePairs(scoreList1);
//
//        JavaPairRDD<Integer,Tuple2<Integer,String>>studentScores=scoreList1.join(scoreList2);
//        studentScores.foreach(
//                new VoidFunction <Tuple2 <Integer, Tuple2 <Integer, String>>>() {
//                    @Override
//                    public void call(Tuple2 <Integer, Tuple2 <Integer, String>> t) throws Exception {
//                        System.out.println("student id:"+t._1);
//                        System.out.println("student name:"+t._2._1);
//                        System.out.println("student score:"+t._2._2);
//                        System.out.printf("=======================");
//                    }
//                }
//
//
//        );
//        sc.close();
//
//
//
//
//
//
//
//
//
//
//    }
//
//
//
//
//
//
//
//
//}
