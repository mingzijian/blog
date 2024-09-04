### GC 概念
垃圾收集 Garbage Collection 通常被称为“GC”

### GC 分类
#### Partial GC （部分垃圾收集）
##### Young GC 
- 收集区域
young gen
- 触发条件
young gen中的eden区分配满

##### Old GC
只收集 old gen (CMS)

##### Mixed GC
收集整个young gen以及部分old gen (G1)
#### Full GC （全部垃圾收集）
- 收集区域
整个堆
- 触发条件
当准备要触发一次young GC时，如果发现统计数据说之前young GC的平均晋升大小比目前old gen剩余的空间大，
则不会触发young GC而是转为触发full GC（因为HotSpot VM的GC里，除了CMS的concurrent collection之外，
其它能收集old gen的GC都会同时收集整个GC堆，包括young gen，所以不需要事先触发一次单独的young GC）；
或者，如果有perm gen的话，要在perm gen分配空间但已经没有足够空间时，
强调也要触发一次full GC；或者System.gc()、heap dump带GC，默认也是触发full GC。

### GC 垃圾收集算法

#### 标记清除（Mark-Sweep）
标记内存中将要回收的数据，直接清除
![Mark-Sweep](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/image-20240325163045324.png)

内存碎片（可用空间不连续）

可容纳最大对象（3 × 4）<= 剩余空间（4 × 6）

#### 复制（Copying）
将一片内存中的数据直接复制到另一块内存中，然后清理掉之前的内存区域
![Copying](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/image-20240325163119746.png)

空间利用率低（备用空间）

#### 标记整理（Mark-Compact）
将内存中的数据，标记集中移动到内存的一边，进行清理
![Mark-Compact](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/image-20240325163102423.png)

相对耗时较高（对象挪动）


#### 分代收集算法
  <pre>
  这个算法是物理和逻辑上将内存堆划分年轻代，老年代，根据不同代中的对象清除时候使用什么算法来提出的一种算法策略
  </pre>
**不同代使用不同算法：**

年轻代对象相对活跃，使用节约时间的复制算法
> 复制算法：Eden → S0   ；Eden+S0 → S1   ；Eden+S1 → S0 

老年代对象相对稳定，使用节约空间的标记整理算法
> 标记整理算法：Young  →  Old

![image-20240325171657298](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/image-20240325171657298.png)


