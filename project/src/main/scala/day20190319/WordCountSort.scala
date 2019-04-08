package day20190319

import org.apache.spark.{SparkConf, SparkContext}

object WordCountSort {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("wordcounts                                                                                                                                   ````    ort")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val lines=sc.textFile("E://people.txt")
    val words=lines.flatMap(line=>line.split(" "))
    val paris=words.map(word=>(word,1))
    val wordCounts=paris.reduceByKey(_+_)
    val countWords=wordCounts.map(wordcount=>(wordcount._2,wordcount._1))
    val countWordsort=countWords.sortByKey(false)
    val wordCountSorts=countWordsort.map(countword=>(countword._2,countword._1))
    wordCountSorts.foreach(p=>println(p._1+" appears "+p._2+" times"))
  }
}
