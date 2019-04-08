package scalasparksql;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.List;
import java.util.ArrayList;
public class RDD2DataFrameProgrammatically {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("local")
                .setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        SQLContext sqlContext=new SQLContext(sc);
        JavaRDD<String> lines=sc.textFile("e://students.txt");
        JavaRDD<Row> studentRDD=lines.map(new Function <String, Row>() {
            @Override
            public Row call(String line) throws Exception {
                String[] lineSplited=line.split(",");

                return RowFactory.create(Integer.valueOf(lineSplited[0]),lineSplited[1],Integer.valueOf(lineSplited[2]));
            }
        });
        List<StructField> strunctFields=new ArrayList<StructField>();
        strunctFields.add(DataTypes.createStructField("id", DataTypes.IntegerType,true));
        strunctFields.add(DataTypes.createStructField("name", DataTypes.StringType,true));
        strunctFields.add(DataTypes.createStructField("age", DataTypes.IntegerType,true));
        StructType structType=DataTypes.createStructType(strunctFields);
        Dataset studentDF=sqlContext.createDataFrame(studentRDD,structType);
        studentDF.registerTempTable("students");
        Dataset teenageDF=sqlContext.sql("select * from students where age<18");
        teenageDF.show();
        List<Row> rows=teenageDF.javaRDD().collect();
        for(Row row:rows){
            System.out.println(row);
        }
    }
}
