Zipkin
----------

Zipkin 是一个分布式追踪系统。
它有助于收集解决服务架构中的延迟问题所需的计时数据。
功能包括收集和查找此数据。


## 架构

### 架构概览

![Zipkin architecture](https://gitee.com/mingzijian/resources/raw/master/picgo/architecture-1.png)

### 示例流程

当用户发起一次调用时，Zipkin 的客户端会在入口处为整条调用链路生成一个全局唯一的 trace id，并为这条链路中的每一次分布式调用生成一个 span id。span 与 span 之间可以有父子嵌套关系，代表分布式调用中的上下游关系。span 和 span 之间可以是兄弟关系，代表当前调用下的两次子调用。一个 trace 由一组 span 组成，可以看成是由 trace 为根节点，span 为若干个子节点的一棵树。

 Zipkin 会将 trace 相关的信息在调用链路上传递，并在每个调用边界结束时异步的把当前调用的耗时信息上报给 Zipkin Server。Zipkin Server 在收到 trace 信息后，将其存储起来。随后 Zipkin 的 Web UI 会通过 API 访问的方式从存储中将 trace 信息提取出来分析并展示。
 
 ![trace](https://gitee.com/mingzijian/resources/raw/master/picgo/1153954-20190529124054604-116989993.png)

```
┌─────────────┐ ┌───────────────────────┐  ┌─────────────┐  ┌──────────────────┐
│ User Code   │ │ Trace Instrumentation │  │ Http Client │  │ Zipkin Collector │
└─────────────┘ └───────────────────────┘  └─────────────┘  └──────────────────┘
       │                 │                         │                 │
           ┌─────────┐
       │ ──┤GET /foo ├─▶ │ ────┐                   │                 │
           └─────────┘         │ record tags
       │                 │ ◀───┘                   │                 │
                           ────┐
       │                 │     │ add trace headers │                 │
                           ◀───┘
       │                 │ ────┐                   │                 │
                               │ record timestamp
       │                 │ ◀───┘                   │                 │
                             ┌─────────────────┐
       │                 │ ──┤GET /foo         ├─▶ │                 │
                             │X-B3-TraceId: aa │     ────┐
       │                 │   │X-B3-SpanId: 6b  │   │     │           │
                             └─────────────────┘         │ invoke
       │                 │                         │     │ request   │
                                                         │
       │                 │                         │     │           │
                                 ┌────────┐          ◀───┘
       │                 │ ◀─────┤200 OK  ├─────── │                 │
                           ────┐ └────────┘
       │                 │     │ record duration   │                 │
            ┌────────┐     ◀───┘
       │ ◀──┤200 OK  ├── │                         │                 │
            └────────┘       ┌────────────────────────────────┐
       │                 │ ──┤ asynchronously report span     ├────▶ │
                             │                                │
                             │{                               │
                             │  "traceId": "aa",              │
                             │  "id": "6b",                   │
                             │  "name": "get",                │
                             │  "timestamp": 1483945573944000,│
                             │  "duration": 386000,           │
                             │  "annotations": [              │
                             │--snip--                        │
                             └────────────────────────────────┘

```

### 传输

3中主要的传输方式：
 - HTTP
 - Kafka
 - Scribe

### 组件
#### Zipkin Collector
收集器

一旦追踪数据到达Zipkn收集器守护程序，它就已经被收集器验证，存储和建立索引。

#### Storage
存储

Zipkin最初建立以存储在Cassandra上的数据，因为Cassandra可扩展，具有灵活的架构，并且在Twitter中使用大量使用。
但是，Cassandra可插拔。
除了Cassandra之外，还支持Elasticsearch和MySQL。
其他后端可能提供为第三方扩展。

#### Zipkin Query Service
查询服务

主要为 Web UI 提供一个简单的JSON API，用户查找和检索追踪数据。

#### Web UI
漂亮界面

未内置身份验证！