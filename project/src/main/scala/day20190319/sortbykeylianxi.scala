package day20190319
import org.apache.spark.{SparkConf, SparkContext}

object sortbykeylianxi {
  def main(args: Array[String]): Unit = {
    sortbylian()
  }
  def sortbylian(): Unit ={
    val conf=new SparkConf()
      .setAppName("sortbykey")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val scoreList=Array(Tuple2(43,"leo"),Tuple2(44,"tom"),Tuple2(12,"marry"),
                 Tuple2(42,"jeason"),Tuple2(56,"jim"))
    val scores=sc.parallelize(scoreList,1)
    val sortedScores = scores.sortByKey(false)
    sortedScores.foreach(sortedScores=>println(sortedScores._1+":"+sortedScores._2))
  }
}
