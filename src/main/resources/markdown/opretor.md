# 操作符及`apply,update,unapply,unapplySeq,Dynamic`

## 自定义二元操作符,对应中缀表达式

```scala
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
}
```

**上面的程序我们自定义了一个`*`操作符,可以看出其实就是定义一个方法,`a * b`相当于`a.*b`**

## apply方法

这个比较简单,一般定义在半生对象里面,取代`new class`

## unapply 提取器--apply的反向操作

这个就比较有意思!

```scala
object UnapplyDemo {
  def apply: UnapplyDemo = {
    println("apply 被调用了")
    new UnapplyDemo()
  }

  def unapply(input: String): Option[Int] = {
    println("unapply 被调用了")
    try {
      Some(input.trim.toInt)
    } catch {
      case ex: NumberFormatException => {
        println(ex)
        None
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val number = "123456"
    //    val number="123456f"
    number match {
      case UnapplyDemo(n) => println(n.getClass + "\t" + n)
      case ex => println("failed")
    }
  }
}
class UnapplyDemo
```

**result**

```shell
unapply 被调用了
int	123456
```

我们发现,确实是`unapply`方法被调用了,相当于砸门函数里的反函数`sin(x)=0.5`,是不是很有意思,这个地方吧面向对象和函数结合的真是有意思,这里页常常可以用强大的模式匹配来处理.

## `unapplySeq`

```scala
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
```

**注意的是`unapply`与`unapplySeq`如果有相同的入参就会报错,这个反函数怎么样?是不是更加强大,可以根据不同的结果反推出不同的输入,而且更加契合模式匹配**

## update

```scala
object UpdateDemo {

  def main(args: Array[String]): Unit = {
    //    method1
    val change = new Test
    change("hs", 18) = "xidian"
    change("hg", 76) = "hell"
  }

  private def method1 = {
    val map = new scala.collection.mutable.HashMap[String, Int]
    //注意,这个调用update必须在debug模式下才能调出来
    map("bob") = 12
    val nmap = map("bob")
    println(map("bob"))
    println(nmap + "\t")
  }
}

class Test {
  def update(name: String, age: Int, newAddress: String) =
    println(s"changing address of $name, whose age is $age to $newAddress")
}
```

**这里我们用一个自己定义的类测试了一下`update`方法,它默认调用的时候将最后一个参数作为`obj()=最后一个参数`的形式**

## Dynamic