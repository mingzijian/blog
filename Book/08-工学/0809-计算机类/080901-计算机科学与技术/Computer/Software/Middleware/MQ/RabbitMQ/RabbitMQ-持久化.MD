RabbitMQ 持久化
--------------------------
### 持久化配置
#### 交换机持久化
spring amqp 默认开启

##### 关键代码
```java
package org.springframework.amqp.core;
import ...
public abstract class AbstractExchange extends AbstractDeclarable implements Exchange {
    private final String name;
    private final boolean durable;
    private final boolean autoDelete;
    private final Map<String, Object> arguments;
    private volatile boolean delayed;
    private boolean internal;

    public AbstractExchange(String name) {
        this(name, true, false);
    }

    public AbstractExchange(String name, boolean durable, boolean autoDelete) {
        this(name, durable, autoDelete, (Map)null);
    }

    public AbstractExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        this.name = name;
        this.durable = durable;
        this.autoDelete = autoDelete;
        if (arguments != null) {
            this.arguments = arguments;
        } else {
            this.arguments = new HashMap();
        }

    }
    ...
}
```

#### 队列持久化
spring amqp 默认开启

##### 关键代码
```java
package org.springframework.amqp.core;
import ...
public class Queue extends AbstractDeclarable {
    public static final String X_QUEUE_MASTER_LOCATOR = "x-queue-master-locator";
    private final String name;
    private final boolean durable;
    private final boolean exclusive;
    private final boolean autoDelete;
    private final Map<String, Object> arguments;
    private volatile String actualName;

    public Queue(String name) {
        this(name, true, false, false);
    }

    public Queue(String name, boolean durable) {
        this(name, durable, false, false, (Map)null);
    }

    public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete) {
        this(name, durable, exclusive, autoDelete, (Map)null);
    }

    public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) {
        Assert.notNull(name, "'name' cannot be null");
        this.name = name;
        this.actualName = StringUtils.hasText(name) ? name : Base64UrlNamingStrategy.DEFAULT.generateName() + "_awaiting_declaration";
        this.durable = durable;
        this.exclusive = exclusive;
        this.autoDelete = autoDelete;
        this.arguments = (Map)(arguments != null ? arguments : new HashMap());
    }
    ...
}
```

#### 消息持久化
spring amqp 默认开启

##### 关键代码
```java
package org.springframework.amqp.core;
import ...
public class MessageProperties implements Serializable {
    ...
    public static final MessageDeliveryMode DEFAULT_DELIVERY_MODE;
    ...
    private volatile MessageDeliveryMode deliveryMode;
    ...
    public MessageProperties() {
        this.deliveryMode = DEFAULT_DELIVERY_MODE;
        this.priority = DEFAULT_PRIORITY;
    }
    ...
    static {
        DEFAULT_DELIVERY_MODE = MessageDeliveryMode.PERSISTENT;
        DEFAULT_PRIORITY = 0;
    }
}
```


### 什么时候需要持久化 （要求将消息写入磁盘）
- 消息本身在publish的时候就要求消息写入磁盘；
- 内存紧张，需要将部分内存中的消息转移到磁盘；

### 什么时候会持久化（数据刷写到磁盘）
- 写入文件前会有一个Buffer，大小为1M（1048576），数据在写入文件时，首先会写入到这个Buffer，如果Buffer已满，则会将Buffer写入到文件（未必刷到磁盘）；
- 有个固定的刷盘时间：25ms，也就是不管Buffer满不满，每隔25ms，Buffer里的数据及未刷新到磁盘的文件内容必定会刷到磁盘；
- 每次消息写入后，如果没有后续写入请求，则会直接将已写入的消息刷到磁盘：使用Erlang的receive x after 0来实现，只要进程的信箱里没有消息，则产生一个timeout消息，而timeout会触发刷盘操作。

### 以什么格式保存持久化数据
消息保存于$MNESIA/msg_store_persistent/x.rdq文件中，其中x为数字编号，从1开始，每个文件最大为16M（16777216），超过这个大小会生成新的文件，文件编号加1。

### 持久化数据删除
publish消息时写入内容，ack消息时删除内容（更新该文件的有用数据大小），当一个文件的有用数据等于0时，删除该文件。
#### 持久化数据合并
当所有文件中的垃圾消息（已经被删除的消息）比例大于阈值（GARBAGE_FRACTION = 0.5）时，会触发文件合并操作（至少有三个文件存在的情况下），以提高磁盘利用率。

### 数据恢复
#### 异常重启

#### 备份还原
