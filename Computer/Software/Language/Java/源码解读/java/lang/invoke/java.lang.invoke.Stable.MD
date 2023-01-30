java.lang.invoke.Stable
------------------------------------
稳定的。
如果一个字段的所有组件变量最多只改变一次值，则可以将该字段注解为稳定的。
包级私有，不能被三方使用。
```java
package java.lang.invoke;
/* package-private */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Stable {
}
```
