package javaspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.List;
import java.util.Arrays;
public class BroadcastVariable {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("broadcast")
                .setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        final int factor=3;
        final Broadcast<Integer> factorBroadcast=sc.broadcast(factor);
        List<Integer>numberList= Arrays.asList(1,2,3,4,5);
        JavaRDD<Integer> number=sc.parallelize(numberList);
        //让集合中的每个数字，都乘以外部数据中的那个factor
        JavaRDD<Integer> numermap=number.map(new Function <Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                int factor=factorBroadcast.value();
                return v1*factor;
            }
        });
        numermap.foreach(new VoidFunction <Integer>() {
            @Override
            public void call(Integer t) throws Exception {
                System.out.println(t);
            }
        });
        sc.close();
    }
}
