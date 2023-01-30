CountDownLatch
---------------------------
一种同步辅助工具，允许一个或多个线程等待其他线程中正在执行的一组操作完成。

类比：
- 门闩
- 发令枪
- 下课铃声


使用给定的`count`参数初始化`CountDownLatch`。
`countDown()`方法会将当前`count` `- 1` 。
`count`无法重置（如果需要重置计数，请考虑使用`CyclicBarrier`）。
`await()`方法会一直阻塞到`count`为`0`，之后将释放所有等待线程，任何后续的`await`调用都将立即返回。

内存一致性影响：在`count`达到`0`之前，线程中调用`countDown()` `happen-before`另一个线程中相应的`await()`。

### 源码解读
```java
package java.util.concurrent;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
/**
 * 一种同步辅助工具，允许一个或多个线程等待其他线程中正在执行的一组操作完成。
 */
public class CountDownLatch {

	/**
	 * 倒计时锁存器的同步控制。
     * 使用AQS状态表示计数。
     * 内部使用了volatile 和 CAS。
	 */
    private static final class Sync extends AbstractQueuedSynchronizer {
        ...
    }
    
    private final Sync sync;
    
    /**
	 * 使用指定的count构造CountDownLatch
	 */
    public CountDownLatch(int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.sync = new Sync(count);
    }
    
    
    /**
	 * 使当前线程进入等待状态，直到count==0
	 * 除非：线程被中断
	 */
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
    
    /**
	 * 使当前线程进入等待状态，直到count==0
	 * 除非：线程被中断 或 超过等待时间
	 */
    public boolean await(long timeout, TimeUnit unit)
        throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }
    
    /**
	 * count-- (原子)
	 */
    public void countDown() {
        sync.releaseShared(1);
    }

	/**
	 * getCount
	 */
	public long getCount() {
        return sync.getCount();
    }
    
    /**
	 * toString
	 */
    public String toString() {
        return super.toString() + "[Count = " + sync.getCount() + "]";
    }
}

```
