package scala_programing_book.trait_learning

/**
  * @author pinker on 2018/1/9
  */
object TraitAddExtra {
  def main(args: Array[String]): Unit = {
    val savingsAccount = new SavingsAccount1
    savingsAccount.deposit(1000)
    savingsAccount.withDraw(300)
    savingsAccount.withDraw(800)
  }
}

trait MyLogger1 {
  def log(msg: String)

  def info(msg: String) = log("INFO\t" + msg)

  def warn(msg: String) = log("WARN\t" + msg)

  def err(msg: String) = log("ERR\t" + msg)
}

class SavingsAccount1 extends Acount(0, 0) with MyLogger1 {
  def withDraw(amount: Double): Unit = {
    if (amount > balance) err("Insufficient funds")
    else {
      balance -= amount
      info("withDraw money" + amount)
      warn("remained money" + balance)
    }
  }

  override def log(msg: String): Unit = println(msg)
}