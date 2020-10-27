AtomicBoolean
------------------------
一个可以原子更新的对象引用。

简化源码
```java
package java.util.concurrent.atomic;
...
public class AtomicReference<V> implements java.io.Serializable {
	...
	private volatile V value;
	...
	public final V get() {
        return value;
    }
	...
	public final void set(V newValue) {
        value = newValue;
    }
	...
}

```

AtomicReferenceArray

AtomicReferenceFieldUpdater

AtomicStampedReference