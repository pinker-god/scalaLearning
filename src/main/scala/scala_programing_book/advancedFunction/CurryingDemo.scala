package scala_programing_book.advancedFunction

import java.io.FileInputStream

/**
  * @author pinker on 2018/1/11
  */
object CurryingDemo {
  def main(args: Array[String]): Unit = {
    val demo1 = printStar("*") _
    demo1(3)
    println("-" * 10)
    demo1(4)
    println("-" * 10)
    demo1(5)
    val demo2 = printStar("&") _
    demo2(6)
    withIoStream(new FileInputStream("src/main/resources/app.log")) {
      println("开始读取")
      val buffer = new Array[Byte](1024)
      val len = -1
      _.read(buffer)
    }
  }

  /**
    * 更加通用的接口
    *
    * @param stream
    * @param fun
    */
  def withIoStream(stream: java.io.InputStream)(fun: java.io.InputStream => Unit) {
    try {
      fun(stream)
    } finally {
      stream.close()
    }
  }

  def printStar(grap: String)(num: Int) =
    for (i <- 1 to num) {
      println(grap * i)
    }

  def sum(x: Int, y: Int) = x + y

}
