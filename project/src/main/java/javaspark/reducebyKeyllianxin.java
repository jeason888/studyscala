//import org.apache.spark.SparkConf;
//import org.apache.spark.SparkContext;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.Function2;
//import org.apache.spark.api.java.function.VoidFunction;
//import org.apache.spark.sql.sources.In;
//import org.codehaus.janino.Java;
//import scala.Tuple2;
//import scala.reflect.internal.util.SourceFile;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Arrays;
//
//public class reducelianxi {
//    public static void main(String[] args) {
//        groupby();
//    }
//    private static void groupby() {
//        // 创建SparkConf
//        SparkConf conf = new SparkConf()
//                .setAppName("groupByKey")
//                .setMaster("local");
//        // 创建JavaSparkContext
//        JavaSparkContext sc = new JavaSparkContext(conf);
//
//        // 模拟集合
//        List<Tuple2<String, Integer>> scoreList = Arrays.asList(
//                new Tuple2<String, Integer>("class1", 80),
//                new Tuple2<String, Integer>("class2", 75),
//                new Tuple2<String, Integer>("class1", 90),
//                new Tuple2<String, Integer>("class2", 65));
//
//        // 并行化集合，创建JavaPairRDD
//        JavaPairRDD<String,Integer> scores=sc.parallelizePairs(scoreList);
//
//        // 针对scores RDD，执行groupByKey算子，对每个班级的成绩进行分组
//        // groupByKey算子，返回的还是JavaPairRDD
//        // 但是，JavaPairRDD的第一个泛型类型不变，第二个泛型类型变成Iterable这种集合类型
//        // 也就是说，按照了key进行分组，那么每个key可能都会有多个value，此时多个value聚合成了Iterable
//        // 那么接下来，我们是不是就可以通过groupedScores这种JavaPairRDD，很方便地处理某个分组内的数据
//        JavaPairRDD<String,Integer> totalscores = scores.reduceByKey(new Function2 <Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1+v2;
//            }
//        });
//
//        // 打印groupedScores RDD
//        totalscores.foreach(new VoidFunction <Tuple2 <String, Integer>>() {
//            @Override
//            public void call(Tuple2 <String, Integer>t) throws Exception {
//                System.out.println(t._1+":"+t._2);
//            }
//        });
//        // 关闭JavaSparkContext
//        sc.close();
//}}
