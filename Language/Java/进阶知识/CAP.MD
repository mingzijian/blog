CAP
---
#### 分布式系统的三个指标
 - Consistency （一致性）
   写操作之后的读操作，必须返回写操作之后的值。
 - Availability （可用性）
   服务端必须响应客户端的请求。
 - Partition tolerance （分区容错）
   区间通信可能失败，分区容错无法避免。

#### CAP 定理
在一个分布式系统中， Consistency（一致性）、 Availability（可用性）、Partition tolerance（分区容错性），三者不可得兼。

![CAP](https://images.gitee.com/uploads/images/2020/0617/131347_dffcb4ce_536895.png "CAP.png")

#### CAP 组合
 - C+A 
   以2阶段提交(2 phase commit)为代表的严格选举协议。当通信中断时算法不具有终止性。
 - C+P 
   以Paxos、Raft为代表的多数派选举算法。当不可用的执行过程超过半数时，算法无法得到正确结果。
 - A+P 
   以Gossip协议为代表的冲突解决协议。当网络分区存在和执行过程正确时，只能等待分区消失才保持一致性。

