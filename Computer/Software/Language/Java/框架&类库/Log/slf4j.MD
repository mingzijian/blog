SLF4J
--------
The Simple Logging Facade for Java.
针对 Java 的简单日志门面。

### Hello World
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```

### 典型用法
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Panda {
 
  final Logger logger = LoggerFactory.getLogger(Panda.class);
  Integer t;
  Integer oldT;

  public void setTemperature(Integer temperature) {
   
    oldT = t;        
    t = temperature;

    logger.debug("温度设置为： {}. 之前的温度是： {}.", t, oldT);

    if(temperature.intValue() > 50) {
      logger.info("温度已上升到50度以上.");
    }
  }
} 
```

### 流畅的日志API



### MDC支持

MDC:Mapped Diagnostic Context 映射调试上下文，一种方便在多线程条件下记录日志的功能。

### 参考
- [SLF4J用户手册](http://www.slf4j.org/manual.html)
- [为什么阿里巴巴禁止工程师直接使用日志系统(Log4j、Logback)中的 API](https://mp.weixin.qq.com/s/vCixKVXys5nTTcQQnzrs3w)