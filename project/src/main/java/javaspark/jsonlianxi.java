package javaspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.sources.In;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
public class jsonlianxi {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("json")
                .setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        SQLContext sqlContext=new SQLContext(sc);
        Dataset studentscoreDF=sqlContext.read().json("e://students.json");
        studentscoreDF.registerTempTable("studentscore");
        Dataset goodstudentDF=sqlContext.sql("select name,score from studentscore where score>=80");
        List<String> goodstudentnames=goodstudentDF.javaRDD().map(new Function<Row,String>() {
            @Override
            public String call(Row v1) throws Exception {
                return v1.getString(0);
            }
        }).collect();
        System.out.println(goodstudentnames);
        //然后针对javardd<string>创建dataframe
        List<String> studentsJsons=new ArrayList <String>();
        studentsJsons.add("{\"name\":\"Leo\",\"age\":18}");
        studentsJsons.add("{\"name\":\"Marry\",\"age\":17}");
        studentsJsons.add("{\"name\":\"Jack\",\"age\":19}");
        JavaRDD<String> studentInfoJSSONSrdd=sc.parallelize(studentsJsons);
        Dataset studentInfoDF=sqlContext.read().json(studentInfoJSSONSrdd);
        studentInfoDF.registerTempTable("student_infos");
        studentInfoDF.show();
        String sql = "select name,age from student_infos where name in (";
        for(int i = 0; i < goodstudentnames.size(); i++) {
            sql += "'" + goodstudentnames.get(i) + "'";
            if(i < goodstudentnames.size() - 1) {
                sql += ",";
            }
        }
        sql += ")";
        Dataset goodStudentInfoDF = sqlContext.sql(sql);
        goodStudentInfoDF.show();
        goodstudentDF.show();
        //然后将两份数据的dataframe,转换为javapairrdd，执行
        JavaPairRDD<String,Tuple2<String,Integer>> goodStudentsRDD=goodstudentDF.javaRDD().mapToPair(new PairFunction<Row,String,Integer>() {
            @Override
            public Tuple2<String,Integer> call(Row row) throws Exception {
                return new Tuple2<String,Integer>(row.getString(0),
                Integer.valueOf(String.valueOf(row.getLong(1))));
            }
        }).join(goodStudentInfoDF.javaRDD().mapToPair(new PairFunction<Row,String,Integer>() {
            @Override
            public Tuple2<String,Integer> call(Row row) throws Exception {
                return new Tuple2 <String, Integer>(row.getString(0),
                        Integer.valueOf(String.valueOf(row.getLong(1))));
            }
        }));
           //然后将封装在rdd中的好学生的全部信息转换为一个row的格式
        JavaRDD<Row> goodStudentRowsrdd=goodStudentsRDD.map(new Function <Tuple2 <String, Tuple2 <String, Integer>>, Row>() {
            @Override
            public Row call(Tuple2 <String, Tuple2 <String, Integer>> tuple) throws Exception {
                return RowFactory.create(tuple._1,tuple._2._1,tuple._2._2);
            }
        });//创建一份元数据，将javardd<Row)转换为Dataframe
        List<StructField> StrunctFields=new ArrayList <StructField>();
        StrunctFields.add(DataTypes.createStructField("name",DataTypes.StringType,true));
        StrunctFields.add(DataTypes.createStructField("score",DataTypes.IntegerType,true));
        StrunctFields.add(DataTypes.createStructField("age",DataTypes.IntegerType,true));
        StructType structType=DataTypes.createStructType(StrunctFields);
        Dataset goodstudentsDF=sqlContext.createDataFrame(goodStudentRowsrdd,structType);
        goodstudentsDF.write().format("json").save("e://good-students");
    }
}
