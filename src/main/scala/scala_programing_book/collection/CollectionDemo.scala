package scala_programing_book.collection

import scala.collection.mutable.ListBuffer

/**
  * @author pinker on 2018/1/11
  */
object CollectionDemo {
  def main(args: Array[String]): Unit = {
    //    toOtherCollection
    //    println(digits(51298))
    //    testList()
    //    testSet()
    //    testMap()
    //    testIterable()
  }

  def testIterable(): Unit = {
    val map = Map(1 -> 2, 2 -> 3, 3 -> 4)
    val list = List(1, 2, 3, 4)
    val set = Set(1, 2, 3, 4)
    val its = map.iterator
    val its1 = list.iterator
    val its2 = set.iterator
    while (its.hasNext) {
      println(its.next())
    }
  }

  /**
    * Set用+添加元素,List用:+或者+:因为List是有顺序得.Set是无序得(是指插入顺序)
    * ++两者都可以用
    * -/--可以用于Set,被定义再SetLike里
    * 由于map是用Set实现得,因此和Set一样
    */
  def testMap(): Unit = {
    val map = Map(1 -> "hs", 3 -> "ys")
    println(map + (2 -> "zs"))
    println(map ++ Map(2 -> "zs"))
    println(map - 2)
  }

  def testSet(): Unit = {
    val set = Set(1)
    val set1 = set + 2
    println(set1)
    println(set1 ++ Set(3, 4))
    println(set1 - 1)
    println(Set(1 to 5: _*) -- set)
  }

  def testList(): Unit = {
    val list = List(1)
    val list1 = list :+ 2
    println(list)
    println(list1)
    val list2 = 3 +: list
    println(list2)
    println(list1 ++ list2)
    /* println(list1 - 1)
     println(list1 -- list2)*/
  }

  /**
    * expected List(5,1,2,9,8)
    *
    * @param n
    * @return
    */
  def digits(n: Int): List[Int] = {
    if (n < 0) digits(-n)
    else if (n < 10) List(n)
    else digits(n / 10) :+ (n % 10)
  }

  private def toOtherCollection = {
    val arr = Array(1 to 10: _*)
    val set = arr.toSet
    println(set)
    val seq = arr.toSeq
    println(seq)
    val list = arr.toList
    println(list)
    val arrayBuffer = arr.to[ListBuffer]
    println(arrayBuffer)
  }
}
