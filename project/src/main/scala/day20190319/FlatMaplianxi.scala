package day20190319

import org.apache.spark.{SparkConf, SparkContext}

object FlatMaplianxi {
  def main(args: Array[String]): Unit = {
    flatMap()
  }
  def flatMap(): Unit ={
    //创建conf
    val conf=new SparkConf()
      .setAppName("flatmap")
      .setMaster("local")
    val sc=new SparkContext(conf);
    val linesArrays=Array("hello you","hello me","hello world")
    val lines=sc.parallelize(linesArrays,1)
    val words=lines.flatMap(line=>line.split(" "))
    words.foreach(word=>println(word))
  }
}
