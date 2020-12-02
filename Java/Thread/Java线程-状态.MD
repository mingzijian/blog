Java线程-状态
-------------
#### Java线程的6种状态

 - NEW 新建
    实现Runnable接口和继承Thread可以得到一个线程类，new一个实例出来，但还没有调用start()方法，线程就进入了初始状态。
  ```java
  Thread thread = new Thread();
  ```

 - RUNNABLE 运行
   Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。线程只能从就绪状态进入到运行状态。
   - 就绪（ready）
    1. 就绪状态只是说你资格运行，调度程序没有挑选到你，你就永远是就绪状态。
    2. 调用线程的`start()`方法，此线程进入就绪状态。
    3. 当前线程`sleep()`方法结束，其他线程`join()`结束，等待用户输入完毕，某个线程拿到对象锁，这些线程也将进入就绪状态。
    4. 当前线程时间片用完了，调用当前线程的`yield()`方法，当前线程进入就绪状态。
    5. 锁池里的线程拿到对象锁后，进入就绪状态。
   - 运行中（running）
     线程调度程序从已就绪的线程池中选择一个线程作为当前线程时线程所处的状态。这也是线程进入运行状态的唯一一种方式。
 - BLOCKED 阻塞
   阻塞状态是线程阻塞在进入`synchronized`关键字修饰的方法或代码块(获取锁)时的状态。
   阻塞的三种情况:
   - 等待阻塞
     通过调用线程的`wait()`方法，让线程等待某工作的完成。
   - 同步阻塞
     线程在获取`synchronized`同步锁失败（因为锁被其他线程占用），它会进入同步阻塞状态。
   - 其他阻塞
     通过调用线程的`sleep()`或`join()`或发出了 I/O 请求时，线程会进入到阻塞状态。当`sleep()`状态超时、`join()`等待线程终止或超时、或者 I/O 处理完毕时，线程重新转入就绪状态。
 - WAITING 等待
   处于等待状态的线程不会被分配 CPU 执行时间，它们要等待被显式地唤醒，否则会处于无限期等待的状态。
 - TIMED_WAITING 超时等待
   处于超时等待状态的线程不会被分配 CPU 执行时间，不过无须无限期等待被其他线程显示地唤醒，在达到一定时间后它们会自动唤醒。
 - TERMINATED 终止
   终止状态表示该线程已经执行完毕。

![Java线程的6种状态](https://images.gitee.com/uploads/images/2020/1202/151049_7908e7d9_536895.png "Java线程的6种状态.png")

#### java.lang.Thread 部分源码参考
```java 
    /**
     * A thread state.  A thread can be in one of the following states:
     * <ul>
     * <li>{@link #NEW}<br>
     *     A thread that has not yet started is in this state.
     *     </li>
     * <li>{@link #RUNNABLE}<br>
     *     A thread executing in the Java virtual machine is in this state.
     *     </li>
     * <li>{@link #BLOCKED}<br>
     *     A thread that is blocked waiting for a monitor lock
     *     is in this state.
     *     </li>
     * <li>{@link #WAITING}<br>
     *     A thread that is waiting indefinitely for another thread to
     *     perform a particular action is in this state.
     *     </li>
     * <li>{@link #TIMED_WAITING}<br>
     *     A thread that is waiting for another thread to perform an action
     *     for up to a specified waiting time is in this state.
     *     </li>
     * <li>{@link #TERMINATED}<br>
     *     A thread that has exited is in this state.
     *     </li>
     * </ul>
     *
     * <p>
     * A thread can be in only one state at a given point in time.
     * These states are virtual machine states which do not reflect
     * any operating system thread states.
     *
     * @since   1.5
     * @see #getState
     */
    public enum State {
        /**
         * Thread state for a thread which has not yet started.
         */
        NEW,

        /**
         * Thread state for a runnable thread.  A thread in the runnable
         * state is executing in the Java virtual machine but it may
         * be waiting for other resources from the operating system
         * such as processor.
         */
        RUNNABLE,

        /**
         * Thread state for a thread blocked waiting for a monitor lock.
         * A thread in the blocked state is waiting for a monitor lock
         * to enter a synchronized block/method or
         * reenter a synchronized block/method after calling
         * {@link Object#wait() Object.wait}.
         */
        BLOCKED,

        /**
         * Thread state for a waiting thread.
         * A thread is in the waiting state due to calling one of the
         * following methods:
         * <ul>
         *   <li>{@link Object#wait() Object.wait} with no timeout</li>
         *   <li>{@link #join() Thread.join} with no timeout</li>
         *   <li>{@link LockSupport#park() LockSupport.park}</li>
         * </ul>
         *
         * <p>A thread in the waiting state is waiting for another thread to
         * perform a particular action.
         *
         * For example, a thread that has called <tt>Object.wait()</tt>
         * on an object is waiting for another thread to call
         * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
         * that object. A thread that has called <tt>Thread.join()</tt>
         * is waiting for a specified thread to terminate.
         */
        WAITING,

        /**
         * Thread state for a waiting thread with a specified waiting time.
         * A thread is in the timed waiting state due to calling one of
         * the following methods with a specified positive waiting time:
         * <ul>
         *   <li>{@link #sleep Thread.sleep}</li>
         *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
         *   <li>{@link #join(long) Thread.join} with timeout</li>
         *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
         *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
         * </ul>
         */
        TIMED_WAITING,

        /**
         * Thread state for a terminated thread.
         * The thread has completed execution.
         */
        TERMINATED;
    }
```