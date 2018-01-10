# trait

老实说,感觉`trait`和**Java**中的`interface`已经很像了,因此下面我们主要说不像的.

1. `Java`用`implements`来实现接口,	`scala`用`extends`和`with`来实现接口

   先看一个经典的有关日志的例子

   ```scala
   object TraitAddExtra {
     def main(args: Array[String]): Unit = {
       val savingsAccount = new SavingsAccount1
       savingsAccount.deposit(1000)
       savingsAccount.withDraw(300)
       savingsAccount.withDraw(800)
     }
   }

   trait MyLogger1 {
     def log(msg: String)

     def info(msg: String) = log("INFO\t" + msg)

     def warn(msg: String) = log("WARN\t" + msg)

     def err(msg: String) = log("ERR\t" + msg)
   }

   class SavingsAccount1 extends Acount(0, 0) with MyLogger1 {
     def withDraw(amount: Double): Unit = {
       if (amount > balance) err("Insufficient funds")
       else {
         balance -= amount
         info("withDraw money" + amount)
         warn("remained money" + balance)
       }
     }

     override def log(msg: String): Unit = println(msg)
   }
   ```

   ```scala
   class Acount (val id: Int, initialBalance: Double) {
     var balance = initialBalance

     def deposit(amount: Double): Double = {
       balance += amount
       balance
     }

     override def toString: String = id + "->" + balance
   }

   object Acount {
     private var lastNumber = 0
     println("i am in constructor of object")

     def newUniqueNumber(): Int = {
       lastNumber += 1
       lastNumber
     }

     def apply(initialBalance: Double): Acount = new Acount(newUniqueNumber(), initialBalance)

     def main(args: Array[String]): Unit = {
       val accounts = ArrayBuffer[Acount](Acount(100.0), Acount(1000.0), Acount(10000.0))
       accounts.foreach(println)
     }
   }
   ```

   - 通过以上代码我们发现,`scala`在这点上和`java`上没有多大区别,接口里都可以定义抽象方法和具体方法.

2. 多`trait`引入的处理--`scala`这里采用从最后一个开始倒着处理.(这个说明一下,这种功能`java`无法实现,无论你用`interface`还是`abstract`,均无法调用`super.log()`这个方法,故无法完成,其实`Java`里这里采用策略设计模式会去实现,但是也非常不好用,还得靠组合函数去实现.这里就体现出了`scala`的优势,确实非常舒服!)

   ```scala
   object TraitWhenOverlying extends App {
     //先调用ShortLogger 调用super.log为TimestampLogger调用super.log为PrintLogger
     val act1 = new SavingsAccount with PrintLogger with TimestampLogger with ShortLogger
     //先调用TimestampLogger 调用ShortLogger调用super..log为PrintLogger
     val act2 = new SavingsAccount with PrintLogger with ShortLogger with TimestampLogger
     //如果你想指定调用特定的trait,用super[Trait].log()
     //  val act3 = new SavingsAccount with LongLogger with PrintLogger
     act1.deposit(1000)
     act1.withDraw(400)
     act1.withDraw(700)
     act2.deposit(1000)
     act2.withDraw(400)
     act2.withDraw(700)
     /*act3.deposit(1000)
     act3.withDraw(400)
     act3.withDraw(700)*/
   }

   trait TimestampLogger extends PrintLogger {
     override def log(msg: String): Unit = {
       println("我被调用了,我在TimestampLogger里")
       super.log(new Date() + "\t" + msg)
     }
   }

   trait ShortLogger extends PrintLogger {
     val maxLength = 15

     override def log(msg: String): Unit = {
       println("我被调用了,我在ShortLogger里")
       super.log(if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
     }
   }
   ```

   **结果**

   ```shell
   我被调用了,我在ShortLogger里
   我被调用了,我在TimestampLogger里
   Tue Jan 09 16:42:16 CST 2018	Insufficient...
   我被调用了,我在TimestampLogger里
   我被调用了,我在ShortLogger里
   Tue Jan 09 1...
   ```

   ​

3. `trait`的初始化顺序:注意和上面的`super.log`比较,会发现方向正好相反,初始化是按照顺序正常构造,`super`是从右向左来调用,前者的顺序化是线性化的反向,后者的顺序是线性化的方向.

   - 首先调用超类的构造器
   - `trait`构造器在超类构造器之后,在类构造器之后.
   - `trait`构造器由左到右被构造
   - 每个`trait`中,父构造器先被构造
   - 如果多个`trait`共有一个父`trait`,而那个父`trait`已经被构造,则不会被再次构造
   - 所有`trait`构造完毕,类被构造

   ​

   以`class SavingAcount extends Acount with FileLogger with ShortLogger`为例

   - `Acount`(超类)
   - `Logger`(第一个`trait`的父`trait`)
   - `ShortLogger`(第一个`trait`)
   - `FileLogger`(第二个`trait`)
   - `SavingAcount`(类)

4. `trait`和类之间的唯一技术差别就是`trait`不能右构造器,只有一个默认的无参构造器.你应该记得`object`也是这样,不能有构造器,其他的特性都有.