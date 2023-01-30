Java 线程池
-----------------
### 介绍
#### 参数

 - int corePoolSize,
   核心线程数，线程池中保留的最小线程数量
 - int maximumPoolSize,
   最大线程数，线程池中允许的最大线程数量
 - long keepAliveTime,
   存活时间,当线程池中线程数量大于核心线程数，空闲线程的存活时间
 - TimeUnit unit,
   时间单位,上一参数(即:存活时间)的时间单位
 - BlockingQueue\<Runnable\> workQueue,
   工作队列，新任务被提交后，会先进入到此工作队列中，任务调度时再从队列中取出任务。
    - ArrayBlockingQueue
      基于数组的有界阻塞队列,FIFO.
    - LinkedBlockingQueue
      基于链表的无界(Interger.MAX)阻塞队列(因为该队列是无界的,所以使用该队列时,最大线程数参数无法产生作用)
    - SynchronousQuene
      一个不缓存任务的同步阻塞队列.该队列没有任何容量(一个空队列,并且不允许出现null元素),其中每个插入操作必须等待另一个线程执行相应的删除操作，反之亦然.
    - PriorityBlockingQueue
      基于优先级的无界(逻辑上无界)阻塞队列.优先级通过参数Comparator实现,不允许出现不可比较的对象。
 - ThreadFactory threadFactory,
   线程工厂,创建新线程时所使用的工厂
 - RejectedExecutionHandler handler
   拒绝策略,当工作队列已满,并且线程池中的线程数量达到最大,此时,有新任务提交进来,处理新任务的策略。
   
   -  AbortPolicy
    抛出一个RejectedExecutionException,丢弃任务
   ```java
       /**
         * A handler for rejected tasks that throws a
         * {@code RejectedExecutionException}.
         */
        public static class AbortPolicy implements RejectedExecutionHandler {
            /**
             * Creates an {@code AbortPolicy}.
             */
         public AbortPolicy() { }
   
            /**
             * Always throws RejectedExecutionException.
             *
             * @param r the runnable task requested to be executed
             * @param e the executor attempting to execute this task
             * @throws RejectedExecutionException always
             */
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                throw new RejectedExecutionException("Task " + r.toString() +
                                                     " rejected from " +
                                                     e.toString());
            }
        }
   ```
   
   -  CallerRunsPolicy
      直接在调用方线程中执行任务.如果执行器已关闭,丢弃任务
      ```java
        /**
         * A handler for rejected tasks that runs the rejected task
         * directly in the calling thread of the {@code execute} method,
         * unless the executor has been shut down, in which case the task
         * is discarded.
         */
        public static class CallerRunsPolicy implements RejectedExecutionHandler {
            /**
             * Creates a {@code CallerRunsPolicy}.
             */
         public CallerRunsPolicy() { }
   
            /**
             * Executes task r in the caller's thread, unless the executor
             * has been shut down, in which case the task is discarded.
             *
             * @param r the runnable task requested to be executed
             * @param e the executor attempting to execute this task
             */
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                if (!e.isShutdown()) {
                    r.run();
                }
            }
        }
     ```
     
   -  DiscardOldestPolicy
      抛弃队列中最老的任务,然后尝试将当前任务加入队列.如果执行器已关闭,丢弃任务
      
      ```java
        /**
         * A handler for rejected tasks that discards the oldest unhandled
         * request and then retries {@code execute}, unless the executor
         * is shut down, in which case the task is discarded.
         */
        public static class DiscardOldestPolicy implements RejectedExecutionHandler {
            /**
             * Creates a {@code DiscardOldestPolicy} for the given executor.
          */
         public DiscardOldestPolicy() { }
      
            /**
             * Obtains and ignores the next task that the executor
             * would otherwise execute, if one is immediately available,
             * and then retries execution of task r, unless the executor
             * is shut down, in which case task r is instead discarded.
             *
             * @param r the runnable task requested to be executed
             * @param e the executor attempting to execute this task
             */
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                if (!e.isShutdown()) {
                    e.getQueue().poll();
                    e.execute(r);
                }
            }
       }
     ```
     
   -  DiscardPolicy
      默默地丢弃任务.
      ```java
        /**
         * A handler for rejected tasks that silently discards the
         * rejected task.
         */
        public static class DiscardPolicy implements RejectedExecutionHandler {
            /**
             * Creates a {@code DiscardPolicy}.
          */
            public DiscardPolicy() { }
   
            /**
             * Does nothing, which has the effect of discarding task r.
             *
             * @param r the runnable task requested to be executed
             * @param e the executor attempting to execute this task
             */
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            }
        }
     ```




```java
package java.util.concurrent;
import ...
public class ThreadPoolExecutor extends AbstractExecutorService {
    ...
    /**
     * 使用给定的初始参数构造一个新的ThreadPoolExecutor。
     */
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
                              ...
    }
    ...
}
```

### 作用
 - 复用
 - 管理

### 使用
#### 使用方式

##### 工具类  java.util.concurrent.Executors

