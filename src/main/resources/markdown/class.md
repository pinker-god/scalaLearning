# scala class
## Field :var vs val
1. var

  ```java
  class Person {
  var age = 0
  }
  ```

  **反编译后:**

  ```shell
   $ scalac Person.scala
   $ javap -private Person
   
   Compiled from "Person.scala"
   public class Person {
   private int age;
   public int age();
   public void age_$eq(int);
   public Person();
   }
  ```


  **Result:** 可以看出自己提供了`setter`,`getter`方法

2.  val

   ```java
   class People{
   val age=0
   }
   ```

   **编译后:**

   ```shell
   $ scalac People.scala
    $ javap -private People
    
    Compiled from "Person.scala"
    public class Person {
    private int age;
    public int age();
    public Person();
    }
   ```

   **Result:**可以看出,与`var`相比,少了个`public void age_$eq(int);`即`setter`方法

3. 自定义`setter`,`getter`方法

   ```java
   class Person1 {
     private var page = 0
     def age = page
     def age_=(newValue: Int) = {
     if (newValue > page)
     page = newValue
    }
   }

   ```

   **编译后:**

   ```shell
   $ javap Person1

     Compiled from "Person1.scala"
     public class Person1 {
     public int age();
     public void age_$eq(int);
     public Person1();
     }
   ```

   **Result:**可以看出,由于私有属性被同时会生成私有的`setter`,`getter`方法,故而被隐藏,相比与没有自定义的类,少了`private int age;`即`age`属性的定义,但是提供了其`public`方法

4. `field`为私有

   ```java
   class Demo{
   private var age=0
   private val name="hs"
   }
   ```

   **反编译后:**

   ```shell
   $ javap Demo
   Compiled from "Demo.scala"
   public class Demo {
     public Demo();
   }
   ```

   **Result:**可以看出,私有属性全部被隐藏了.这也解释了上面自定义方法为什么可以实现.

5. 总结一下:

   - 如果字段是私有的,则`getter`,`setter`方法也是私有的
   - 如果字段是`val`,则只有`getter`方法生成
   - 如果你不需要任何`setter` ,`getter`方法将字段声明为`private[this]`

6. scala中没有参数得函数可以带括号,也可以不带括号.

   - 带括号得函数调用时可以加括号,也可以不加括号
   - 不带括号得函数调用时一定不能加括号.
   - 对于改变值得建议加上括号,即`setter`方法一般加上括号
   - 对于取值类方法一般不加括号

7. 类私有和对象私有:`scala`和`Java`一样,方法可以访问该类所有对象得私有字段.

   1. 类私有
      - java

   ```java
   public class Test1 {
       public static void main(String[] args) {
           Counter3 other = new Counter3();
           System.out.println(other.incrementAndGet());
           System.out.println(new Counter3().less(other));
       }
   }

   class Counter3 {
       private int value = 0;

       public int incrementAndGet() {
           return ++value;
       }

       public boolean less(Counter3 other) {
           System.out.println("other.value"+"\t"+other.value);
           return value < other.value;
       }
   }
   ```

   **结果**:

   ```powershell
   1
   other.value	1
   true
   ```

   - scala

   ```scala
   class Counter2 {
     private var value = 0
     private[this] var value1=0

     def incrementAndGet: Int = {
       value += 1
       value
     }

     def less(other: Counter2): Boolean = {
       println("scala other.value" + "\t" + other.value)
       value < other.value
     }
   }

   object Demo2 {
     def main(args: Array[String]): Unit = {
       val other = new Counter2()
       println(other.incrementAndGet)
       println(new Counter2().less(other))
     }
   }
   ```

   **结果:**

   ```shell
   1
   scala other.value	1
   true对象私有(scala有而java没有)--private[this]
   ```

   - 对象私有--private[this]
   - `scala`对类私有生成私有的`getter`,`setter`方法,但是对对象私有的根本不胜成`getter`,`setter`方法.

8.  private[类名]--将访问权赋予外部类


## scala constructor

- primary constructor会执行	类中定义的所有语句.	这使得当你在配置类的某个字段时特别有用.
- auxiliary constructor:this
- 如果类名后没有参数,则该类自动有一个无参的`primary constructor`,这样的构造器仅仅时简单的执行类体中的所有语句而已.
- `primary constuctor`可以使用默认参数
- `primary constructor`中类似`class Person(name: String, age: Int) `的字段等同于`private[this] val字段 `的效果.

```scala
object Demo3 {
  def main(args: Array[String]): Unit = {
    println(new Person())
    println(new Person)
    println(new Person("hs"))
    println(new Person("hs", 12))
    new LoadFile
  }
}

class Person(var name: String, var age: Int) {
  def this() = this(null, 0)

  def this(name: String) = this(name, 0)

  println("this is belong primary constructor")

  override def toString: String = name + "\t" + age
}
class LoadFile {
  private val pros = new Properties
  pros.load(new FileReader("src/main/resources/people.properties"))
  println(pros.getProperty("Andy"))
}
```

