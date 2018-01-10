package scala_programing_book.trait_learning

import scala.collection.mutable.ArrayBuffer

class Acount (val id: Int, initialBalance: Double) {
  var balance = initialBalance

  def deposit(amount: Double): Double = {
    balance += amount
    balance
  }

  override def toString: String = id + "->" + balance
}

object Acount {
  private var lastNumber = 0
  println("i am in constructor of object")

  def newUniqueNumber(): Int = {
    lastNumber += 1
    lastNumber
  }

  def apply(initialBalance: Double): Acount = new Acount(newUniqueNumber(), initialBalance)

  def main(args: Array[String]): Unit = {
    val accounts = ArrayBuffer[Acount](Acount(100.0), Acount(1000.0), Acount(10000.0))
    accounts.foreach(println)
  }
}