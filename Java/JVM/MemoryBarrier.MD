内存屏障 Memory Barrier
--------------------------------------

### 硬件层的内存屏障
#### 读屏障 Load Barrier
读屏障，禁止load操作重排序。
屏障前的load操作不能被重排序到屏障后，屏障后的load操作不能被重排序到屏障前

Store：将处理器缓存的数据刷新到内存中。

#### 写屏障 Store Barrier
写屏障，禁止store操作重排序。
屏障前的store操作不能被重排序到屏障后，屏障后的store操作不能被重排序到屏障前

Load：将内存存储的数据拷贝到处理器的缓存中。

### JVM内存屏障：

| 屏障类型            | 指令示例                 | 说明                                                         |
| :------------------ | :----------------------- | :----------------------------------------------------------- |
| LoadLoad Barriers   | Load1;LoadLoad;Load2     | 该屏障确保Load1数据的装载先于Load2及其后所有装载指令的的操作 |
| StoreStore Barriers | Store1;StoreStore;Store2 | 该屏障确保Store1立刻刷新数据到内存(使其对其他处理器可见)该操作先于Store2及其后所有存储指令的操作 |
| LoadStore Barriers  | Load1;LoadStore;Store2   | 确保Load1的数据装载先于Store2及其后所有的存储指令刷新数据到内存的操作 |
| StoreLoad Barriers  | Store1;StoreLoad;Load2   | 该屏障确保Store1立刻刷新数据到内存的操作先于Load2及其后所有装载装载指令的操作。<br>它会使该屏障之前的所有内存访问指令(存储指令和访问指令)完成之后,才执行该屏障之后的内存访问指令 |



