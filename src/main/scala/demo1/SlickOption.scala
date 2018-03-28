package demo1


import demo1.SlickDB.UserInfo
import org.slf4j.LoggerFactory
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created with IntelliJ IDEA.
  * Author: dulei
  * Date: 18-3-27
  * Desc: 
  */
object SlickOption extends App {
  //init logger
  val logger = LoggerFactory.getLogger(getClass.getSimpleName)
  logger.info("slick test start")
  // query by condition
  val db = new DBUtil().getDatabase()
  val slick_table = SlickDB.slick_table
  //创建表动作
  val createTableAction = slick_table.schema.create
  val res = db.run(createTableAction).andThen{
    case Success(_) => println("table user_info created")
    case Failure(e) => println(e.getMessage)
  }
  Await.result(res, 10 seconds)

  //插入数据库动作
  val insertAction = slick_table ++= Seq(
    UserInfo(1L, "dulei", 24),
    UserInfo(2L, "yuxin", 25),
    UserInfo(3L, "wangrui", 24)

  )
  val res1 = db.run(insertAction).andThen{
    case Success(_) => println("user infos inserted")
    case Failure(e) => println(e.getMessage)
  }
  Await.result(res1, 10 seconds)
  //数据查询动作
  val selectAction = slick_table.result
  val res2 = db.run(selectAction)
  def printResult[T](fut: Future[Iterable[T]]): Unit = {
    Await.result(fut, Duration.Inf).foreach(println)
  }
  printResult(res2)

//  val res2 = Await.result(db.run(slick_table.filter(_.age > 25).result), Duration.Inf)
//  val res6 = Await.result(db.run(sql"""select * from user_info where name = 'mary'""".as[(Long, String, Int)]), Duration.Inf)

}