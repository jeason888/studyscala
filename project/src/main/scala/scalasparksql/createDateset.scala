package scalasparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object createDateset {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("dateset")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val sqlContext=new SQLContext(sc)
    val df=sqlContext.read.json("e://students.json")
    df.show()
    df.printSchema()
    df.select("name").show()
    df.select(df.col("name"),df.col("age").plus(1)).show()
    df.filter(df.col("age").>(18)).show()
    df.groupBy("age").count().show()
  }
}
