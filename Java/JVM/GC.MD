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

![初始标记](https://images.gitee.com/uploads/images/2020/0624/140324_d12c068e_536895.png "初始标记.png")

![并发标记](https://images.gitee.com/uploads/images/2020/0624/140432_022a15fe_536895.png "并发标记.png")

####  G1



####  ZGC



####  Shenandoah



#### Epsilon







### 参考
 - https://blog.csdn.net/ityouknow/article/details/85826170
 - https://blog.csdn.net/zsj777/article/details/80353464







