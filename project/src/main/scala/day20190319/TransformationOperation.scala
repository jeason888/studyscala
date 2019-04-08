package day20190319
import org.apache.spark.{SparkConf, SparkContext}

object TransformationOperation {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("transformationOperation")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val numbers=Array(1,2,3,4,5)
    val numberRDD=sc.parallelize(numbers)
    val mutil=numberRDD.map(line=>line*2)
    mutil.foreach(mutil=>println(mutil))
  }
}
