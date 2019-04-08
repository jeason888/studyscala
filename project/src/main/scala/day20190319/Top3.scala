package day20190319
import org.apache.spark.{SparkConf, SparkContext}
object Top3 {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("top3")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val lines=sc.textFile("E://top.txt",1)
    val pairs=lines.map{line=>(line.toInt,line)}

    val sortpairs=pairs.sortByKey(false)
    val sortNumber=sortpairs.map(s=>s._1)
    val top=sortNumber.take(3)
    for(num<-top){
       println(num)
    }
  }
}
