package scalasparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object parquetLoad {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setMaster("local")
      .setAppName("parque")
    val sc=new SparkContext(conf)
    val sqlContext=new SQLContext(sc)
    val parqueDF=sqlContext.read.load("e://users.parquet")
    parqueDF.registerTempTable("user")
    val namesDF=sqlContext.sql("select name from user")
    val name=namesDF.rdd.map(row=>"name:"+row(0)).collect().foreach(name=>println(name))
  }
}
