volatile 修饰符
----------------------

volatile 修饰的成员变量在每次被线程访问时，都强制从共享内存中重新读取该成员变量的值。而且，当成员变量发生变化时，会强制线程将变化值回写到共享内存。
这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。

`volatile` 保证了`可见性`和`有序性`，但是不保证`原子性`。		

### 多线程编程中的三个核心概念
#### 原子性
一个操作（有可能包含有多个子操作）要么全部执行（生效），要么全部都不执行（都不生效）。
#### 可见性
当多个线程并发访问共享变量时，一个线程对共享变量的修改，其它线程能够立即看到。
#### 有序性
程序执行的顺序按照代码的先后顺序执行。
指令重排序，虚拟机在把代码编译为指令后执行，出于优化的目的，在保证结果不变的情况下，可能会调整指令的执行顺序。

### 应用场景
#### 双重检查
```java
public class Singleton { 
  private volatile Singleton instance = null; 
  public Singleton getInstance() { 
    if (instance == null) { 
      synchronized(this) { 
        if (instance == null) { 
          instance = new Singleton(); 
        } 
      } 
    } 
    return instance; 
  } 
}
```

```java

// 静态内部类实现懒汉单例
public class Something {
    private Something() {}
    private static class LazyHolder {
        static final Something INSTANCE = new Something();
    }

    public static Something getInstance() {
        return LazyHolder.INSTANCE;
    }
}
```

### 参考

- [Java内存模型之volatile--程晓明](https://www.infoq.cn/article/java-memory-model-4)
