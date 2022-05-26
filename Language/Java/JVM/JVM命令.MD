# jps（JVM Process Status Tool）

虚拟机进程状况工具，可以列出正在运行的虚拟机进程，并显示虚拟机执行主类（MainClass,main（）函数所在的类）名称以及这些进程的本地虚拟机唯一ID（Local Virtual Machine Identifier,LVMID）。

- 命令格式：`jps[options][hostid]`
- 参数：
  - `-q`：只输出LVMID，省略主类名称
  - `-m`：输出虚拟机进程启动时传给主类main()函数的参数
  - `-l`：输出主类全名，如果进程执行的时jar包则输出jar路径
  - `-v`：输出虚拟机进程启动时的jvm参数

# jstat（JVM Statistics Monitoring Tool）

虚拟机统计信息监视工具，用于监视虚拟机各种运行状态信息的命令行工具。它可以显示本地或者远程虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据，在没有GUI图形界面，只提供了纯文本控制台环境的服务器上，它将是运行期定位虚拟机性能问题的首选工具。

- 命令格式：`jstat[option vmid[interval[s|ms][count]]]`
- 对于命令格式中的VMID与LVMID需要特别说明一下：如果是本地虚拟机进程，VMID与LVMID是一致的；如果是远程虚拟机进程，那VMID的格式应当是：`[protocol：][//]lvmid[@hostname[：port]/servername]`
- 参数interval和count代表查询间隔和次数，如果省略这两个参数，说明只查询一次。

```
// 每250毫秒查询一次进程2764垃圾收集状况，一共查询20次
jstat-gc 2764 250 20
```

- 参数：主要分为3类：类装载、垃圾收集、运行 期编译状况
  - `-class`：监视类装载、卸载数量、总空间和类装载消耗的时间
  - `-gc`：监视java堆状况，包括Eden区、2个Survivor区、老年代、永久代等的容量，已用空间，GC时间合计等信息
  - `-gccapacity`：	监视内容与`-gc`相同，但输出主要关注Java堆各个区域使用到的最大、最小空间
  - `-gcutil`：监视内容与`-gc`相同，但输出主要关注已使用空间占总空间的百分百
  - `-gccause`：与`-gcutil`一样，但会额外输出上一次GC产生的原因
  - `-gcnew`：监视新生代GC的状况
  - `-gcnewcapacity`：监视内容与`-gcnew`相同，输出主要关注使用到的最大、最小空间
  - `-gcold`：监视老年代GC的状况
  - `-gcoldcapacity`：监视内容与`-gcold`相同，输出主要关注使用到的最大、最小空间
  - `-gcpermcapacity`：输出永久代使用到的最大、最小空间
  - `-compiler`：输出JIT编译过的方法、耗时等信息
  - `-printcompilation`：输出已被JIT编译的方法

# jinfo（Configuration Info for Java）

Java配置信息工具，可实时查看和调整虚拟机各项参数。

- 命令格式：`jinfo[option]pid`
- 参数：
  - `-flag`：输出指定args参数的值
  - `-flags`：不需要args参数，输出所有JVM参数的值
  - `-sysprops`：输出系统属性，等同于System.getProperties()

# jmap（Memory Map for Java）

Java内存映像工具用于生成堆转储快照（dump文件）

- 几种获得dump文件的方式
  - jmap命令
  - -XX：+HeapDumpOnOutOfMemoryError参数，可以让虚拟机在OOM异常出现之后自动生成dump文件
  - -XX：+HeapDumpOnCtrlBreak参数则可以使用[Ctrl]+[Break]键让虚拟机生成dump文件
  - 在Linux系统下通过`Kill-3`命令发送进程退出信号也能拿到dump文件
- 命令格式：`jmap[option]vmid`
- 参数：
  - `-dump`：生成堆转储快照
  - `-finalizerinfo`：显示在F-Queue队列等待Finalizer线程执行finalizer方法的对象
  - `-heap`：显示Java堆详细信息
  - `-histo`：显示堆中对象的统计信息
  - `-permstat`：以CLassLoader为统计口径显示永久代内存状态
  - `-F`：当-dump没有响应时，强制生成dump快照

# jhat（JVM Heap Analysis Tool）

与jmap搭配使用，用来分析jmap生成的堆转储快照。jhat内置了一个微型的HTTP/HTML服务器，生成dump文件的分析结果后，可以在浏览器中查看。

- 命令格式：`jhat [dumpfile]`
- 不推荐使用此命令，有2个原因：
  - 一般不会直接在服务器上分析dump文件，浪费服务器资源。
  - 分析功能简陋，推荐用专业的可视化分析工具，比如VisualVM

# jstack（Stack Trace for Java）

Java堆栈跟踪工具，用于生成虚拟机当前时刻的线程快照。

线程快照就是当前虚拟机内每一条线程正在执行的方法堆栈的集合，生成线程快照的主要目的是定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的长时间等待等都是导致线程长时间停顿的常见原因。

线程出现停顿的时候通过jstack来查看各个线程的调用堆栈，就可以知道没有响应的线程到底在后台做些什么事情，或者等待着什么资源。

- 命令格式：`jstack[option]vmid`
- 参数：
  - `-F`：当正常输出的请求不被响应时，强制输出线程堆栈
  - `-l`：除堆栈外，显示关于锁的附加信息
  - `-m`：如果调用本地方法，可显示C/C++的堆栈
- 在JDK 1.5中，`java.lang.Thread`类新增了一个`getAllStackTraces()`方法用于获取虚拟机中所有线程的StackTraceElement对象。

