package day20190319

import org.apache.spark.{SparkConf, SparkContext}

object joinlianxi {
  def main(args: Array[String]): Unit = {
    //join()
    cogroup()
  }
  def join(){
    val conf = new SparkConf()
      .setAppName("join")
      .setMaster("local")
    val sc =new  SparkContext(conf)
    val studentList =Array(Tuple2(1, "tom"), Tuple2(2, "jerry"), Tuple2(3, "marry"))
    val scoreList =Array(Tuple2(1, 12), Tuple2(2, 43), Tuple2(3, 54))
    val students = sc.parallelize(studentList)
    val scores = sc.parallelize(scoreList)
    val studentscores = students.join(scores)
    studentscores.foreach(studentscore =>{
      println("学生id：" + studentscore._1)
      println("学生名字：" + studentscore._2._1)
      println("学生分数：" + studentscore._2._2)})
      println("==================================")
  }
  def cogroup(){
    val conf = new SparkConf()
      .setAppName("cogroup")
      .setMaster("local")
    val sc =new  SparkContext(conf)
    val studentList =Array(Tuple2(1, "tom"), Tuple2(1, "jerry"), Tuple2(3, "marry"))
    val scoreList =Array(Tuple2(1, 12), Tuple2(2, 43), Tuple2(3, 54),Tuple2(1, 34),Tuple2(1, 54))
    val students = sc.parallelize(studentList)
    val count=students.countByKey()
    println(count)
    count.map(x=>println(x))
    val scores = sc.parallelize(scoreList)


    val studentscores = students.cogroup(scores)
    studentscores.foreach(studentscore =>{
      println("学生id：" + studentscore._1)
      println("学生名字：" + studentscore._2._1)
      println("学生分数：" + studentscore._2._2)})
    println("==================================")
  }
}
