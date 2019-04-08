package javaspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import java.util.List;
import java.util.Arrays;
public class actionlianxi {
    public static void main(String[] args) {
      reduce();
    }
    private static void reduce(){
        SparkConf conf = new SparkConf()
                .setAppName("reduce")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer>number=sc.parallelize(numberList);
        //reduce操作的本质就是，聚合，将多个元素聚合，一个一个元素累加
        int count=number.reduce(new Function2 <Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });
        System.out.println(count);
        sc.close();
    }
}
