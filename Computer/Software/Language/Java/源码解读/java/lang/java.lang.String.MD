String
---------
String类表示字符串。Java程序中的所有字符串文本（如“abc”）都作为此类的实例实现。


### 源码分析

```java
package java.lang;
import ...
public final class String 
	implements java.io.Serializable, Comparable<String>, CharSequence {

	/** 用于字符存储 */
	private final char value[];
	
	/** 字符串的 hash code 缓存 */
	private int hash;
	
	...
}

```

AbstractStringBuilder
StringBuffer
StringBuilder （线程安全 synchronized）