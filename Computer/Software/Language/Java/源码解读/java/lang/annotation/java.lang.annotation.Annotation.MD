java.lang.annotation.Annotation
---------------------------------------------------

被所有注解类型扩展的公共接口。

### 简要代码
```java
package java.lang.annotation;
/**
 * 被所有注解类型扩展的公共接口。
 */
public interface Annotation {

    /** 逻辑等价判断。 */
    boolean equals(Object obj);
    
    /** 返回当前注解的hashCode */
    int hashCode();
    
    /** 返回当前注解的字符串表现形式 */
    String toString();
    
    /** 返回当前注解的注解类型 */
    Class<? extends Annotation> annotationType();
}
```