Istio
-------
连接、安全加固、控制和观察服务的开放平台

 ![Istio](https://gitee.com/mingzijian/resources/raw/master/picgo/image-20210429150138710.png)

- 连接（Connect）：

  智能地控制服务之间的流量和API调用，执行一系列测试，并随服务逐步升级红/黑部署。

- 安全（Secure）：

  自动为服务之间的调用提供认证、授权和加密。

- 控制（Control）：

  应用用户定义的策略，保证资源在消费者中公平分配。

- 观察（Observe）：

  查看服务运行期间的各种数据，比如日志、监控和 追踪，了解服务的运行情况。
  
### 架构
整体架构
 ![Istio整体架构](https://gitee.com/mingzijian/resources/raw/master/picgo/9c0a20ff386489f65f4d0c64b125b246.svg)

#### Mixer (搅拌机)
为整个集群执行 访问控制 和 策略管理（限流/配额等），并且收集代理观察到的服务之间的流量统计数据。

#### Pilot (飞行员)
为 特使（Envoy） 提供了服务发现，流量管理和智能路由（AB 测试、金丝雀发布等），以及错误处理（超时、重试、熔断）功能。 
用户通过 Pilot 的 API 管理网络相关的资源对象，Pilot 会根据用户的配置和服务的信息把网络流量管理变成 Envoy 能识别的格式分发到各个 边车（ Sidecar） 代理中。

#### Galley (战舰)
Galley 是 Istio 的配置验证、提取、处理和分发组件。
它负责将其余的 Istio 组件与从底层平台（例如 Kubernetes）获取用户配置的细节隔离开来。

#### Citadel (堡垒)
为服务之间提供认证和证书管理，可以让服务自动升级成 TLS 协议。

### 通信管理

- 虚拟服务 [Virtual services]
- 目标规则 [Destination rules]
- 网关 [Gateways]
- 服务条目 [Service entries]
- 边车 [Sidecars]
- 网络弹性与测试 [Network resilience and testing]

### 安全加固

安全概览
 ![Security overview](https://gitee.com/mingzijian/resources/raw/master/picgo/overview.svg)

安全架构
 ![Security Architecture](https://gitee.com/mingzijian/resources/raw/master/picgo/arch-sec.svg)

### 可观察性
遥测类型：
- 指标
  - 代理级指标
  - 服务级指标
  - 控制面板指标
- 分布式追踪
- 访问日志

### 可拓展性

 ![Extending Istio/Envoy](https://gitee.com/mingzijian/resources/raw/master/picgo/extending.svg)

### Istio项目名称的由来
Istio（ISS-tee-oh）是由 Tetrate 创始人 Varun Talwar 和谷歌首席工程师 Louis Ryan 在 2017 年命名的，当时他们都在谷歌工作。

Istio 在希腊语中是 “sail” 的意思，它（ιστίο）延用了 Kubernetes（在希腊语中是飞行员或舵手的意思）建立的希腊航海主题。Istio 和它的表亲 Istos（ιστός）（意思是桅杆、网）都来自古希腊词根 Istimi（ἵστημι），意思是 “to make stand”。

最后说明一下：Istio 并不是首字母缩写，但如果一定要说它代表着什么，也许会是 “I Secure, Then I Observe”（我保护，我观察），或者 "I’m Sexy To Infrastructure Operators."（我对基础设施运营商富有吸引力）。

### 参考
 - [官网](https://istio.io)
 - [Istio是啥](http://www.uml.org.cn/wfw/201909063.asp)
 - [what-is-istio](https://istio.io/latest/zh/docs/concepts/what-is-istio)
 - [Istio1.4中文文档 --书栈](https://www.bookstack.cn/read/istio-1.4-zh/575f2c1cabf14274.md)
 - [Istio中文文档 --官网](https://istio.io/latest/zh/docs)
 - [Istio项目名称的由来](https://www.amd5.cn/atang_4803.html)