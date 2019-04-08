package scalasparksql
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
object RDD2dataset extends App{
      val conf=new SparkConf()
        .setAppName("rdd")
        .setMaster("local")
      val sc=new SparkContext(conf)
      val sqlContext=new SQLContext(sc)
      import sqlContext.implicits._
      case class Student(id:Int,name:String,age:Int)
      val lines=sc.textFile("e://students.txt",1)
      val studentDF=lines.map{line=>line.split(",")}.map{arr=>Student(arr(0).trim.toInt,arr(1),arr(2).trim.toInt)}.toDF()
      studentDF.registerTempTable("students")
      val teenagerDF=sqlContext.sql("select * from students where age>16")
      teenagerDF.show()
      val teenagerRDD=teenagerDF.rdd
      teenagerRDD.foreach(p=>println(p))
      teenagerRDD.map{row=>Student(row(0).toString.toInt,row(1).toString,row(2).toString.toInt)}
        .collect()
        .foreach{stu=>println(stu.id+":"+stu.name+":"+stu.age)}
     teenagerRDD.map{row=>Student(row.getAs[Int]("id"),row.getAs[String]("name"),row.getAs[Int]("age"))}
    .collect()
       .foreach{stu=>println(stu.id+":"+stu.name+":"+stu.age)}
     teenagerRDD.map{row=>val map=row.getValuesMap[Any](Array("id","name","age"));
     Student(map("id").toString.toInt,map("name").toString,map("age").toString.toInt)
     }.collect().foreach{stu=>println(stu.id+":"+stu.name+":"+stu.age)
}}
