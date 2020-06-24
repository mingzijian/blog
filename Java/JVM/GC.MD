GC

----

### GC 概念



### GC 垃圾收集算法

- 标记-清除算法（Mark-Sweep）
<pre>
标记内存中将要回收的数据,直接清除
</pre>

- 复制算法(Copying)
  <pre>
  将一片内存中的数据直接复制到另一块内存中,然后清理掉之前的内存区域
  </pre>

- 标记-整理算法（Mark-Compact）
  <pre>
  将内存中的数据,标记集中移动到内存的一边,进行清理
  </pre>

- 分代收集算法
  <pre>
  注,这个算法是物理和逻辑上将内存堆划分年轻代,老年代,根据不同代中的对象清除时候使用什么算法来提出的一种算法
  </pre>
  
  

### GC 垃圾收集器

####  Serial

![Serial](https://images.gitee.com/uploads/images/2020/0624/135638_29677cda_536895.png "Serial.png")

####   Serial Old



####  ParNew
![ParNew](https://images.gitee.com/uploads/images/2020/0624/141016_6e3be987_536895.png "ParNew.png")


#### Parallel Old



####  Parallel Scavenge



#### CMS
    1）初始标记(STW)
    
    2）并发标记
    
    3）重新标记(STW)
    
    4）并发清除

初始标记
![初始标记](https://images.gitee.com/uploads/images/2020/0624/140324_d12c068e_536895.png "初始标记.png")
并发标记
![并发标记](https://images.gitee.com/uploads/images/2020/0624/140432_022a15fe_536895.png "并发标记.png")
三色标记
![三色标记](https://images.gitee.com/uploads/images/2020/0624/163629_9ec7bc2e_536895.png "三色标记.png")

##### Incremental Update write barrier
Incremental Update 算法判断如果一个白色的对象由一个黑色的对象引用，即目的，如三色标记图，D的引用由B-->A,A是目的地址，所以cms 的Incremental Update算法是从目标入手解决的，这是和SATB的第一个区别，发现这种情况时，也是通过write barrier写屏障技术，把黑色的对象重新标记为灰色，让collector 重新来扫描，活着通过mod-union table 来标记，cms 就是这样实现的，这是第二个区别，做法不一样，也是上面讲的防止第一种情况发生。



####  G1
    1、初始标记(STW)
    2、并发标记
    3、最终标记(STW)
    4、筛选回收

与其它收集器相比，G1变化较大的是它将整个Java堆划分为多个大小相等的独立区域（Region），
虽然还保留了新生代和来年代的概念，但新生代和老年代不再是物理隔离的了它们都是一部分Region（不需要连续）的集合。
同时，为了避免全堆扫描，G1使用了Remembered Set来管理相关的对象引用信息。
当进行内存回收时，在GC根节点的枚举范围中加入Remembered Set即可保证不对全堆扫描也不会有遗漏了。
##### SATB (Snapshot-AT-Beginning)
    SATB 算法认为开始标记的都认为是活的对象，如三色标记图所示,引用B到D 的引用改为B到C时，通过write barrier写屏障技术，会把B到D 的引用推到gc 遍历执行的堆栈上，保证还可以遍历到D对象，相对于d来说，引用从B-->A,SATB 是从源入手解决的，即上面说的第2种情况，
    这也能理解为啥叫satb 了，即认为开始时所有能遍历到的对象都是需要标记的，即都认为是活的。如果我吧b = null,那么d 久是垃圾了， satb算法也还是会把D最终标记为黑色，导致D 在本轮gc 不能回收，成了浮动垃圾。


####  ZGC



####  Shenandoah



#### Epsilon







### 参考
 - https://blog.csdn.net/ityouknow/article/details/85826170
 - https://blog.csdn.net/zsj777/article/details/80353464







