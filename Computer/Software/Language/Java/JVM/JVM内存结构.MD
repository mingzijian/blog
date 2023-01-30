JVM内存结构（Java8）
-------------------------------

###  Run-Time Data Areas （运行时数据区）

在程序执行期间使用的各种运行时数据区域。

#### The `pc` Register （程序计数器）

JVM支持多线程。每个JVM线程都有其自己的程序计数器。在任意时刻，每个线程都在执行单个方法的代码，即该线程的当前方法。如果该方法不是本地方法，则程序计数器包含当前正在执行的JVM指令地址。

简而言之，就是记录当前方法运行到哪了。

#### Java Virtual Machine Stacks （Java虚拟机栈）

每个JVM线程都有一个私有Java虚拟机栈，与该线程同时创建。

Java虚拟机栈的内存可以是不连续的。

Java虚拟机栈可以固定大小，也可以根据计算要求动态伸缩。

如果固定大小，则在创建每个Java虚拟机栈时可以独立选择它们的大小。

如果动态伸缩，则可以指定其初始值、最大值和最小值。

以下异常与Java虚拟机栈相关：

-  StackOverflowError

  线程中的计算超出Java虚拟机栈允许的范围（固定大小或最大值）。

-  OutOfMemoryError

  没有足够的内存来为新线程创建初始Java虚拟机栈。

#### Heap （堆）

所有Java虚拟机线程之间共享的内存区域。

堆是在虚拟机启动时创建的。

自动存储管理系统（称为垃圾收集器）可以回收对象的堆存储；对象永远不会显式释放。 

Java虚拟机不假定特定类型的自动存储管理系统，

并且可以根据实现者的系统要求选择存储管理技术。

堆的大小可以是固定的，

也可以根据计算的需要进行扩展，如果不需要更大的堆，则可以将其收缩。

堆的内存不必是连续的。

Java虚拟机实现可以为程序员或用户提供对堆初始大小的控制，并且，如果可以动态扩展或收缩堆，则可以控制最大和最小堆大小。

以下异常情况与堆关联：

- OutOfMemoryError

  计算需要的堆多于自动存储管理系统所能提供的堆，将引发 OutOfMemoryError。
 ![堆内存分配](https://images.gitee.com/uploads/images/2020/0624/133832_20f4d1d6_536895.png "堆内存分配.png")


#### Method Area （方法区）

所有Java虚拟机线程之间共享。

它存储每个类的结构，例如运行时常量池，字段和方法数据，以及方法和构造函数的代码，包括用于类和实例初始化以及接口初始化的特殊方法。

方法区是在虚拟机启动时创建的。

方法区可以固定大小，也可以指定初始值、最大值和最小值从而根据计算需要来进行动态伸缩。

方法区的内存不必是连续的。

以下异常条件与方法区域相关联：

- OutOfMemoryError

  如果无法提供方法区域中的内存来满足分配请求，则Java虚拟机将引发OutOfMemoryError。

####  Run-Time Constant Pool （运行时常量池）

 Each run-time constant pool is allocated from the Java Virtual Machine's method area ([§2.5.4](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.5.4)).  

每个运行时常量池都是从JVM的方法区分配的。

#### Native Method Stacks （本地方法栈）

Java虚拟机的实现可以使用传统的栈，俗称“C栈”，以支持本地方法（用Java编程语言以外的语言编写的方法）。

本地方法栈也可以由Java虚拟机的指令集的解释器的实现使用，例如C.Java虚拟机实现不能加载本地方法，并且本身不依赖于传统栈，不需要提供本地方法栈。如果提供，则通常在创建每个线程时为每个线程分配本地方法栈。

本地方法栈可以固定大小，也可以指定初始值、最大值和最小值从而根据计算需要来进行动态伸缩。

以下异常条件与本地方法栈关联：

- StackOverflowError
- OutOfMemoryError



| JVM内存区域  | 何时创建            | 内存连续 | 所有线程共享<br>当前线程独享 | 固定大小<br>动态伸缩 | 相关异常                                |
| ------------ | ------------------- | -------- | ---------------------------- | -------------------- | --------------------------------------- |
| 堆           | 虚拟机启动时        | 不必     | 共享                         | √                    | OutOfMemoryError                        |
| 本地方法栈   | 对应线程创建时（?） | 连续     | 独享                         | √                    | StackOverflowError<br/>OutOfMemoryError |
| Java虚拟机栈 | 对应线程创建时      | 连续     | 独享                         | √                    | StackOverflowError<br/>OutOfMemoryError |
| 程序计数器   | 对应线程启动时      | -        | 独享                         | -                    | -                                       |
| 方法区       | 虚拟机启动时        | 不必     | 共享                         | √                    | OutOfMemoryError                        |

 ![运行时数据区](https://images.gitee.com/uploads/images/2020/0624/131226_d5e57543_536895.png "运行时数据区.png")

 ![运行时数据区](https://images.gitee.com/uploads/images/2020/0624/115457_72b2c95a_536895.png "jvms-2.5.png")

#### 参考

 -  https://www.cnblogs.com/ityouknow/p/5610232.html
 -  https://baijiahao.baidu.com/s?id=1668705529778019153&wfr=spider&for=pc 
 -  https://docs.oracle.com/javase/specs/jvms/se8/html/index.html 
 -  https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.5 

   