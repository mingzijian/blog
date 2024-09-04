Apache Commons Pool
------------------------------------

提供对象池API和许多对象池实现。

### 核心代码
#### PooledObjectFactory
提供用于管理池化对象的生命周期的通用接口。


#### GenericObjectPool
提供各种配置选项，能够缩写空闲或活动实例的数量，以撤销池中闲置的实例，因为第2版，GenericObjectPool还提供了废弃的实例跟踪和删除。


#### SoftReferenceObjectPool
可以按需增长，但允许垃圾收集器根据需要从池中撤销空闲实例。
SoftReference：当内存不足，GC会回收SoftReference所引用的对象。

#### PooledObjectState

PooledObject状态枚举。
[PooledObjectState Javadoc](https://javadoc.io/doc/org.apache.commons/commons-pool2/latest/org/apache/commons/pool2/PooledObjectState.html)

| 状态                      | 说明                                                         | comments                                                     |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| IDLE                      | 在队列中，处于空闲状态，可被分配使用                         | In the queue, not in use.                                    |
| ALLOCATED                 | 已被分配，被使用中                                           | In use.                                                      |
| EVICTION                  | 在池中，正在被逐出器验证                                     | In the queue, currently being tested for possible eviction.  |
| EVICTION_RETURN_TO_HEAD   | 不在队列中，目前正在测试是否可能逐出。测试时尝试借用对象，将其从队列中移除。一旦逐出测试完成，它应该返回到队列的头部。TODO:考虑分配对象并忽略逐出测试的结果。 | Not in the queue, currently being tested for possible eviction. An attempt to borrow the object was made while being tested which removed it from the queue. It should be returned to the head of the queue once eviction testing completes. TODO: Consider allocating object and ignoring the result of the eviction test. |
| VALIDATION                | 在队列中，当前正在验证。                                     | In the queue, currently being validated.                     |
| VALIDATION_PREALLOCATED   | 不在队列中，当前正在验证。对象是在验证时借用的，因为配置了testOnBorrow，所以它被从队列中删除并预先分配。它应该在验证完成后分配。 | Not in queue, currently being validated. The object was borrowed while being validated and since testOnBorrow was configured, it was removed from the queue and pre-allocated. It should be allocated once validation completes. |
| VALIDATION_RETURN_TO_HEAD | 不在队列中，当前正在验证。在先前进行逐出测试时尝试借用该对象，从而将其从队列中移除。一旦验证完成，它应该返回到队列的头部。 | Not in queue, currently being validated. An attempt to borrow the object was made while previously being tested for eviction which removed it from the queue. It should be returned to the head of the queue once validation completes. |
| INVALID                   | 维护失败（如逐出测试或验证），将/已被销毁                    | Failed maintenance (e.g. eviction test or validation) and will be / has been destroyed |
| ABANDONED                 | 对象被客户端拿出后，长时间未返回池中，或没有调用 use 方法，即被标记为抛弃的 | Deemed abandoned, to be invalidated.                         |
| RETURNING                 | 正在归还到池                                                 | Returning to the pool.                                       |


#### BaseObjectPoolConfig
基础对象池配置。
为子类共享的公共属性提供实现。
将使用公共常量定义的默认值创建此类的新实例。
此类不是线程安全的。
[BaseObjectPoolConfig Javadoc](https://javadoc.io/doc/org.apache.commons/commons-pool2/latest/org/apache/commons/pool2/impl/BaseObjectPoolConfig.html)

关键配置：

| 配置项                         | 说明                                                         | 示例                                  |
| ------------------------------ | ------------------------------------------------------------ | ------------------------------------- |
| lifo<br>后进先出               | 是否后进先出，默认为`true`，放在空闲队列最前面               | true                                  |
| fairness<br>公平               | 从池中获取/返还对象时是否使用公平锁机制，默认为 `false`      | false                                 |
| maxWaitMillis                  | 获取资源的最大等待时间。blockWhenExhausted 为 `true `时有效。`-1` 代表无时间限制，一直阻塞直到有可用的资源 | -1L                                   |
| minEvictableIdleTimeMillis     | 对象空闲的最小时间，达到此值后空闲对象将可能会被移除。`-1` 表示不移除；默认 30 分钟 | 1000L * 60L * 30L                     |
| softMinEvictableIdleTimeMillis | 同上，额外的条件是池中至少保留有 minIdle 所指定的个数的对象  | -1                                    |
| numTestsPerEvictionRun         | 资源回收线程执行一次回收操作，回收资源的数量。默认`3`        | 3                                     |
| evictionPolicyClassName        | 资源回收策略，默认值`org.apache.commons.pool2.impl.DefaultEvictionPolicy` <br>驱逐条件`((config.getIdleSoftEvictTime() < underTest.getIdleTimeMillis() && config.getMinIdle() < idleCount) || config.getIdleEvictTime() < underTest.getIdleTimeMillis())` | DefaultEvictionPolicy.class.getName() |
| testOnCreate                   | 创建对象时是否调用 factory.validateObject 方法，默认 `false` | false                                 |
| testOnBorrow                   | 借用对象时是否调用 factory.validateObject 方法，默认 `false` | false                                 |
| testOnReturn                   | 归还对象时是否调用 factory.validateObject 方法，默认 `false` | false                                 |
| testWhileIdle                  | 池中的闲置对象是否由逐出器验证。无法验证的对象将从池中删除销毁。默认 `false` | false                                 |
| timeBetweenEvictionRunsMillis  | 回收资源线程的执行周期，默认`-1` 表示不启用回收资源线程      | -1L                                   |
| blockWhenExhausted             | 资源耗尽时，是否阻塞等待获取资源，默认 `true`                | true                                  |




### Sample

```java
public class ObjectFactory extends BasePooledObjectFactory<Object> {
    // 创建对象
    @Override
    public Object create() throws Exception {
        return new Object();
    }

    @Override
    public PooledObject<Object> wrap(Object object) {
        return new DefaultPooledObject<>(object);
    }

    // 钝化对象
    @Override
    public void passivateObject(PooledObject<Object> pooledObject) {
        // 属性初始化
    }
    
    public static void main(String[] args) throws Exception {
        // 池化对象工厂(池化对象创建、钝化、销毁、包装)
        ObjectFactory objectFactory = new ObjectFactory();
        // 创建对象池（借用、归还）
        GenericObjectPool<Object> objectPool = new GenericObjectPool<Object>(objectFactory);
        // 设置池可以分配的对象数的上限(默认:8)
        objectPool.setMaxTotal(100);
        Object object = null;
        for (int i = 0; i < 3; i++) {
            try {
                // 借用对象
                object = objectPool.borrowObject();
                // 对象赋值、处理
                System.out.println(object.hashCode());
            }catch (Exception e){
                throw new RuntimeException("Unable to borrow Object from pool" + e.toString());
            } finally{
                try {
                    // 归还对象
                    objectPool.returnObject(object);
                } catch (Exception e) {
                    // ignored
                }
            }
        }

    }

}

```


### 参考
 - [Apache Commons Pool](http://commons.apache.org/proper/commons-pool/)