package javaspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class TransformationOpeation {
    public static void main(String[] args) {
    map();
    }
    //map 算子案例，将集合中的每一个元素都乘以2
    private static void map(){
        //创建sparkconf
        SparkConf conf=new SparkConf()
                .setAppName("map")
                .setMaster("local");
        //创建javasparkcontext
        JavaSparkContext sc=new JavaSparkContext(conf);
        //构建集合
        List<Integer> numbers=Arrays.asList(1,2,3,4,5);
        //并行化集合，创建初始RDD
        //map算子是对任何类型的rdd，java都可以调用的
        //在java中,map算子接受参数是function对象
        //创建的Function对象，一定会让你设置第二个泛型参数，就是返回的新元素的类型
        //同时call（）方法返回的类型，也必须与第二个泛型类型同步
        //在call()方法内部，就可以对原RDD中的每一个元素进行各种处理和计算，并返回一个新的元素
        JavaRDD<Integer> numberRDD=sc.parallelize(numbers);
        JavaRDD<Integer> multipleNumberRDD=numberRDD.map(new Function <Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1*2;
            }
        });
        //打印新的RDD
        multipleNumberRDD.foreach(new VoidFunction <Integer>() {
            @Override
            public void call(Integer t) throws Exception {
                System.out.println(t);
            }
        });
        //关闭JavaSparkContext
        sc.close();

    }
}
