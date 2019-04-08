package day20190319
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object wordcount {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("WordCount")
      .setMaster("local");
    val sc=new SparkContext(conf)
    val lines=sc.textFile("C://Users/wangjianxin/Desktop/wordcount.txt");
    val words=lines.flatMap{line=>line.split(" ")}
    val pairs=words.map{word=>(word,1)}
    val wordCounts=pairs.reduceByKey{_+_}
    val sum=wordCounts.collect()
    //val count=wordCounts.count()
    wordCounts.saveAsTextFile("E://1.txt")
    for(num<-sum){
      println(num)
    }
   // wordCounts.foreach(wordCount=>println(wordCount._1+" appeared "+wordCount._2+" time."))
  }
}
