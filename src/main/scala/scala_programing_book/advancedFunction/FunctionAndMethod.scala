package scala_programing_book.advancedFunction

/**
  * 这里深刻得演示了函数和方法得区别,函数得扩展性是多么得好!
  *
  * @author pinker on 2018/1/11
  */

object FunctionAndMethod {
  def main(args: Array[String]): Unit = {
    val num = 3.14
    val demo = new FunctionAndMethod
    demo.invokeFunction(demo.triple, num)
    demo.invokeFunction(x => x * 4, num)
    demo.invokeFunction(x => x * 5, num)
    demo.invokeFunction(demo.triple1,num)
    demo.invokeMethod(num)
  }
}

class FunctionAndMethod {
  val triple: Double => Double = (x: Double) => x * 3

  def triple1: Double => Double = (x: Double) => x * 3

  def tri(x: Double): Double = x * 3

  def invokeFunction(function: Function[Double, Double], arg: Double): Unit = {
    println(arg + "\t" + function(arg))
  }

  def invokeMethod(arg: Double): Unit = {
    println(arg + "\t" + tri(arg))
  }
}
