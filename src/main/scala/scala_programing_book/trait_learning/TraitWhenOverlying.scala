package scala_programing_book.trait_learning

import java.util.Date

/**
  * @author pinker on 2018/1/9
  *         !!!特质从最后一个开始处理!!!
  */
object TraitWhenOverlying extends App {
  //先调用ShortLogger 调用super.log为TimestampLogger调用super.log为PrintLogger
  val act1 = new SavingsAccount with PrintLogger with TimestampLogger with ShortLogger
  //先调用TimestampLogger 调用ShortLogger调用super..log为PrintLogger
  val act2 = new SavingsAccount with PrintLogger with ShortLogger with TimestampLogger
  //如果你想指定调用特定的trait,用super[Trait].log()
  //  val act3 = new SavingsAccount with LongLogger with PrintLogger
  act1.deposit(1000)
  act1.withDraw(400)
  act1.withDraw(700)
  act2.deposit(1000)
  act2.withDraw(400)
  act2.withDraw(700)
  /*act3.deposit(1000)
  act3.withDraw(400)
  act3.withDraw(700)*/
}

trait TimestampLogger extends PrintLogger {
  override def log(msg: String): Unit = {
    println("我被调用了,我在TimestampLogger里")
    super.log(new Date() + "\t" + msg)
  }
}

trait ShortLogger extends PrintLogger {
  val maxLength = 15

  override def log(msg: String): Unit = {
    println("我被调用了,我在ShortLogger里")
    super.log(if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
  }
}

/**
  * 下面这个会报编译错误!
  */
/*
trait LongLogger extends MyLogger {
  override def log(msg: String): Unit = {
    super.log(new Date() + "\t" + msg)
  }
}*/
