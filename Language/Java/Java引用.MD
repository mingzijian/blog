Java引用
------------

### 概念介绍

 - 强引用
   不会被清理，即使面临内存溢出
   `Object object = new Object();`
 - 软引用
   内存不足时会被清理
   `SoftReference reference = new SoftReference(object); object = null;`
 - 弱引用
   会被直接清理，不管内存是否够用
   `WeakReference reference = new WeakReference(object); object = null;`
 - 虚引用
   会被直接清理，不管内存是否够用，而且通过虚引用的 get 方法不会得到对象的引用
   `PhantomReference refernce = new PhantomReference(object); object = null;`

### 应用场景

 - 强引用
   比比皆是
   `Object object = new Object();`
   
 - 软引用
   一般用来实现一些内存敏感的缓存，只要内存空间足够，对象就会保持不被回收掉，比如网页缓存、图片缓存等。

 - 弱引用
   ThreadLocalMap 中的 Entry 继承自 WeakReference 。
   
   ```java
   package java.lang;
   import ...
   public class ThreadLocal<T> {
     ...
     static class ThreadLocalMap {
       static class Entry extends WeakReference<ThreadLocal<?>> {
            /** 与此ThreadLocal相关联的value，ThreadLocal作为key */
         Object value;
   
            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
        ...
     }
     ...
   }
   ```
   
   Spring 事务传递是通过 ThreadLocal 实现的。
   ```java
   package org.springframework.transaction.support;
   import ...
   public abstract class TransactionSynchronizationManager {
     private static final Log logger = LogFactory.getLog(TransactionSynchronizationManager.class);
     private static final ThreadLocal<Map<Object, Object>> resources = 
         new NamedThreadLocal("Transactional resources");
     private static final ThreadLocal<Set<TransactionSynchronization>> synchronizations = 
         new NamedThreadLocal("Transaction synchronizations");
     private static final ThreadLocal<String> currentTransactionName = 
         new NamedThreadLocal("Current transaction name");
     private static final ThreadLocal<Boolean> currentTransactionReadOnly = 
         new NamedThreadLocal("Current transaction read-only status");
     private static final ThreadLocal<Integer> currentTransactionIsolationLevel = 
         new NamedThreadLocal("Current transaction isolation level");
     private static final ThreadLocal<Boolean> actualTransactionActive = 
         new NamedThreadLocal("Actual transaction active");
   
     public TransactionSynchronizationManager() {
     }
    ...
   }
   ```
   

 ![image-20210608092856429](https://gitee.com/mingzijian/resources/raw/master/picgo/image-20210608092856429.png)

 - 虚引用
   在使用虚引用时，必须和引用队列关联使用。在对象的垃圾回收过程中，如果GC发现一个对象还存在虚引用，则会把这个虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象内存被回收之前采取必要的行动防止被回收。
虚引用主要用来跟踪对象被垃圾回收器回收的活动。
DirectByteBuffer 是通过虚引用(Phantom Reference)来实现堆外内存的释放的。 


### 代码示例
#### 强引用
```java
public class StrongReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        StrongReferenceTest object = new StrongReferenceTest();

        System.gc();
        TimeUnit.SECONDS.sleep(1);//暂停一秒钟

        System.out.println(object == null);//false
    }
}
```

#### 软引用
```java
/**
 * java -Xms10m -Xmx10m SoftReferenceTest 
 */
public class SoftReferenceTest {

    static class HeapObject {
        byte[] bs = new byte[1024 * 1024];
    }

    public static void main(String[] args) {
        SoftReference<HeapObject> softReference = new SoftReference<>(new HeapObject());

        List<HeapObject> list = new ArrayList<>();

        while (true) {
            if (softReference.get() != null) {
                list.add(new HeapObject());
                System.out.println("list.add");
            } else {
                System.out.println("---------软引用已被回收---------");
                break;
            }
            System.gc();
        }
    }
}
```

#### 弱引用
```java
/**
 * jdk 1.8
 */
public class WeakReferenceTest {
    static class TestObject{

    }

    public static void main(String[] args) throws InterruptedException {
        WeakReference<TestObject> weakReference=new WeakReference<>(new TestObject());

        System.out.println(weakReference.get() == null);//false

        System.gc();
        TimeUnit.SECONDS.sleep(1);//暂停一秒钟

        System.out.println(weakReference.get() == null);//true
    }
}
```

```java
/**
 * java -Xms10m -Xmx10m SoftReferenceTest
 */
public class SoftReferenceQueueTest {

    static class HeapObject {
        byte[] bs = new byte[1024 * 1024];
    }

    public static void main(String[] args) {
        ReferenceQueue<HeapObject> queue=new ReferenceQueue<>();
        SoftReference<HeapObject> softReference = new SoftReference<>(new HeapObject(),queue);

        List<HeapObject> list = new ArrayList<>();

        while (true) {
            if (softReference.get() != null) {
                list.add(new HeapObject());
                System.out.println("list.add");
            } else {
                System.out.println("---------软引用已被回收---------");
                break;
            }
            System.gc();
        }
        Reference<? extends  HeapObject> pollRef = queue.poll();
        while (pollRef != null) {
            System.out.println(pollRef);
            System.out.println(pollRef.get());
            pollRef = queue.poll();
        }

    }
}
```

#### 虚引用
```java
public class PhantomReferenceTest {
    static class TestObject {

    }

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<TestObject> queue = new ReferenceQueue<>();
        PhantomReference<TestObject> phantomReference = new PhantomReference<>(new TestObject(), queue);

        System.out.println(phantomReference.get() == null);//true


    }
}
```

### 参考

 - [Reference Javadoc](https://docs.oracle.com/javase/8/docs/api/java/lang/ref/Reference.html)


