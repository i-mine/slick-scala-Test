package demo1

import slick.lifted.{TableQuery, Tag}
import slick.driver.MySQLDriver.api._
/**
  * Created with IntelliJ IDEA.
  * Author: dulei
  * Date: 18-3-27
  * Desc: 
  */
object SlickDB {
  //create bean
  case class UserInfo(id: Long, name: String, age: Int)
  //table name : "user_info"
  class SlickModelTable(tag: Tag) extends Table[UserInfo](tag, "user_info"){
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def age = column[Int]("age")
    def * = (id, name, age) <> (UserInfo.tupled, UserInfo.unapply)
  }
  def slick_table = TableQuery[SlickModelTable]
}