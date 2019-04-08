package day20190319
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object LineCount {
    def main(args:Array[String]){
      val conf=new SparkConf()
        .setAppName("LineCount")
        .setMaster("local")
      val sc=new SparkContext(conf);
      val lines=sc.textFile("E://spark.txt",1)
      val pairs=lines.map{line=>(line,1)}
      val lineCounts=pairs.reduceByKey{_+_}.foreach(lineCount=>println(lineCount+" appears "+lineCount._2))
    }
}

