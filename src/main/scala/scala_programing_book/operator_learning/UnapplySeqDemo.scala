package scala_programing_book.operator_learning

/**
  * @author pinker on 2018/1/10
  */
object UnapplySeqDemo {
  def unapplySeq(input: String): Option[Seq[String]] =
    if (input.trim == "") None else Some(input.trim.split("\\s+"))

  def main(args: Array[String]): Unit = {
    val author = "hs  eotk yotk pinker"
    //    val UnapplySeqDemo(author) = "hs pinker select"
    author match {
      case UnapplySeqDemo(first, last) => println(first + "\t" + last)
      case UnapplySeqDemo(first, mid, last) => println(first + "\t" + mid + "\t" + last)
      case UnapplySeqDemo(first, "eotk", "yotk", last) => println("匹配到我了")
    }
  }
}
