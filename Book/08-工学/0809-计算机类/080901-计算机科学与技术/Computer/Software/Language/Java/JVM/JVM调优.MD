JVM调优
-----------

### 分析问题瓶颈（性能监控和分析）

无监控，不调优

#### JVM 监控工具

##### Arthas

##### JProfiler

#### JVM 监控指标

##### CPU 监控

监控 CPU 利用率，预警

监控线程 CPU 时间，找出 CPU 消耗大户

##### 内存监控

监控内存利用率，预警

监控堆内存、非堆内容使用情况，分析可能存在内存泄露的原因

##### 线程数监控

监控线程数量和状态，分析线程阻塞的原因

##### GC 监控

GC 时间监控，时间过长预警

GC 频率监控，频率过快预警



### 设置恰当的内存



### 选择合适的垃圾收集器

#### 垃圾回收算法

##### 标记清除（Mark-Sweep）

![Mark-Sweep](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/image-20240325163045324.png)

内存碎片（可用空间不连续）

可容纳最大对象（3 × 4）<= 剩余空间（4 × 6）

##### 复制（Copying）

![Copying](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/image-20240325163119746.png)

空间利用率低（备用空间）

##### 标记整理（Mark-Compact）

![Mark-Compact](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/image-20240325163102423.png)

相对耗时较高（对象挪动）

#### 分代回收
CMS
按使用、回收频率，将对象定义成不同的代。



#### 分区回收
G1



#### 常见的垃圾收集器

见[GC](GC.MD)



### 设置必要的辅助参数

#### 原则

1. 堆大于4g，使用G1，小于等于4g，使用CMS
2. 如果是cms，一般建议年轻代和年老代1:1，根据实际情况进行调整

#### 相关命令

1. /opt/jdk1.8.0_231/bin/jstat -gcutil 1 2000
2. /opt/jdk1.8.0_231/bin/jmap -heap 1

#### 相关参数

| 类型       | 参数说明                                                     |
| ---------- | ------------------------------------------------------------ |
| 通用       | -Xmx4g 最大堆                                                |
|            | -Xms4g  初始堆                                               |
|            | -Xmn2g  年轻代                                               |
|            | XX:MaxDirectMemorySize=512m 堆外内存(直接内存)               |
|            | -XX:ParallelGCThreads=2  STW期间，并行GC线程数               |
|            | -XX:ConcGCThreads=2   并发标记阶段，并行执行的线程数         |
|            |                                                              |
| GC日志相关 | -XX:+PrintGC<br/>-XX:+PrintGCDetails<br/>-XX:+PrintGCDateStamps<br/>-XX:+HeapDumpOnOutOfMemoryError<br/>-XX:HeapDumpPath=/opt/xxx/modules/heapdump.hprof <br/>-Xloggc:/opt/xxx/modules/gc.log |
|            |                                                              |
| G1         | -XX:+UnlockExperimentalVMOptions   解锁实验性参数            |
|            | -XX:+UseG1GC   使用G1垃圾回收器                              |
|            | -XX:G1NewSizePercent=40    年轻代堆大小最小百分比，默认5%    |
|            | -XX:G1MaxNewSizePercent=45    年轻代堆大小最大百分比，默认60% |
|            | -XX:InitiatingHeapOccupancyPercent=30    设置触发标记周期的 Java 堆占用率阈值。默认值是45%。这里的java堆占比指的是non_young_capacity_bytes，包括old+humongous |
|            | -XX:G1HeapRegionSize=16M   区大小，默认值将根据 heap size 算出最优解，最小值为 2Mb, 最大值为 32Mb。 |
|            | -XX:MaxGCPauseMillis=200     垃圾回收最大暂停时间，单位ms，默认200ms，太小可能导致垃圾回收不完整 |
|            | -XX:G1ReservePercent=20       为晋升失败保留20%空间，默认10%。 |
|            | -XX:+UseCodeCacheFlushing    代码缓存回收，jdk8 默认开启，不用配置 |
|            |                                                              |
| CMS        | -XX:SurvivorRatio=10    E区比S区值                           |
|            | -XX:MaxMetaspaceSize=512M    Metaspace 最大值                |
|            | -XX:MetaspaceSize=512M     Metaspace初始值                   |
|            | -XX:+UseConcMarkSweepGC  使用CMS                             |
|            | -XX:+UseCMSInitiatingOccupancyOnly 与CMSInitiatingOccupancyFraction配合使用 |
|            | -XX:CMSInitiatingOccupancyFraction=75  阈值达到75%触发FGC    |
|            | -XX:+UseCMSCompactAtFullCollection GC碎片整理                |
|            | -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses 禁止显式GC |
|            | -XX:+CMSClassUnloadingEnabled 允许栈区GC                     |
|            | -XX:+ParallelRefProcEnabled                                  |
|            | -XX:+CMSScavengeBeforeRemark                                 |
|            | -XX:CMSMaxAbortablePrecleanTime=5000                         |
|            | -XX:MaxTenuringThreshold=15                                  |



### 总结



### 参考
 - [JVM调优](https://www.cnblogs.com/hulianwangjiagoushi/p/11233263.html)
 - [JVM参数调优总结](https://www.cnblogs.com/likehua/p/3369823.html)
 - [GC调优指南-官方](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/)

