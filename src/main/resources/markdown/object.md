# object

## object vs class

1. 对象本质上拥有类的所有特性,它设置可以扩展其他类或者`trait`,只有一个例外,你不能提供构造函数

   ```scala
   object Acount {
     private var lastNumber = 0
     println("i am in constructor of object")
     def newUniqueNumber(): Int = {
       lastNumber += 1
       lastNumber
     }
   }
   object Demo1 {
     def main(args: Array[String]): Unit = {
       println(Acount.newUniqueNumber())
     }
   }
   ```

   **result:**

   ```shell
   i am in constructor of object
   1
   ```

   ​

2. 对象用的地方:

   - 作为存放工具函数和常量的地方
   - 高效的共享某个不可变实例
   - 需要用单例来协调某个服务时

3. 伴生类和伴生对象--可以互相访问其私有属性

   ```scala
   object Acount {
     private var lastNumber = 0
     println("i am in constructor of object")

     def newUniqueNumber(): Int = {
       lastNumber += 1
       lastNumber
     }
   }

   class Acount {
     val id: Int = Acount.newUniqueNumber()
     private var balance = 0.0

     def deposit(amount: Double): Double = {
       balance += amount
       balance
     }
   }
   ```

4. 扩展类和对象---目前不知道有啥用,难道我用类去扩展类不可以吗?像 `Java`那样

   ```scala
   abstract class UndoableAction(val description: String) {
     def undo(): Unit

     def redo(): Unit

     override def toString: String = description
   }

   object DoNothingAction extends UndoableAction("do nothing") {
     override def undo(): Unit = {}

     override def redo(): Unit = {}
   }

   object PrintfAction extends UndoableAction("print") {
     override def undo(): Unit = {
       println(this.description + "\tin undo")
     }

     override def redo(): Unit = {
       println(this.description + "\tin redo")
     }
   }

   object ActionTest {
     def main(args: Array[String]): Unit = {
       val actions = Map("open" -> DoNothingAction, "save" -> PrintfAction, "close" -> PrintfAction)
       actions.foreach(println)
     }
   }
   ```

   5. 枚举

      - 通过下面的例子我们主要掌握了`scala`中的枚举时object通过继承`Enumeration`来实现的,其中`Value`是个内部抽象类,对应一个唯一id,`values`是所有枚举值的集合.
      - 同时,我们也展示了枚举类型的模式匹配,用起来很方便.

      ```scala
      object WeekDay extends Enumeration {
        type WeekDay = Value
        val mon = Value(1)
        val tue = Value(2)
        val wed = Value(3)
        val thur = Value(4)
        val fri = Value(5)
        val sat = Value(6)
        val sun = Value(7)

        def checkExists(day: Int): Boolean = {
          this.values.exists(_.id == day)
        }

        def isWorkingDay(day: WeekDay): Boolean = !(day == sat || day == sun)

        def showAll = this.values.foreach(println)

        def main(args: Array[String]): Unit = {
          val test = WeekDay.fri
          println(test + "\t" + test.id + "\t")
          println(WeekDay.checkExists(7))
          println(WeekDay.checkExists(8))
          WeekDay.showAll
          println(WeekDay.isWorkingDay(test))
          println(WeekDay.isWorkingDay(WeekDay.sun))
          test match {
            case WeekDay.sat => println("星期六")
            case WeekDay.thur => println("星期四")
            case WeekDay.fri => println("星期五")
            case WeekDay.sun => println("星期天")
            case _ => println("其他天")
          }
        }
      }
      ```

      ​