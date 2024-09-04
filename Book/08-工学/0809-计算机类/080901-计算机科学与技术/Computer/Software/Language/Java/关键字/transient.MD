transient
--------------

### 字面含义
短暂的，临时的。

**理解：**
作为临时变量，只短暂存在于内存，不会被序列化到磁盘中。

###  作用
对于实现了`Serializable`接口的对象，被`transient`修饰的属性不会被序列化。

####  应用场景
过滤敏感信息，如：密码

### 拓展

#### Serializable & Externalizable

```java
package java.io;
public interface Serializable {
}
```

```java
package java.io;
import java.io.ObjectOutput;
import java.io.ObjectInput;
public interface Externalizable extends java.io.Serializable {
  // 外部写入（序列化保存对象）
  void writeExternal(ObjectOutput out) throws IOException;
  // 外部读取（反序列化读取对象）
  void readExternal(ObjectInput in) throws IOException,ClassNotFoundException;
}
```
#### 部分属性序列化过滤的方法
- 1.使用transient关键字
- 2.实现Externalizable接口，重写writeExternal和readExternal方法
- 3.自定义writeObject和readObject方法

##### 被transient修饰的属性是否一定不会被序列化？
不一定。被transient修饰的属性有可能会被序列化。比如通过方法2或方法3自定义了对象的序列化方式，在自定义方法中，仍然可以序列化该属性，即使它被transient修饰。

