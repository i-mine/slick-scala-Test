package demo1

import com.typesafe.scalalogging.slf4j.Logger
import demo1.SlickDB.UserInfo
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Await

/**
  * Created with IntelliJ IDEA.
  * Author: dulei
  * Date: 18-3-28
  * Desc: 
  */
object SlickOption1 extends App {
//  val logger = Logger(LoggerFactory.getLogger(SlickOption1.getClass))
  val logger = LoggerFactory.getLogger(SlickOption1.getClass)
  val db = DBUtil.getDatabase()
  logger.info("get db connection")
  val slick_table = SlickDB.slick_table

  //query all and print
  val queryAllAciton = slick_table.map{
    user => Tuple3(user.id, user.name, user.age)
  }.result
  val res3 = db.run(queryAllAciton)
  Await.result(res3, Duration.Inf).foreach(println(_))

  //update
  val new_user = UserInfo(2L, "shimeng", 24)
  val updateAction = slick_table.filter(_.name === "shime").update(new_user)
  val res4 = db.run(updateAction)
  Await.result(res4, Duration.Inf)

  //delete
  val deleteAction = slick_table.filter(_.name === "wangrui").delete
  val res5 = db.run(deleteAction)
  Await.result(res5, 1 seconds)

  //-----------------use sql---------------------------
  //insert sql
  val id = 3L
  val name = "wangrui"
  val age = 24
  val insert_sql = sqlu"""insert into user_info values($id, $name, $age)"""
  Await.result(db.run(insert_sql), Duration.Inf)

  //query sql
  val res6 = Await.result(db.run(sql"""select * from user_info where id = '3'""".as[(Long, String, Int)]), Duration.Inf)
  logger.info(s"result is $res6")

  //update sql
  val update_sql = sqlu"""update user_info set name='shimeng.cui' where name='shimeng'"""
  Await.result(db.run(update_sql), Duration.Inf)

  //delete sql
  val delete_sql = sqlu"""delete from user_info where name='wangrui'"""
  Await.result(db.run(delete_sql), Duration.Inf)
}
