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


java.util.concurrent.ThreadPoolExecutor
```java
/**
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @param threadFactory the factory to use when the executor
     *        creates a new thread
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code corePoolSize < 0}<br>
     *         {@code keepAliveTime < 0}<br>
     *         {@code maximumPoolSize <= 0}<br>
     *         {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException if {@code workQueue}
     *         or {@code threadFactory} or {@code handler} is null
     */
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
```

### 作用
 - 复用
 - 管理
### 使用
#### 使用方式

#### 使用场景

