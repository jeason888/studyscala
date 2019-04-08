package day20190319

import org.apache.spark.{SparkConf, SparkContext}

object reducelianxi {
  def main(args: Array[String]): Unit = {
    reducelian()
  }
  def reducelian(){
    val conf=new SparkConf()
      .setAppName("reduce")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val list=Array(1,2,3,4,5,6,7,8,9,10)
    val listrdd=sc.parallelize(list,1)
    val sum=listrdd.reduce(_+_)
    println(sum)
  }
}
