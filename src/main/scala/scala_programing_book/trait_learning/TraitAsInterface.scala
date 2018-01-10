package scala_programing_book.trait_learning

/**
  * @author pinker on 2018/1/9
  */
object TraitAsInterface {
  def main(args: Array[String]): Unit = {
    val logger = new ConsoleLogger
    logger.log("pinker")
    val savings = new SavingsAccount
    savings.withDraw(1000)
    savings.deposit(2000)
    savings.withDraw(300)
    println(savings.balance)
  }
}

trait MyLogger {
  def log(msg: String)
}

/**
  * Logger with Cloneable with Serializable 为一个整体再来扩展
  */
@SerialVersionUID(123L)
class ConsoleLogger extends MyLogger with Cloneable with Serializable {
  override def log(msg: String): Unit = println(msg)
}

trait PrintLogger extends MyLogger {
  override def log(msg: String): Unit = println(msg)
}

/**
  * 其实这个从接口中获得具体的实现类Java8中已经可以做到!scala没有多大优势,区别scala除第一个继承用extends
  * 后面的接口用with
  */
class SavingsAccount extends Acount(0, 0) with PrintLogger {
  def withDraw(amount: Double): Unit = {
    if (amount > balance) log("Insufficient funds")
    else balance -= amount
  }
}