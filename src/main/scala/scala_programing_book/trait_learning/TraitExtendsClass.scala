package scala_programing_book.trait_learning

import java.io.IOException

/**
  * @author pinker on 2018/1/10
  *         public class Exception extends Throwable {
  */
trait MyException extends Exception with MyLogger {
  override def log(msg: String): Unit = println(msg)

  def log() {
    log(getMessage)
  }
}

/**
  * trait扩展的class自动成为超类,with了这个trait的时候只能使用其超类的子类
  */
object TraitExtendsClass extends IOException with MyException with App {
  log()
}
