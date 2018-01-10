package scala_programing_book.operator_learning

/**
  *
  */

object UnapplyDemo {
  def apply: UnapplyDemo = {
    println("apply 被调用了")
    new UnapplyDemo()
  }

  def unapply(input: String): Option[Int] = {
    println("unapply 被调用了")
    try {
      Some(input.trim.toInt)
    } catch {
      case ex: NumberFormatException => {
        println(ex)
        None
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val number = "123456"
    //    val number="123456f"
    number match {
      case UnapplyDemo(n) => println(n.getClass + "\t" + n)
      case ex => println("failed")
    }
  }
}
class UnapplyDemo
