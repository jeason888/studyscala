package scalasparksql;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import java.util.List;
import org.apache.spark.sql.SQLContext;
public class RDD2DataSetReflection {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("dataframe")
                .setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        SQLContext sqlContext=new SQLContext(sc);
        JavaRDD<String> lines=sc.textFile("e://students.txt");
        JavaRDD<Student> students= lines.map(new Function <String, Student>() {
            @Override
            public Student call(String line) throws Exception {
                String[] lineSplited=line.split(",");
                Student stu=new Student();
                stu.setId(Integer.valueOf(lineSplited[0].trim()));
                stu.setName(lineSplited[1]);
                stu.setAge(Integer.valueOf(lineSplited[2].trim()));
                return stu;
            }
        });
        Dataset studentDF=sqlContext.createDataFrame(students,Student.class);
        studentDF.registerTempTable("students");

        Dataset teenagerDF=sqlContext.sql("select * from students where age>=18");
        JavaRDD<Row> teenagerRDD=teenagerDF.javaRDD();
        JavaRDD<Student> teenagerStudentRDD=teenagerRDD.map(new Function <Row, Student>() {
            @Override
            public Student call(Row row) throws Exception {
                Student stu=new Student();
                stu.setAge(row.getInt(0));
                stu.setId(row.getInt(1));
                stu.setName(row.getString(2));
                return stu;
            }
        });
        teenagerStudentRDD.foreach(new VoidFunction <Student>() {
            @Override
            public void call(Student student) throws Exception {
                System.out.println(student);
            }
        });
        List<Student> stulist=teenagerStudentRDD.collect();
        for(Student stu:stulist){
            System.out.println(stu.toStrng());
        }
    }

}
