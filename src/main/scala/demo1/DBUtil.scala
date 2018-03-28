package demo1

import java.io.File

import com.typesafe.config.ConfigFactory
import slick.driver.MySQLDriver.api._
/**
  * Created with IntelliJ IDEA.
  * Author: dulei
  * Date: 18-3-27
  * Desc: 
  */
object DBUtil {
  val file = new File("application.conf")
  val config = ConfigFactory.parseFile(file)
  val db = Database.forConfig("mysql_db")

  def getDatabase():Database = {
    return db
  }
}