- newFixedThreadPool 新建固定大小的线程池
```java
    /**
     * Creates a thread pool that reuses a fixed number of threads
     * operating off a shared unbounded queue, using the provided
     * ThreadFactory to create new threads when needed.  At any point,
     * at most {@code nThreads} threads will be active processing
     * tasks.  If additional tasks are submitted when all threads are
     * active, they will wait in the queue until a thread is
     * available.  If any thread terminates due to a failure during
     * execution prior to shutdown, a new one will take its place if
     * needed to execute subsequent tasks.  The threads in the pool will
     * exist until it is explicitly {@link ExecutorService#shutdown
     * shutdown}.
     *
     * @param nThreads the number of threads in the pool
     * @param threadFactory the factory to use when creating new threads
     * @return the newly created thread pool
     * @throws NullPointerException if threadFactory is null
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>(),
                                      threadFactory);
    }

```

- newWorkStealingPool 新建具有抢占式操作的线程池
```java
    /**
     * Creates a thread pool that maintains enough threads to support
     * the given parallelism level, and may use multiple queues to
     * reduce contention. The parallelism level corresponds to the
     * maximum number of threads actively engaged in, or available to
     * engage in, task processing. The actual number of threads may
     * grow and shrink dynamically. A work-stealing pool makes no
     * guarantees about the order in which submitted tasks are
     * executed.
     *
     * @param parallelism the targeted parallelism level
     * @return the newly created thread pool
     * @throws IllegalArgumentException if {@code parallelism <= 0}
     * @since 1.8
     */
    public static ExecutorService newWorkStealingPool(int parallelism) {
        return new ForkJoinPool
            (parallelism,
             ForkJoinPool.defaultForkJoinWorkerThreadFactory,
             null, true);
    }


```

- newSingleThreadExecutor 新建单线程执行器(线程池中只有一个线程,串行处理任务)
```java
    /**
     * Creates an Executor that uses a single worker thread operating
     * off an unbounded queue. (Note however that if this single
     * thread terminates due to a failure during execution prior to
     * shutdown, a new one will take its place if needed to execute
     * subsequent tasks.)  Tasks are guaranteed to execute
     * sequentially, and no more than one task will be active at any
     * given time. Unlike the otherwise equivalent
     * {@code newFixedThreadPool(1)} the returned executor is
     * guaranteed not to be reconfigurable to use additional threads.
     *
     * @return the newly created single-threaded Executor
     */
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
```

- newCachedThreadPool 新建缓存线程池(线程数量不固定,自动增长)
  - 核心线程数:0
  - 最大线程数:Integer.MAX_VALUE
  - 空闲线程存活时间:0秒
```java
    /**
     * Creates a thread pool that creates new threads as needed, but
     * will reuse previously constructed threads when they are
     * available.  These pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     * Calls to {@code execute} will reuse previously constructed
     * threads if available. If no existing thread is available, a new
     * thread will be created and added to the pool. Threads that have
     * not been used for sixty seconds are terminated and removed from
     * the cache. Thus, a pool that remains idle for long enough will
     * not consume any resources. Note that pools with similar
     * properties but different details (for example, timeout parameters)
     * may be created using {@link ThreadPoolExecutor} constructors.
     *
     * @return the newly created thread pool
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }


```

- newScheduledThreadPool 新建固定大小的可调度线程池(延时或定时执行任务)
```java
    /**
     * Creates a single-threaded executor that can schedule commands
     * to run after a given delay, or to execute periodically.
     * (Note however that if this single
     * thread terminates due to a failure during execution prior to
     * shutdown, a new one will take its place if needed to execute
     * subsequent tasks.)  Tasks are guaranteed to execute
     * sequentially, and no more than one task will be active at any
     * given time. Unlike the otherwise equivalent
     * {@code newScheduledThreadPool(1)} the returned executor is
     * guaranteed not to be reconfigurable to use additional threads.
     * @return the newly created scheduled executor
     */
    public static ScheduledExecutorService newSingleThreadScheduledExecutor() {
        return new DelegatedScheduledExecutorService
            (new ScheduledThreadPoolExecutor(1));
    }


```


#### 使用场景

### 合适的线程数
通用公式：CPU 核心数 * (1+ IO 耗时/CPU 耗时)

无监控（测试），不调优。

#### CPU 密集型
CPU 密集型任务:比如加密、解密、压缩、计算等任务。

最佳线程数 = CPU 核心数的 1~2 倍

CPU密集型任务，会密集占用CPU时间片，如果线程数过多（比如是 CPU 核心数的 4 倍），线程去频繁抢占CPU资源，导致大量的不必要的CPU上下文切换，从而造成性能下降。

#### IO 密集型
IO 密集型任务:比如数据库、文件的读写，网络通信等任务。

最佳线程数 = CPU 核心数 * (1+ IO 耗时/CPU 耗时)

IO 密集型任务，任务的大多数时间都耗费在IO上，由于IO读写速度远慢于CPU速度，如果线程数过少（比如是 CPU 核心数的 一半），线程正在处理IO耗时操作，不与要CPU参与计算，就会导致CPU空闲，无疑是资源浪费。