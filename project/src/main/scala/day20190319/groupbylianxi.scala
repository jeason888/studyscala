package day20190319

import org.apache.spark.{SparkConf, SparkContext}

object groupbylianxi {
  def main(args: Array[String]): Unit = {
    groupByKey()
  }
  def groupByKey(): Unit ={
    val conf=new SparkConf()
      .setAppName("groupbykey")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val scorelist=Array(Tuple2("class1",80),Tuple2("class2",90),
      Tuple2("class2",80),Tuple2("class1",69))
    val scores=sc.parallelize(scorelist,1)
    val groupbyScores=scores.groupByKey()
    groupbyScores.foreach(
      score=>{println(score._1);
              score._2.foreach{single=>println(single)}
             println("========")
    }
    )
  }
}
