[TOC]
### 发布时间
2014-03-18

### 新特性
#### Java 编程语言
##### Lambda 表达式

##### 方法引用 

##### 默认方法

##### 重复注解

##### 类型注解

##### 改进的类型推断

##### 方法参数反射


#### 集合 Collections
- Stream API
- HashMap key冲突性能优化

#### 安全 Security
- 默认启用客户端 TLS 1.2
- AccessController.doPrivileged 的新变体
- 更强大的基于密码的加密算法
- JSSE 服务器中的 SSL/TLS 服务器名称指示 (SNI) 扩展支持
- 支持 AEAD 算法
- 密钥库增强
- SHA-224 消息摘要
- 增强对 NSA Suite B 加密的支持
- 更好地支持高熵随机数生成
- 用于配置 X.509 证书吊销检查的新类 java.security.cert.PKIXRevocationChecker 
- 适用于 64 位Windows 的  PKCS11
- Kerberos 5 重放缓存中的新 rcache 类型
- 默认禁用 Kerberos 5 弱加密类型
- 未绑定 SASL的GSS-API/Kerberos 5 机制
- 多个主机名的 SASL 服务
- JNI 桥接 Mac OS X 上的原生 JGSS
- 在 SunJSSE 提供程序中支持更强的临时 DH 密钥
- 支持 JSSE 中的服务器端密码套件首选项自定义

#### JavaFX

#### 工具 Tools
- 提供了 jjs 命令来调用 Nashorn 引擎。
- java 命令启动 JavaFX 应用程序。
- java 手册页已经过重新设计。
- jdeps 命令行工具用于分析类文件。
- Java 管理扩展 (JMX) 提供对诊断命令的远程访问。
- jarsigner 工具可以选择从时间戳机构 (TSA) 请求签名时间戳。


#### 国际化 Internationalization
- Unicode 增强功能，包括对 Unicode 6.2.0 的支持
- 采用 Unicode CLDR 数据和 java.locale.providers 系统属性
- 新的日历类和语言环境 API
- 能够安装自定义资源包作为扩展


#### 部署 Deployment

#### 脚本编写 Scripting
- Rhino javascript 引擎已被 Nashorn Javascript 引擎取代

#### Pack200

#### IO and NIO

#### java.lang and java.util Packages
- 并行数组排序
- 标准编码和解码 Base64
- 支持无符号算术

#### JDBC
- JDBC-ODBC 桥已被删除。
- JDBC 4.2 引入了新功能。

#### Java DB

#### 联网 Networking
- 新增类 java.net.URLPermission
- 优化类 java.net.HttpURLConnection 如果安装了安全管理器，则调用打开连接的请求需要权限。
#### 并发 Concurrency
- java.util.concurrent 包下新增类和接口
- ConcurrentHashMap 支持流设施和lambda表达式
- java.util.concurrent.atomic 包支持可扩展的可更新变量
- ForkJoinPool 支持公共池（common pool）
- StampedLock 基于能力（capability-based）的锁

#### Java XML - JAXP

#### HotSpot 虚拟机
- 将硬件内在函数用以高级加密标准 (AES)。
- 移除永久代（PermGen） 
- 用于方法调用的字节码指令支持 Java 编程语言中的默认方法。

#### Java Mission Control 5.3 Release Notes


### 参考
- [What's New in JDK 8](https://www.oracle.com/java/technologies/javase/8-whats-new.html)