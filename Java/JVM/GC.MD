GC

----

### GC 概念



### GC 垃圾收集算法

- 标记-清除算法（Mark-Sweep）
  
<pre>
一块内存中的,将要回收的数据,标记 ,直接清除
</pre>

- 复制算法(Copying)
  
  <pre>
  将一片内存 中的数据直接复制到另一块内存中,然后清理掉之前的内存区域
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



####   Serial Old



####  ParNew



#### Parallel Old



####  Parallel Scavenge



#### CMS



####  G1



####  ZGC



####  Shenandoah



#### Epsilon















