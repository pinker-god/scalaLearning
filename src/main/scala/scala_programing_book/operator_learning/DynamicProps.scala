package scala_programing_book.operator_learning

import scala.language.dynamics

/**
  * @author pinker on 2018/1/10
  */
abstract class DynamicProps(val props: java.util.Properties) extends Dynamic {
  def updateDynamic(name: String)(value: String): Unit = {
    props.setProperty(name.replaceAll("_", "."), value)
  }

  def selectDynamic(name: String)(value: String): Unit = {
    props.getProperty(name.replaceAll("_", "."))
  }

  def applyDynamicNamed(name: String)(args: (String, String)*): Unit = {
    if (name != "add") throw new IllegalArgumentException
    for ((k, v) <- args) {
      props.setProperty(k.replaceAll("_", "."), v)
    }
  }
}

class Person extends Dynamic {
  def updateDynamic(field: String)(newValue: String): Unit = {
    println(s"update\t$field\t$newValue")
  }

  def selectDynamic(field: String)(value: String) = {
    println(s"select\t$field\t$value")
    value
  }

  def applyDynamic(field: String)(value: String) = {
    println(s"apply\t$field \t$value")
  }

  def test(arg: String): Unit = {
    println(s"test\t$arg")
  }
}

object DynamicDemo extends App {
  val person = new Person
  //调用update
  person.lastName = "Neo"
  //调用select
  //  val name = person.lastName
  //  println(name)
  //这个调用apply
  val neo = person.findByLastName("Neo")
  person.applyDynamic("test")("hs")
}
