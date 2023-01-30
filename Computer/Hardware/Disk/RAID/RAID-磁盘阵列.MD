RAID 磁盘阵列
---------------------
RAID （ Redundant Array of Independent Disks ）即独立磁盘冗余阵列，简称为「磁盘阵列」

### 常见方案
- RAID0
- RAID1
- RAID3
- RAID5
- RAID6
- RAID10

#### RAID0
 ![RAID0](https://images.gitee.com/uploads/images/2021/0224/133413_51f26fff_536895.png "RAID0.png")

#### RAID1
 ![RAID1](https://images.gitee.com/uploads/images/2021/0224/133436_7dadcbdd_536895.png "RAID1.png")

#### RAID3
 ![RAID3](https://images.gitee.com/uploads/images/2021/0224/133504_11f83840_536895.png "RAID3.png")

#### RAID5
 ![RAID5](https://images.gitee.com/uploads/images/2021/0224/133534_93ac21a3_536895.png "RAID5.png")

#### RAID6
 ![RAID6](https://images.gitee.com/uploads/images/2021/0224/133549_70292b19_536895.png "RAID6.png")

#### RAID10
 ![RAID10](https://images.gitee.com/uploads/images/2021/0224/133627_f24e952f_536895.png "RAID10.png")

#### 主流 RAID 等级技术对比

| **RAID** **等级** | **RAID0** | **RAID1** | **RAID3**        | **RAID5**        | **RAID6**        | **RAID10** |
| ----------------- | --------- | --------- | ---------------- | ---------------- | ---------------- | ---------- |
| **别名**          | 条带      | 镜像      | 专用奇偶校验条带 | 分布奇偶校验条带 | 双重奇偶校验条带 | 镜像加条带 |
| **容错性**        | 无        | 有        | 有               | 有               | 有               | 有         |
| **冗余类型**      | 无        | 有        | 有               | 有               | 有               | 有         |
| **热备份选择**    | 无        | 有        | 有               | 有               | 有               | 有         |
| **读性能**        | 高        | 低        | 高               | 高               | 高               | 高         |
| **随机写性能**    | 高        | 低        | 低               | 一般             | 低               | 一般       |
| **连续写性能**    | 高        | 低        | 低               | 低               | 低               | 一般       |
| **需要磁盘数**    | n≥1       | 2n (n≥1)  | n≥3              | n≥3              | n≥4              | 2n(n≥2)≥4  |
| **可用容量**      | 全部      | 50%       | (n-1)/n          | (n-1)/n          | (n-2)/n          | 50%        |

#### 拓展
##### JBOD (磁盘串)
JBOD （ Just a Bunch Of Disks ）不是标准的 RAID 等级，它通常用来表示一个没有控制软件提供协调控制的磁盘集合。
JBOD 将多个物理磁盘串联起来，提供一个巨大的逻辑磁盘。
 ![JBOD](https://images.gitee.com/uploads/images/2021/0224/133851_cce8cc50_536895.png "JBOD.png")

### 实现方式
#### 硬RAID
硬 RAID 拥有自己的 RAID 控制处理与 I/O 处理芯片，甚至还有阵列缓冲，对 CPU 的占用率和整体性能是三类实现中最优的，但实现成本也最高的。
硬 RAID 通常都支持热交换技术，在系统运行下更换故障磁盘。
#### 软RAID
软 RAID 没有专用的控制芯片和 I/O 芯片，完全由操作系统和 CPU 来实现所的 RAID 的功能。
#### 混合RAID (软+硬)
软 RAID 性能欠佳，而且不能保护系统分区，因此很难应用于桌面系统。
而硬 RAID 成本非常昂贵，不同 RAID 相互独立，不具互操作性。
因此，人们采取软件与硬件结合的方式来实现 RAID ，从而获得在性能和成本上的一个折中，即较高的性价比。
### 参考
 - [RAID磁盘阵列是什么（一看就懂）--慕课网](http://www.imooc.com/article/264962)
 - [RAID技术全解图解](https://blog.csdn.net/ensp1/article/details/81318135)