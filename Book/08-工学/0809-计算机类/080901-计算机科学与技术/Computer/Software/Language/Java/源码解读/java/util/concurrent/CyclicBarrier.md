CyclicBarrier （循环屏障）
--------------------------------------
一种同步辅助工具，允许一组线程全部等待对方到达一个公共屏障点。



### CountDownLatch & CyclicBarrier

CyclicBarrier 是 CountDownLatch 的增强版，同时CyclicBarrier 也更加复杂。

| CountDownLatch                                       | CyclicBarrier                                              |
| ---------------------------------------------------- | ---------------------------------------------------------- |
| 减计数（构造时指定一个起始数count）                  | 加计数（构造时指定一个终点数parties）                      |
| 计数为0时释放所有等待线程（一次性，不可重置）        | 计数为parties时释放所有等待线程（计数重置为0，可重复使用） |
| 调用await只进入等待，不影响计数；调用countDown计数-1 | 调用await计数+1，若+1后计数不为parties，则线程进入等待     |
| 基于AQS                                              | 基于ReentranLock（ReentranLock基于AQS）                    |



### 源码解读
```java
package java.util.concurrent;
import ...
public class CyclicBarrier {
    ...
    private static class Generation {
        boolean broken = false;
    }
    
    /** 站点屏障入口防护锁 */
    private final ReentrantLock lock = new ReentrantLock();
    /** 到达屏障条件 */
    private final Condition trip = lock.newCondition();
    /** 参与线程数 */
    private final int parties;
    /** 到达屏障点时执行的命令 */
    private final Runnable barrierCommand;
    /** 当前站点 */
    private Generation generation = new Generation();

    /**
     * 仍在等待（尚未到达屏障点）的线程数
     */
    private int count;
    
    ...
    
    /** 主屏障代码，涵盖各种策略 (--count) 使用ReentrantLock保证原子性 */
    private int dowait(boolean timed, long nanos)
        throws InterruptedException, BrokenBarrierException,
               TimeoutException {
        ...           
    }
    
    public CyclicBarrier(int parties, Runnable barrierAction) {
        ...
    }
    
    public CyclicBarrier(int parties) {
        this(parties, null);
    }
    
    public int getParties() {
        return parties;
    }
    
    /** 进入等待状态，直到所有参与线程在此屏障上调用await */
    public int await() throws InterruptedException, BrokenBarrierException {
        ...
    }
    
    /** 进入等待状态，直到所有参与线程在此屏障上调用await，或者等待超时。 */
    public int await(long timeout, TimeUnit unit)
        throws InterruptedException,
               BrokenBarrierException,
               TimeoutException {
        return dowait(true, unit.toNanos(timeout));
    }
    
    /** 重置屏障状态（下一站点）并唤醒所有线程。只在持有锁时调用。 (count = parties) */
    private void nextGeneration() {
        ...
    }
    
    /** 将当前的屏障状态设置为“已破坏”，并唤醒所有线程。只在持有锁时调用。 (count = parties) */
    private void breakBarrier() {
        ...
    }
    
    /** 返回：屏障是否处于损坏状态 */
    public boolean isBroken() {
        ...
    }
    
    /** 将屏障重置为初始状态 */
    public void reset() {
        ...
    }
    
    /** 返回：当前被阻塞等待的线程数 (parties - count) */
    public int getNumberWaiting() {
        ...
    }
    ...
}
```

