package scala_programing_book.advancedFunction

import java.util

/**
  * 高阶函数,相当于f(g(x))函数的组合因而再这里被称为高阶函数.
  *
  * @author pinker on 2018/1/11
  */
object FunctionAsArgs {
  def main(args: Array[String]): Unit = {
    val fun1 = scala.math.ceil _
    val fun2 = scala.math.sqrt _
    println(valueAtOneQuarter(fun1))
    println(valueAtOneQuarter(fun2))
    val f: (String, Int) => Char = _.charAt(_)
    //    val test: (Int, Double, String) => Unit = println("value" + _ + "\t" + _ + "\t" + _)
    val test0 = (x: Int) => println("lk" + _)
    val test1: Int => Unit = println(_)
    val test = (x: Int, y: Double, z: String) => println("value\t" + x + "\t" + y + "\t" + z)
    test(3, 2.0, "hs")
    test0(3)
    test1(4)
    (1 to 9).map("*" * _).foreach(println)
    println((1 to 9).reduceLeft(_ * _))
    println((1 to 9).reduceRight(_ * _))
    "Marry had a little lamb".split(" ").sortWith(_.length < _.length).foreach(println)
    Array(1, 6, 3, 2, 8).sortWith(_ < _).foreach(println)
    //SAM
    val arr = new util.ArrayList[String]();
    arr.add("pinker")
    arr.add("hs")
    arr.add("yotk")
    arr.add("hotk_family")
    arr.sort(_.length - _.length)
    println(arr)
    arr.sort(-_.length + _.length)
    println(arr)
  }

  /**
    * 有点像已知x,函数未定,根据不同得函数来计算不同得结果.
    * f:已知,x:已知:f(x)正常得函数调用
    * f:未知,x:已知:根据不同的函数调用,如我们的本例
    * f:未知,x:未知:,根据不同的函数用不同的函数去处理变量,这个就没意义了
    * f:已知,x:未知,结果已知,这就是砸门的那个求反函数啊,可以用模式匹配,实际上调用了unapply
    *
    * @param f
    * @return
    */
  def valueAtOneQuarter(f: (Double) => Double): Double = f(0.25)
}
