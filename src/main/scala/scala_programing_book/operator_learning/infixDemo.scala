package scala_programing_book.operator_learning

/**
  * 1. 本实例展示了一个自定义中缀表达式从而理解中缀表达式的原理
  * 2. 展示了apply方法和unapply方法的原理及其使用
  *
  * @author pinker on 2018/1/10
  */
object infixDemo extends App {
  val fraction = Fraction(1, 3) * Fraction(2, 5)
  println(fraction)
  fraction match {
    case Fraction(a, b) => println(a + "->" + b)
  }
}

class Fraction(val m: Int, val n: Int) {
  def *(other: Fraction) = new Fraction(m * other.m, n * other.n)

  override def toString = m + "/" + n
}

object Fraction {
  def apply(m: Int, n: Int): Fraction = new Fraction(m, n)

  def unapply(arg: Fraction): Option[(Int, Int)] = {
    println("我被调用了")
    if (arg.n == 0) None else Some((arg.m, arg.n))
  }

  def unapplySeq(input: String) = {

  }
}

