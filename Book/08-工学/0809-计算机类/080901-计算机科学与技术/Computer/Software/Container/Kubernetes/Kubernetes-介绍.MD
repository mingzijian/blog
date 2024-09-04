Kubernetes
------------------
Kubernetes 是一个可移植的，可扩展的开源平台，用于管理容器化的工作负载和服务，方便了声明式配置和自动化。
它拥有一个庞大且快速增长的生态系统。
Kubernetes 的服务，支持和工具广泛可用。

Kubernetes的名字来自希腊语，意思是“舵手” 或 “领航员”。
K8s是将中间的8个字母“ubernete”替换为“8”的缩写。

### 目标
促进完善组件和工具的生态系统，以减轻应用程序在公有云或私有云中运行的负担。

### 特性

- **可移植**: 支持公有云，私有云，混合云，多重云（multi-cloud）
- **可扩展**: 模块化, 插件化, 可挂载, 可组合
- **自动化**: 自动部署，自动重启，自动复制，自动伸缩/扩展


### 组件
#### 控制平面组件（Control Plane Components）
控制平面的组件对集群做出全局决策(比如调度)，以及检测和响应集群事件（例如，当不满足部署的 replicas 字段时，启动新的 pod）。

控制平面组件可以在集群中的任何节点上运行。

##### kube-apiserver
该组件用于暴露Kubernetes API。
任何的资源请求/调用操作都是通过kube-apiserver提供的接口进行。

##### etcd
etcd 是兼具一致性和高可用性的键值数据库，可以作为保存 Kubernetes 所有集群数据的后台数据库。

##### kube-scheduler
调度器。
负责监视新创建的、未指定运行节点的 Pods，选择节点让 Pod 在上面运行。

调度决策考虑的因素包括：单个 Pod 和 Pod 集合的资源需求、硬件/软件/策略约束、亲和性和反亲和性规范、数据位置、工作负载间的干扰和最后时限。

##### kube-controller-manager

##### cloud-controller-manager
云控制器管理器是指嵌入特定云的控制逻辑的 控制平面组件。

#### 节点组件 （Node Components）
节点组件在每个节点上运行，维护运行的 Pod 并提供 Kubernetes 运行环境。
##### kubelet

##### kube-proxy
维护节点上的网络规则。

#### 插件（Addons）

##### DNS

##### WEB界面（仪表盘）
Dashboard 是Kubernetes 集群的通用的、基于 Web 的用户界面。
它使用户可以管理集群中运行的应用程序以及集群本身并进行故障排除。

##### 容器资源监控
将关于容器的一些常见的时间序列度量值保存到一个集中的数据库中，并提供用于浏览这些数据的界面。

##### 集群层面日志
将容器的日志数据 保存到一个集中的日志存储中，该存储能够提供搜索和浏览接口。

### API
#### OpenAPI 规范

#### API 变更

#### API 组和版本

#### API 扩展
有两种途径来扩展 Kubernetes API：
1. 使用自定义资源 来以声明式方式定义 API 服务器如何提供你所选择的资源 API。
2. 选择实现自己的 聚合层 来扩展 Kubernetes API。

### 使用 Kubernetes 对象
Kubernetes 对象是 Kubernetes 系统中的持久性实体。
Kubernetes 使用这些实体表示您的集群状态。
了解 Kubernetes 对象模型以及如何使用这些对象。

### 架构


### 参考
 - [kubernetes overview](https://kubernetes.io/zh/docs/concepts/overview/)