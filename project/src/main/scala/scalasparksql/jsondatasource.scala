package scalasparksql
package cn.spark.study.sql
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.LongType
object jsondatasource {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("JSONDataSource")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 创建学生成绩DataFrame
    val studentScoresDF = sqlContext.read.json("e://students.json")

    // 查询出分数大于80分的学生成绩信息，以及学生姓名
    studentScoresDF.registerTempTable("student_scores")
    val goodStudentScoresDF = sqlContext.sql("select name,score from student_scores where score>=80")
    val goodStudentNames = goodStudentScoresDF.rdd.map { row => row(0) }.collect()

    // 创建学生基本信息DataFrame
    val studentInfoJSONs = Array("{\"name\":\"Leo\", \"age\":18}", "{\"name\":\"Marry\", \"age\":17}", "{\"name\":\"Jack\", \"age\":19}")
    val studentInfoJSONsRDD = sc.parallelize(studentInfoJSONs, 3);
    val studentInfosDF = sqlContext.read.json(studentInfoJSONsRDD)

    // 查询分数大于80分的学生的基本信息
    studentInfosDF.registerTempTable("student_infos")
    var sql = "select name,age from student_infos where name in ("
    for(i <- 0 until goodStudentNames.length) {
      sql += "'" + goodStudentNames(i) + "'"
      if(i < goodStudentNames.length - 1) {
        sql += ","
      }
    }
    sql += ")"
    val goodStudentInfosDF = sqlContext.sql(sql)
    goodStudentScoresDF.show()
    goodStudentInfosDF.show()
    val goodStudentsRDD = goodStudentScoresDF.rdd.map { row => (row.getAs[String]("name"), row.getAs[Long]("score"))}
    val goodStudentInfoRDD=goodStudentInfosDF.rdd.map { row => (row.getAs[String]("name"), row.getAs[Long]("age")) }
    val goodStudentRowsRDD = goodStudentsRDD.join(goodStudentInfoRDD).map(info => Row(info._1, info._2._1.toInt, info._2._1.toInt))


    val structType = StructType(Array(
      StructField("name", StringType, true),
      StructField("score", IntegerType, true),
      StructField("age", IntegerType, true)))

    val goodStudentsDF = sqlContext.createDataFrame(goodStudentRowsRDD, structType)
    goodStudentsDF.show()
  }
}