**result:**

```shell
this is belong primary constructor
null	0
this is belong primary constructor
null	0
this is belong primary constructor
hs	0
this is belong primary constructor
hs	12
30
```

- `scala`四种构造器对比

  ```scala
  object Demo4 {
    def main(args: Array[String]): Unit = {
      val test1 = new Test1("pinker")
      val test2 = new Test2("pinker")
      val test3 = new Test3("pinker")
      val test4 = new Test4("pinker")
      println("test1\t" + test1 + "\t" + test1.updateAndGet())
      println("test2\t" + test2 + "\t" + test2.updateAndGet() + "\t" + test2.less(new Test2("binker")))
      println("test3\t" + test3 + "\t" + test3.updateAndGet() + "\t" + test3.less(new Test3("binker")))
      println("test4\t" + test4 + "\t" + test4.updateAndGet() + "\t" + test4.less(new Test4("binker")))
       // 不能调用test2的name()和name_=方法,因为 pirvate
      //   println(test2.name)
      println(test3.name)
      println(test4.name+"\t"+test4.setName("eotk")+"\t"+test4.getName)
    }
  }

  class Test1(name: String) {
    override def toString: String = name

    def updateAndGet(): String = {
      //    不能改变name的值,确实相当于val的效果
      //    name += "hs"
      name
    }

    //不能调用other.name编译不通过,可见确实是private[this]的效果
    //  def less(other: Test1): Boolean = name < other.name
  }

  class Test2(private var name: String) {
    override def toString: String = name

    def updateAndGet(): String = {
      name += "hs"
      name
    }

    def less(other: Test2): Boolean = name < other.name
  }

  class Test3(var name: String) {
    override def toString: String = name

    def updateAndGet(): String = {
      name += "hs"
      name
    }

    def less(other: Test3): Boolean = name < other.name
  }

  class Test4(@BeanProperty var name: String) {
    override def toString: String = name

    def updateAndGet(): String = {
      name += "hs"
      name
    }

    def less(other: Test4): Boolean = this.getName < other.getName
  }
  ```

  ​

**result:**

```shell
test1	pinker	pinker
test2	pinker	pinkerhs	false
test3	pinker	pinkerhs	false
test4	pinker	pinkerhs	false
pinkerhs
pinkerhs	()eotk
```

- `primary constructor`变为私有

  ```scala
  class Test5 private(val name: String) {
    def this() = this("pinker")
  }
  val test5 = new Test5
      //编译不通过,primary constructor被私有了
      //    val test6=new Test5("jh")
  ```

  ​

- 类的嵌套--注意`NetWork#Member`是任何类`NetWork`的`Member都可以,如果想更细粒度,后面会专门有一节来讲.`

  ```scala
  class NetWork {

    class Member(val name: String) {
      val contacts = new ArrayBuffer[Member]

      override def toString: String = name
    }

    //类型投影可以
    val members = new ArrayBuffer[NetWork#Member]
    //这个普通的不可以,会报错如下
    /*Error:(28, 22) type mismatch;
    found   : scala.collection.mutable.ArrayBuffer[other.Member]
    required: NetWork.this.Member
    members += other.members*/
    //  val members = new ArrayBuffer[Member]

    def join(name: String): NetWork = synchronized {
      val m = new Member(name)
      members += m
      this
    }

   def join(other: Member): NetWork = synchronized {
      members += other
      this
    }

    def join(other: NetWork): NetWork = synchronized {
      members ++= other.members
      this
    }
  }

  object Demo5 {
    def main(args: Array[String]): Unit = {
      val chatter = new NetWork
      println(chatter.join("eotk").join("hotk").join("yotk").join("wotk").members.mkString(","))
      val myFace = new NetWork
        println(chatter.join("hs").join(new chatter.Member("zs")).join(myFace.join("ys")).members.mkString("-")
      )
    }
  }
  ```

  ​

## 总结

啰啰嗦嗦把整个讲了一遍,自我总结一遍:

- Field:
  - val: 只有 相对应的`getter`方法
  - var:自动生成对应的`getter`,`setter`方法
  - private:使得相应的属性的`setter`,`getter`方法都私有
  - private[this]:对象私有
- Constructor
  - primary constructor:
  - auxiliary constructor: `def this()=`
  - class Person(name:String)等同于`class Person{private[this] val name:String}`
  - @BeanProperty:相当于自动生成`Java`的`setXXX,getXXX`方法,即`JavaBean`
  - 类的嵌套:
    - 每个对象各自有一个内部类,用如`new chatter.Member("zs")`来构造
    - 类的投影:`NetWork#Member`为任何`Network`类的`Member`对象,其他的类的投影后面会有专门的章节讲述.