package scala_programing_book

/**
  * 此类的功能主要是判断一个String是否含有大小写,主要练习了scala中的高级特性,如果Java实现的
  * 话需要对每个字符遍历
  *
  * @author pinker on 2018/1/9
  */
object HasUpper extends App {
  val out = Console.in
  for (i <- 1 to 5) {
    val name = out.readLine()
    println(name.exists(_.isUpper).formatted("%s"))
  }
  out.close()
}
