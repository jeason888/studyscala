package scalasparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object GenericalLoadsave {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("gene")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val sqlContext=new SQLContext(sc)
    val userDF=sqlContext.read.load("e://users.parquet")
    userDF.select("name").write.save("e://user.txt")
  }
}
