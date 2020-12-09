Netty
---------
### 简介
	Netty是一个异步事件驱动的网络应用框架，用于快速开发可维护的高性能服务器和客户端。

### 特性（优势）
 ![Netty特性](https://images.gitee.com/uploads/images/2020/1203/151410_0cbec12e_536895.png "Netty特性.png")

#### 设计
统一API
可拓展
可定制
真正的无连接数据报套接字支持（自 3.1 起）
#### 易用
文档丰富
依赖简单

#### 性能
高吞吐，低延时
更少的资源消耗
最小化不必要的内存拷贝

#### 安全
完整的 SSL/TLS 和 StartTLS 支持

#### 社区
社区活跃（版本迭代周期短，bug 修复速度快）

### 核心组件

#### Bootstrap
Bootstrap意思是引导。
一个Netty应用通常由一个Bootstrap开始，主要作用是配置整个Netty程序，串联各个组件。
Netty中Bootstrap类是客户端程序的启动引导类，ServerBootstrap是服务端启动引导类。

#### Channel
在Netty框架中，Channel是其中之一的核心概念，是Netty网络通信的主体，由它负责同对端进行网络通信、注册和数据操作等功能。

##### FileChannel
FileChannel 是操作文件的Channel，我们可以通过 FileChannel 从一个文件中读取数据，也可以将数据写入到文件中。

##### SocketChannel
SocketChannel 是一个客户端用来进行 TCP 连接的 Channel。

##### ServerSocketChannel
ServerSocketChannel 顾名思义，是用在服务器为端的，可以监听客户端的 TCP 连接。

##### DatagramChannel
DatagramChannel 是用来处理 UDP 连接的。

#### EventLoop

#### ChannelFuture

#### ChannelFutureListener

#### ChannelHandler

#### ChannelPipeline

### 零拷贝

### TCP 粘包/拆包

### Buffer
传统的 I/O 是面向字节流或字符流的，以流式的方式顺序地从一个 Stream 中读取一个或多个字节, 因此也就不能随意改变读取指针的位置。

在 NIO 中，抛弃了传统的 I/O 流，而是引入了 Channel 和 Buffer 的概念。在 NIO 中，只能从 Channel 中读取数据到 Buffer 中或将数据从 Buffer 中写入到 Channel。

### 线程模型

#### 事件驱动模型

- 事件队列 Event Queue
- 事件分发器 Event Mediator
- 事件通道 Event Channel
- 事件处理器 Event Processor

 ![事件驱动模型](https://images.gitee.com/uploads/images/2020/1203/142309_155b4860_536895.png "事件驱动模型.png")

#### Reactor线程模型
![Reactor线程模型](https://images.gitee.com/uploads/images/2020/1203/144338_328c16dd_536895.png "Reactor线程模型.png")

#### Netty线程模型

![Netty工作架构图](https://images.gitee.com/uploads/images/2020/1203/144527_fd2ecc78_536895.png "Netty工作架构图.png")

### 应用场景
1）互联网行业：
在分布式系统中，各个节点之间需要远程服务调用，高性能的 RPC 框架必不可少，Netty 作为异步高性能的通信框架，往往作为基础通信组件被这些 RPC 框架使用。
典型的应用有：  Dubbo

2）游戏行业：
Netty 作为高性能的基础通信组件，它本身提供了 TCP/UDP 和 HTTP 协议栈。
非常方便定制和开发私有协议栈，账号登录服务器，地图服务器之间可以方便地通过 Netty 进行高性能的通信。

3）大数据领域：
经典的 Hadoop 的高性能通信和序列化组件 Avro 的 RPC 框架，默认采用 Netty 进行跨界点通信，它的 Netty Service 是基于 Netty 框架的二次封装实现。



### 心跳机制

### 参考

- [官网](https://netty.io)
- [Netty API 4.1](https://netty.io/4.1/api/index.html)
- [Netty面试题 --疯狂创客圈](https://www.cnblogs.com/crazymakercircle/p/13903625.html)