#### 三色标记算法
  <pre>
    1,起初所有对象都是白色。
    2,从根出发扫描所有可达对象，标记为灰色，放入待处理队列。
    3,从队列取出灰色对象，将其引用对象标记为灰色放入队列，自身标记为黑色。
    4,重复 3，直到灰色对象队列为空。此时白色对象即为垃圾，进行回收。
  </pre>
    -黑色：根对象，或者该对象与它的子对象都被扫描
    -灰色：对象本身被扫描，但还没扫描完该对象中的子对象
    -白色(注意两个含义)：未被扫描对象，扫描完成所有对象之后，最终为白色的为不可达对象，即垃圾对象

  ![三色标记](https://images.gitee.com/uploads/images/2020/0624/183924_a77d5552_536895.gif "三色标记.gif")

  ![Animation_of_tri-color_garbage_collection](https://images.gitee.com/uploads/images/2021/0302/092213_b583c03d_536895.gif "Animation_of_tri-color_garbage_collection.gif")

  - 多标（浮动垃圾）
    本该回收的内存，但被标记为存活对象，未回收掉，被称之为“浮动垃圾”。浮动垃圾并不会影响垃圾回收的正确性，
    只是需等到下一轮垃圾回收才有被清除的可能。
  - 漏标（读写屏障，类比AOP）
    本不该回收的内存，未被标记为存活对象，被直接回收。这直接影响到了应用程序的正确性，是不可接受的。
    - 漏标只有同时满足以下两个条件时才会发生：
      - 条件一：灰色对象 断开了 白色对象的引用；即灰色对象 原来成员变量的引用 发生了变化。
      - 条件二：黑色对象 重新引用了 该白色对象；即黑色对象 成员变量增加了 新的引用。
    - 解决方案
      - 写屏障（Store Barrier）
        在赋值操作前后，加入一些处理 （ZGC）
      - 读屏障（Load Barrier）
        在载值操作前后，加入一些处理
        - Increment Update (CMS)
          破坏条件二。当 黑色对象 重新引用了 白色对象 时，可以将黑色对象置为灰色
        - Snapshot At The Beginning (G1)
          破坏条件一。当 灰色对象 断开了 白色对象的引用 时，将白色对象记录起来

### GC 垃圾收集器

####  Serial

复制算法

![Serial](https://images.gitee.com/uploads/images/2020/0624/135638_29677cda_536895.png "Serial.png")

####   Serial Old

标记整理算法

####  ParNew

-XX:+UseParNewGC

复制算法

![ParNew](https://images.gitee.com/uploads/images/2020/0624/141016_6e3be987_536895.png "ParNew.png")

#### Parallel Old

-XX:+UseParallelOldGC

标记整理算法

####  Parallel Scavenge

-XX:+UseParallelGC

#### CMS

Concurrent Mark Sweep 并发标记清除

    1）初始标记(STW)
    
    2）并发标记
    
    3）重新标记(STW)
    
    4）并发清除

初始标记
![初始标记](https://images.gitee.com/uploads/images/2020/0624/140324_d12c068e_536895.png "初始标记.png")

并发标记
![并发标记](https://images.gitee.com/uploads/images/2020/0624/140432_022a15fe_536895.png "并发标记.png")

##### 三色标记

| 颜色 | 描述                                                         |
| ---- | ------------------------------------------------------------ |
| 白   | 初始颜色，尚未被垃圾收集器标记，生死未卜的猫。               |
| 黑   | GC Root 可达，被垃圾收集器标记为存活。黑色对象及其所有可达对象都被认为是存活的。 |
| 灰   | 灰色对象肯定是存活的，但是它的一些子对象可能还是白色的，需要进一步标记。 |

![三色标记](https://images.gitee.com/uploads/images/2020/0624/163629_9ec7bc2e_536895.png "三色标记.png")



##### `Incremental Update + Store Barrier` (增量更新+写屏障)
    `Incremental Update` 算法判断如果一个白色的对象由一个黑色的对象引用，如三色标记图，D的引用由B-->A,A是目的地址，
所以cms 的`Incremental Update`算法是从目标入手解决的，
这是和SATB的第一个区别，发现这种情况时，也是通过`write barrier`写屏障技术，
把黑色的对象重新标记为灰色，让`collector`重新来扫描，活着通过`mod-union table`来标记，CMS 就是这样实现的，
这是第二个区别，做法不一样，也是上面讲的防止第一种情况发生。

####  G1

G1 （Garbage-First） 垃圾优先

##### 发展

2004 论文发布

2009 JDK6u14 体验

2012 JDK7u4 官方支持

2017 JDK9 默认 G1（CMS废弃）

##### 相关 JVM 参数

`-XX:+UseG1GC` （使用G1垃圾回收器，JDK9以上为默认）
`-XX:G1HeapRegionSize=8M` （设置单个Region的大小，这里设置为8M，可以推算出分区个数 ）
`-XX:MaxGCPauseMillis=200` （设置最大暂停时间，默认为200ms）
`-XX:InitiatingHeapOccupancyPercent=20` （堆内存使用占比参数，默认45）



##### G1的垃圾回收阶段主要分为三个阶段

youngCollection 是对新生代的垃圾回收。
youngCollection+Concurrent Mark 对新生代的垃圾回收，同时执行一些并发的标记。
MixedCollection 混合的垃圾回收。



##### 并发标记起始时间的调整

并发标记必须在堆空间占满前完成，否则退化为 FullGC，垃圾回收速度跟不上垃圾产生速度会退化为FullGC。
为了尽可能的避免 FullGC，可以设置 `-XX:InitiatingHeapOccupancyPercent`参数（堆内存使用占比参数），达到这个值，将会进行并发标记，然后进行 Mixed GC。
在 JDK 9 可以自动进行动态调整
进行数据采样并动态调整，尽可能的早点进行并发标记，保留一个安全的空档空间，确保堆内存足够，避免Full GC。

    1、初始标记(STW)
    2、并发标记
    3、最终标记(STW)
    4、筛选回收

与其它收集器相比，`G1`变化较大的是它将整个Java堆划分为多个大小相等的独立区域（`Region`），
虽然还保留了新生代和来年代的概念，但新生代和老年代不再是物理隔离的了它们都是一部分`Region`（不需要连续）的集合。
同时，为了避免全堆扫描，`G1`使用了`Remembered Set`来管理相关的对象引用信息。
当进行内存回收时，在GC根节点的枚举范围中加入`Remembered Set`即可保证不对全堆扫描也不会有遗漏了。
##### `SATB` (Snapshot At The Beginning + Store Barrier )(原始快照+写屏障)
    `SATB` 算法认为开始标记的都是活的对象。
如三色标记图所示,引用`B`到`D` 的引用改为`B`到`C`时，
通过`write barrier`写屏障技术，
会把`B`到`D`的引用推到`gc`遍历执行的堆栈上，保证还可以遍历到`D`对象，
相对于`d`来说，引用从`B-->A`,`SATB`是从源入手解决的，即上面说的第2种情况，
    `SATB`算法认为开始时所有能遍历到的对象都是需要标记的，即都是活的。
如果我把`b = null`,那么`d`就是垃圾了， `SATB`算法也还是会把`D`最终标记为黑色，导致`D`在本轮`gc`不能回收，成了`浮动垃圾`。

####  ZGC

JDK11引入实验特性

JDK15稳定正式特定

##### 关键技术
1. **染色指针**：将垃圾回收的元数据存储在指针的高位中，这样可以在**不访问对象头**的情况下获取对象的垃圾回收状态。
2. **内存多重映射**：通过不同的虚拟内存视图来管理内存，这允许它在**不停止应用**程序的情况下移动对象。

##### 支持JDK版本
JDK11及之后的版本

##### 目标
- 1 几毫秒的最大暂停时间（*）
- 2 暂停时间不会随着堆或活动集大小而增加（*）
- 3 处理大小从`8MB`到`16TB`的堆

##### 特性
###### 并发
Concurrent
###### 基于区域（分区）
Region-based
###### 压缩
Compacting
###### 非统一内存访问感知
NUMA（Non Uniform Memory Access）非统一内存访问

###### 使用彩色指针
colored pointers

###### 使用读屏障
读屏障（Load Barrier）
当读取成员变量时，一律记录下来

####  Shenandoah

-XX:+UseShenandoahGC

首次出现在 JDK 12 中

Red Hat
并发GC
目前不分代

#### Epsilon

-XX:+UseEpsilonGC

JDK 11  引入

Epsilon是一个“无操作”（No-Op）垃圾回收器，它不执行任何实际的垃圾回收工作。

主要用于测试和基准测试，特别是在性能测试中，可以帮助排除GC引起的性能假象。

由于不执行垃圾回收，Epsilon GC的停顿时间是零。但是，一旦堆内存用尽，JVM将会因为内存溢出（OOM）而退出。

#### Pauseless
Azul Systems
完全并发 
不分代

#### C4

Continuously Concurrent Compacting Collector 连续并发压缩收集器

Azul Systems
分代

C4 GC是一个研究性质的项目，它可能包含一些实验性的技术和算法，生产环境慎用。




### 参考
 - [CMS 学习笔记](https://blog.csdn.net/ityouknow/article/details/85826170)
 - [G1和CMS](https://blog.csdn.net/zsj777/article/details/80353464)
 - [三色标记与读写屏障](https://www.jianshu.com/p/12544c0ad5c1)
 - [GC调优指南](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/)
 - [ZGC wiki](https://wiki.openjdk.java.net/display/zgc/Main)
 - [Major GC与Full GC的区别及触发条件](https://www.zhihu.com/question/41922036/answer/93079526)

- [jvm G1 无法触发mixed gc (InitiatingHeapOccupancyPercent之坑）](https://www.jianshu.com/p/62aa6d77c8ca)





