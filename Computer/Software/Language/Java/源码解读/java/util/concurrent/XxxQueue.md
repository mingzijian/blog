Queue 队列
-----------------

ConcurrentLinkedQueue
BlockingQueue
└─ArrayBlockingQueue
└─ BlockingDeque
  └─ ConcurrentLinkedDeque
  └─ LinkedBlockingDeque
└─DelayQueue
└─LinkedBlockingQueue
└─PriorityBlockingQueue
└─SynchronousQueue
└─TransferQueue
  └─LinkedTransferQueue

### ConcurrentLinkedQueue
基于链表的无界线程安全队列

### BlockingQueue
阻塞队列

#### ArrayBlockingQueue
基于数组的有界阻塞队列

#### BlockingDeque
双向阻塞队列

##### ConcurrentLinkedDeque
基于链表的无界并发双向队列

##### LinkedBlockingDeque
基于链表的可选有界双向队列

#### DelayQueue
延时无界阻塞队列

#### LinkedBlockingQueue
基于链表的可选有界阻塞队列

#### PriorityBlockingQueue
优先无界阻塞队列。
它使用与PriorityQueue类相同的排序规则并提供阻塞检索操作。

#### SynchronousQueue
同步队列。
没有任何内部容量，其中每个插入操作都必须等待另一个线程执行相应的删除操作，反之亦然。

#### TransferQueue
传输队列。
其中生产者可以等待消费者接收元素。

##### LinkedTransferQueue
基于链表的无界传输队列