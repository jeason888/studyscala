package scalasparksql

import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType,StructField,StringType,IntegerType}
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object RDDDataFrameProgrammatically {
  def main(args: Array[String]): Unit = {
      val conf=new SparkConf()
        .setAppName("program")
        .setMaster("local")
      val sc=new SparkContext(conf)
      val sqlContext=new SQLContext(sc)
      val studentsRDD=sc.textFile("e://students.txt",1)
        .map{line=>Row(line.split(",")(0).toInt,line.split(",")(1),line.split(",")(2).toInt)}
    val structType = StructType(Array(
      StructField("id", IntegerType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true)))

    val structDF=sqlContext.createDataFrame(studentsRDD,structType)
      structDF.registerTempTable("students")
      val teenagerDF=sqlContext.sql("select * from students where age<=19")
      val teenagerRDD=teenagerDF.collect().foreach(row=>println(row))
      studentsRDD.foreach(p=>println(p))
  }
}
