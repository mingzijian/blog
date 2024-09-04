AtomicBoolean
------------------------
一个可以原子更新的布尔值。

简化源码
```java
package java.util.concurrent.atomic;
...
public class AtomicBoolean implements java.io.Serializable {
	...
	private volatile int value;
	...
	public final boolean get() {
        return value != 0;
    }
    ...
    public final void set(boolean newValue) {
        value = newValue ? 1 : 0;
    }
    ...
}
```