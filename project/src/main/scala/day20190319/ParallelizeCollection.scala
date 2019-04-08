package day20190319

import org.apache.spark.{SparkConf, SparkContext}

object ParallelizeCollection {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("ParallelizeCollecton")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val numbers=Array(1,2,3,4,5,6,7,8,9,10)
    val numberRDD=sc.parallelize(numbers,5)
    val sum=numberRDD.reduce(_+_)
    println(sum)

  }
}
