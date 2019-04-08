package javaspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SQLContext;

import javax.xml.soap.SAAJResult;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 每日top3热点搜索统计案例
* */
public class TOP3UV {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("top3daily")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        //伪造数据，查询条件，一般在mysql中读取
        Map <String, List <String>> queryParamMap = new HashMap <String, List <String>>();
        queryParamMap.put("city", Arrays.asList("beijing"));
        queryParamMap.put("platform", Arrays.asList("android"));
        queryParamMap.put("version", Arrays.asList("1.0", "1.2", "1.5", "2.0"));

        final Broadcast<Map<String,List<String>>> queryParamMapBroadcast=sc.broadcast(queryParamMap);
        JavaRDD <String> rawRDD = sc.textFile("e://keywords.txt");
        JavaRDD<String> filterRDD=rawRDD.filter(new Function <String, Boolean>() {
            @Override
            public Boolean call(String log) throws Exception {
                 String[] logsplited=log.split("\t");
                String city=logsplited[3];
                String platform=logsplited[4];
                String version=logsplited[5];
                Map<String,List<String>> queryParamMap=queryParamMapBroadcast.value();
                List<String>cities=queryParamMap.get("city");
                if(cities.size()>0&&!cities.contains(city)){
                    return false;
                }
                if(cities.size()>0&&!cities.contains(platform)){
                    return false;
                }
                if(cities.size()>0&&!cities.contains(version)){
                    return false;
                }
                return true;
            }
        });
        SQLContext sqlContext = new SQLContext(sc);


        sc.close();
    }
}
