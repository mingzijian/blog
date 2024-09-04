Condition (条件)
-------------------------
Condition将对象监视器方法（wait、notify和notifyAll）分解为不同的对象，通过将它们与任意锁实现结合使用，为每个对象提供多个等待集的效果。当锁代替同步方法和语句的使用时，条件代替对象监视器方法的使用。
使用Condition对同步线程进行分组。

简化源码
```java
package java.util.concurrent.locks;
...
public interface Condition {
	...
	/**
	 * 使当前线程进入等待（wait）状态,直到被唤醒（signalled）或中断（interrupted）
	 */
	void await() throws InterruptedException;
	/**
	 * 使当前线程进入等待（wait）状态,直到被唤醒（signalled）或中断（interrupted），或指定的等待时间过去
	 * 返回值：如果从方法返回之前等待时间已过，则为false，否则为true
	 */
	boolean await(long time, TimeUnit unit) throws InterruptedException;
	/**
	 * 使当前线程进入等待（wait）状态,直到被唤醒（signalled）或中断（interrupted），或指定的等待时间过去
	 * 参数：
	 *  nanosTimeout 方法消耗的预估时间
	 * 返回值：剩余时间(nanosTimeout-方法消耗的实际时间)。如果从方法返回之前等待时间已过，则为<=0，否则为>0 
	 */
	long awaitNanos(long nanosTimeout) throws InterruptedException;
	/**
	 * 使当前线程进入等待（wait）状态,直到被唤醒（signalled）
	 */
	void awaitUninterruptibly();
	/**
	 * 使当前线程进入等待（wait）状态,直到被唤醒（signalled）或中断（interrupted），或指定的截止时间过去
	 */
	boolean awaitUntil(Date deadline) throws InterruptedException;
	/**
	 * 唤醒一个等待的线程
	 */
	void signal();
	/**
	 * 唤醒所有等待的线程
	 */
	void signalAll();
	...
}
```