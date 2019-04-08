package javaspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.sources.In;
import scala.Tuple2;

public class LineLengthCount {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("LineCount")
                .setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> lines=sc.textFile("E://spark.txt");
        JavaPairRDD<String,Integer> pairs=lines.mapToPair(new PairFunction<String,String,Integer>() {
            private static  final long serialVersionUID=1L;
            @Override
            public Tuple2<String,Integer>call(String t)throws Exception{
                return new Tuple2 <String, Integer>(t,1);
            }
        });
        JavaPairRDD<String,Integer>lineCounts=pairs.reduceByKey(
                new Function2<Integer,Integer,Integer>(){
                    public static final long serialVersionUID=1L;
                    public Integer call(Integer v1,Integer v2)throws Exception{
                        return v1+v2;
                }
        }
        );
        lineCounts.foreach(new VoidFunction <Tuple2 <String, Integer>>() {
            @Override
            public void call(Tuple2 <String, Integer> t) throws Exception {
                System.out.println(t._1+"appears"+ t._2+"times");
            }
        });

        sc.close();
    }
}
