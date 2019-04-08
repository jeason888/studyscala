package javaspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import java.util.Arrays;
import java.util.List;
public class maplianxi {
    public static void main(String[] args) {
      map();
    }
    private static void map(){
        //创建sparkconf
        SparkConf conf=new SparkConf()
                .setAppName("map2")
                .setMaster("local");
        //创建Sparkcontext
        JavaSparkContext sc=new JavaSparkContext(conf);
        //创建集合
        List<Integer> numbers=Arrays.asList(1,2,3,4,5,6);
        JavaRDD<Integer> numbersRDD=sc.parallelize(numbers);
        JavaRDD<Integer> mutilnumbers=numbersRDD.map(new Function <Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1*2;
            }
        });
//        mutilnumbers.foreach(new VoidFunction <Integer>() {
//            @Override
//            public void call(Integer t) throws Exception {
//                System.out.println(t);
//            }
//        });
        //collect 操作有风险，使用需谨慎，通常使用foreach
        List<Integer> numberList=mutilnumbers.collect();
        for(Integer num:numberList){
            System.out.println(num);
        }
     sc.close();
    }
}
