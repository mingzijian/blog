java.lang.Void
----------------------
Void类是一个不可调整（final）的占位符类，用于保存对表示Java关键字void的类对象的引用。

### 简要代码
```java
package java.lang;

/**
 * Void类是一个不可调整的占位符类，用于保存对表示Java关键字void的类对象的引用
 */
public final class Void {
    /** 表示与关键字void对应的伪类型 */
    public static final Class<Void> TYPE = (Class<Void>) Class.getPrimitiveClass("void");
    
    /** Void 类无法被实例化 */
    private Void() {}

}
```

Class.getPrimitiveClass 返回指定基本类型的虚拟机类对象。
如：

```java
package java.lang;
...
public final class Integer extends Number implements Comparable<Integer> {
...
    public static final Class<Integer> TYPE = (Class<Integer>) Class.getPrimitiveClass("int");
...
}
```

```java
package java.lang;
...
public final class Long extends Number implements Comparable<Long> {
...
    public static final Class<Long> TYPE = (Class<Long>) Class.getPrimitiveClass("long");
...
}
```

**类比**：
- Void : void
- Boolean : boolean 
- Byte : byte
- Short : short
- Integer : int
- Long : long
- Float : float
- Double : double
- Character : char

**注意**
void 不是基本数据类型（primitive type），是伪类型（pseudo-type）


### 应用场景
#### 作为返回值
某些接口（如：Callable）方法必须返回一个值，但实际执行后没有需要返回的数据。
```java
Future<Void> f = pool.submit(new Callable() {
    @Override
    public Void call() throws Exception {
        ...
        return null;
    }
});
```

#### 作为参数
比如通过反射获取所有返回值为void的方法。
```java
public class VoidTest {
    public void hello() { }
    public static void main(String args[]) {
        for (Method method : VoidTest.class.getMethods()) {
            if (method.getReturnType().equals(Void.TYPE)) {
                System.out.println(method.getName());
            }
        }
    }
}
```