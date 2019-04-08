package day20190319
import org.apache.spark.{SparkConf, SparkContext}
object reducebykeylianxi {
  def main(args: Array[String]): Unit = {
    reduceByKey()
  }
  def reduceByKey(){
       val conf=new SparkConf()
          .setAppName("reducebykey")
          .setMaster("local")
       val sc=new SparkContext(conf)
       val scoreList=Array(Tuple2("class1",23),Tuple2("class1",20)
         ,Tuple2("class2",50),Tuple2("class2",90),Tuple2("class2",670))
       val scores=sc.parallelize(scoreList,1)
       val totalscore=scores.reduceByKey(_+_)
       totalscore.foreach(p=>println(p._1+":"+p._2));
  }
}
