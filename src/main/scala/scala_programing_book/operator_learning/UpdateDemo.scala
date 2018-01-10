package scala_programing_book.operator_learning

/**
  * @author pinker on 2018/1/10
  */
object UpdateDemo {

  def main(args: Array[String]): Unit = {
    //    method1
    val change = new Test
    change("hs", 18) = "xidian"
    change("hg", 76) = "hell"
  }

  private def method1 = {
    val map = new scala.collection.mutable.HashMap[String, Int]
    //注意,这个调用update必须在debug模式下才能调出来
    map("bob") = 12
    val nmap = map("bob")
    println(map("bob"))
    println(nmap + "\t")
  }
}

class Test {
  def update(name: String, age: Int, newAddress: String) =
    println(s"changing address of $name, whose age is $age to $newAddress")
}
