java.lang.Object
-------------------------
Object对象是类层次结构的根。每个类都由Object作为超类（父类）。所有对象（包括数组）都实现此类（Object）的方法。


### 简要代码
```java
package java.lang;
/**
 * 跟对象
 */
public class Object {
    private static native void registerNatives();
    static {
        registerNatives();
    }
    
    public final native Class<?> getClass();
    
    public native int hashCode();
    
    public boolean equals(Object obj) {
        return (this == obj);
    }
    
    protected native Object clone() throws CloneNotSupportedException;
    
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    
    public final native void notify();
    
    public final native void notifyAll();
    
    public final native void wait(long timeout) throws InterruptedException;
    
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        wait(timeout);
    }
    
    public final void wait() throws InterruptedException {
        wait(0);
    }
    
    protected void finalize() throws Throwable { }
}

```