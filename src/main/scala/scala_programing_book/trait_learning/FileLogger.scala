package scala_programing_book.trait_learning

import java.io.PrintWriter
import java.util.Date

/**
  * @author pinker on 2018/1/10
  */
trait FileLogger extends MyLogger {
  val filePath: String
  val out = new PrintWriter(filePath)
  out.print("#\t" + new Date().toString + "\n")

  override def log(msg: String): Unit = {
    out.println(msg)
    out.flush()
  }
}

/**
  * 这样会报空指针异常,因为FileLogger的初始化在Demo之前
  */
/*
object Demo extends App with FileLogger {
  override val filePath = "src/main/resources/app1.log"
  log("hs pinker")
}*//*
object Demo extends App {
  val acount = new {
    val filePath = "src/main/resources/app1.log"
  } with SavingsAccount with FileLogger
  acount.log("hs pinker")
}*/
//感觉这个方法不错!或者在FileLogger里将相应的属性设置为lazy
object Demo extends {
  val filePath = "src/main/resources/app2.log"
} with App with FileLogger {
  log("hs pinker")
}
