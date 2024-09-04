AtomicLong
----------------------
可以原子更新的长整型值。

AtomicLong
简化源码
```java
package java.util.concurrent.atomic;
...
public class AtomicLong extends Number implements java.io.Serializable {
	...
	private volatile long value;
	...
	public final long get() {
        return value;
    }
	...
	public final void set(long newValue) {
        value = newValue;
    }
	...
}
```

AtomicLongArray
简化源码
```java
package java.util.concurrent.atomic;
...
public class AtomicLongArray implements java.io.Serializable {
	...
	private static final Unsafe unsafe = Unsafe.getUnsafe();
	private static final int base = unsafe.arrayBaseOffset(long[].class);
    private static final int shift;
	private final long[] array;
	...
	public final long get(int i) {
        return getRaw(checkedByteOffset(i));
    }
	...
	private long getRaw(long offset) {
        return unsafe.getLongVolatile(array, offset);
    }
	...
	public final void set(int i, long newValue) {
        unsafe.putLongVolatile(array, checkedByteOffset(i), newValue);
    }
	...
	private long checkedByteOffset(int i) {
        if (i < 0 || i >= array.length)
            throw new IndexOutOfBoundsException("index " + i);

        return byteOffset(i);
    }
	...
	private static long byteOffset(int i) {
        return ((long) i << shift) + base;
    }
	...
}
```

AtomicLongFieldUpdater
简化源码
```java
package java.util.concurrent.atomic;
...
public abstract class AtomicLongFieldUpdater<T> {
	...
	public abstract long get(T obj);
	...
	public abstract void set(T obj, long newValue);
	...
	private static final class LockedUpdater<T> extends AtomicLongFieldUpdater<T> {
		...
		public final void set(T obj, long newValue) {
            accessCheck(obj);
            synchronized (this) {
                U.putLong(obj, offset, newValue);
            }
        }
		...
		public final long get(T obj) {
            accessCheck(obj);
            synchronized (this) {
                return U.getLong(obj, offset);
            }
        }
		...
	}
	...
}
```
