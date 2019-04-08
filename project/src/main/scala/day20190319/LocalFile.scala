package day20190319
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object LocalFile {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
    .setAppName("LocalFile").setMaster("local")
    val sc=new SparkContext(conf)
    val lines=sc.textFile("E://people.txt")
    val count=lines.map{line=>line.length()}.reduce(_+_)
    println("file count is "+count)
  }
}
