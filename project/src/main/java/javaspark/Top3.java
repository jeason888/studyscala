package javaspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;
import java.util.List;
public class Top3 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("TOP3")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("E://top.txt");
        JavaPairRDD<Integer,String>pairs=lines.mapToPair(new PairFunction <String, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(String s) throws Exception {
                return new Tuple2 <Integer, String>(Integer.valueOf(s),s);
            }
        });
        JavaPairRDD<Integer,String> sortedPairs=pairs.sortByKey(false);
        JavaRDD<Integer>soredNumbers=sortedPairs.map(new Function <Tuple2 <Integer, String>, Integer>() {
            @Override
            public Integer call(Tuple2 <Integer, String> s) throws Exception {
                return s._1;
            }
        });
        List<Integer> sortnumberList=soredNumbers.take(3);
        for(Integer num:sortnumberList){
            System.out.println(num);
        };
        sc.close();
    }
}
