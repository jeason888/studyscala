package javaspark;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;

public class flatmaplianxi{
    public static void main(String[] args) {
        flatMap();
    }
    //map 算子案例，将集合中的每一个元素都乘以2
    private static void flatMap() {
        // 创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("flatMap")
                .setMaster("local");
        // 创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构造集合
        List<String> lineList = Arrays.asList("hello you", "hello me", "hello world");

        // 并行化集合，创建RDD
        JavaRDD<String> lines = sc.parallelize(lineList);

        // 对RDD执行flatMap算子，将每一行文本，拆分为多个单词
        // flatMap算子，在java中，接收的参数是FlatMapFunction
        // 我们需要自己定义FlatMapFunction的第二个泛型类型，即，代表了返回的新元素的类型
        // call()方法，返回的类型，不是U，而是Iterable<U>，这里的U也与第二个泛型类型相同
        // flatMap其实就是，接收原始RDD中的每个元素，并进行各种逻辑的计算和处理，返回可以返回多个元素
        // 多个元素，即封装在Iterable集合中，可以使用ArrayList等集合
        // 新的RDD中，即封装了所有的新元素；也就是说，新的RDD的大小一定是 >= 原始RDD的大小
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction <String, String>() {
            @Override
            public Iterator <String> call(String s) throws Exception {
                return (Iterator <String>) Arrays.asList(s.split(" "));
            }
        });
        // 打印新的RDD
        words.foreach(new VoidFunction<String>() {
            private static final long serialVersionUID = 1L;
            @Override
            public void call(String t) throws Exception {
                System.out.println(t);
            }
        });

        // 关闭JavaSparkContext
        sc.close();
    }}
