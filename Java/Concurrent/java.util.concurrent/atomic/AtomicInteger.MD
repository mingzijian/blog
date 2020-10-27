AtomicInteger
----------------------
可以原子更新的整型值。


AtomicInteger
简化源码
```java
package java.util.concurrent.atomic;
...
public class AtomicInteger extends Number implements java.io.Serializable {
	...
	private volatile int value;
	...
	public final int get() {
        return value;
    }
    ...
    public final void set(int newValue) {
        value = newValue;
    }
    ...
}
```

AtomicIntegerArray
简化源码
```java
package java.util.concurrent.atomic;
...
public class AtomicBoolean implements java.io.Serializable {
	...
	private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final int base = unsafe.arrayBaseOffset(int[].class);
    private static final int shift;
	private final int[] array;
	
	static {
        int scale = unsafe.arrayIndexScale(int[].class);
        if ((scale & (scale - 1)) != 0)
            throw new Error("data type scale not a power of two");
        shift = 31 - Integer.numberOfLeadingZeros(scale);
    }
	...
	public final int length() {
        return array.length;
    }
	...
	public final int get(int i) {
        return getRaw(checkedByteOffset(i));
    }
    ...
    private int getRaw(long offset) {
        return unsafe.getIntVolatile(array, offset);
    }
    ...
    public final void set(int i, int newValue) {
        unsafe.putIntVolatile(array, checkedByteOffset(i), newValue);
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

AtomicIntegerFieldUpdater
简化源码
```java
package java.util.concurrent.atomic;
...
public abstract class AtomicIntegerFieldUpdater<T> {
    ...
	public abstract void set(T obj, int newValue);
	...
	public abstract int get(T obj);
	...
	
    private static final class AtomicIntegerFieldUpdaterImpl<T>
        extends AtomicIntegerFieldUpdater<T> {
        private static final sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();
        private final long offset;
        private final Class<?> cclass;
        private final Class<T> tclass;
        ...
        private final void accessCheck(T obj) {
            if (!cclass.isInstance(obj))
                throwAccessCheckException(obj);
        }
        ...
        public final void set(T obj, int newValue) {
            accessCheck(obj);
            U.putIntVolatile(obj, offset, newValue);
        }
        ...
        public final int get(T obj) {
            accessCheck(obj);
            return U.getIntVolatile(obj, offset);
        }
        ...
    }
}
```

