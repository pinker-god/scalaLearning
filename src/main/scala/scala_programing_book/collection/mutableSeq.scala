package scala_programing_book.collection

/**
  * @author pinker on 2018/1/12
  *         父trait:Seq
  *         子:IndexedSeq(trait),List,Stream,Stack,Queue
  *         子:IndexedSeq的孩子Vector(ArrayBuffer的不可变版本,
  *         树形结构的形式实现的,每个节点可以有不超过32个子节点),
  *         Range(正数序列)
  */
object mutableSeq {
  def main(args: Array[String]): Unit = {
    val range = Range(0, 10000, 1000)
    val range1 = 0 to 10000 by 1000
    val vec = Vector(range: _*)
    val vec1 = Vector(range1: _*)
    println(vec :+ 4)
    println(vec1 ++ Vector(4, 5))
  }
}
