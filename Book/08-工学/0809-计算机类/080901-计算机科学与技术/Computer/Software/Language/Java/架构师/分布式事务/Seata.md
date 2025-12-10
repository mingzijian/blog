# Seata

Seata 是一款开源的分布式事务解决方案，致力于提供高性能和简单易用的分布式事务服务。Seata 将为用户提供了 AT、TCC、SAGA 和 XA 事务模式，为用户打造一站式的分布式解决方案。

![img](https://seata.apache.org/zh-cn/assets/images/solution-1bdadb80e54074aa3088372c17f0244b.png)

## 各事务模式

### AT 模式

### TCC 模式

Try+Confirm/Cancel





#### TCC - 空回滚

未曾 Try 过先 Cancel，还没恋爱就分手。

空回滚指的是在一个分布式事务中，在没有调用参与方的 Try 方法的情况下，TM 驱动二阶段回滚调用了参与方的 Cancel 方法。



```mermaid
---
title: 空回滚
config:
  theme: dark
---
flowchart LR
    A[Cancel <br/>请求到达] --> B{查询<br/>事务记录}
    B -->|记录存在| C[按正常流程<br/>处理]
    B -->|记录不存在| D[创建<br/>空回滚记录]
    D --> E[设置状态<br/>=SUSPENDED]
    E --> F[标记为<br/>已处理]
    F --> G[返回成功]
    C --> H{当前状态}
    H -->|TRYING| I[执行<br/>业务回滚<br/>逻辑]
    H -->|COMMITTED| J[状态冲突<br/>抛出异常]
    H -->|SUSPENDED| K[已空回滚<br/>直接返回成功]
    I --> L[更新状态<br/>=ROLLBACK]
    L --> M[提交事务]
    M --> G[返回成功]
    K --> G[返回成功]
    
  classDef red fill:#f33,stroke:#fff
  classDef check fill:#49f,stroke:#fff, stroke-dasharray: 9,5,stroke-dashoffset: 900,animation: dash 25s linear infinite;
  class B,H check
  class A,G red

```

#### TCC - 防悬挂

已经 Cancel 如何 Try ? 恋爱结束来表白？

悬挂指的是二阶段 Cancel 方法比 一阶段 Try 方法优先执行，由于允许空回滚的原因，在执行完二阶段 Cancel 方法之后直接空回滚返回成功，此时全局事务已结束，但是由于 Try 方法随后执行，这就会造成一阶段 Try 方法预留的资源永远无法提交和释放，也就是悬挂了。

```mermaid
---
title: 防悬挂
config:
  theme: dark
---
flowchart LR
    A[Cancel/Confirm 请求到达] --> B{检查事务记录}
    B -->|记录不存在| C[创建空事务记录<br/>状态=SUSPENDED]
    B -->|记录存在| D{当前状态}
    D -->|TRYING| E[正常执行<br/> Cancel/Confirm]
    D -->|SUSPENDED| F[拒绝操作<br/>抛出悬挂事务异常]
    D -->|COMMITTED/ROLLBACK| G[根据状态返回结果]
    C --> H[记录已悬挂状态]
    E --> I[更新状态为 COMMITTED/ROLLBACK]
    F --> J[通知事务协调器失败]
    I --> L[提交事务]
    
  classDef red fill:#f33,stroke:#fff
  classDef check fill:#49f,stroke:#fff, stroke-dasharray: 9,5,stroke-dashoffset: 900,animation: dash 25s linear infinite;
  class B,D check
  class A,L,G,J red




```





#### TCC - 重试机制（幂等处理）

参与者 执行完二阶段之后，由于网络抖动或者宕机问题，会造成 TC 收不到参与者 执行二阶段的返回结果，TC 会重复发起调用，直到二阶段执行结果成功。

```mermaid
---
title: 重试
config:
  theme: dark
---
flowchart LR
     A[发起<br/>事务操作] --> B[执行<br/>业务逻辑]
    B --> C{操作成功?}
    C -->|是| D[更新<br/>事务状态]
    C -->|否| E{重试次数 < 最大次数?}
    E -->|是| F[等待退避时间<br/>（指数退避算法）]
    F --> G[重试次数+1]
    G --> B
    E -->|否| H[标记事务为<br/>失败]
    H --> I[触发<br/>全局回滚]
    D --> J[提交事务]
    I --> K[通知<br/>协调器]
    J --> L[返回成功]
    K --> M[记录<br/>失败原因]
    
  classDef red fill:#f33,stroke:#fff
  classDef check fill:#49f,stroke:#fff, stroke-dasharray: 9,5,stroke-dashoffset: 900,animation: dash 25s linear infinite;
  class C,E check
  class A,L red

```

##### 幂等处理

```mermaid
---
title: 重试
config:
  theme: dark
---
flowchart LR
    A[Confirm/Cancel 请求] --> B[获取全局锁]
    B --> C{查询事务记录<br/>xid+branchId}
    C -->|记录不存在| D[抛出异常<br/>事务记录不存在]
    C -->|记录存在| E{当前状态}
    E -->|目标状态| F[幂等返回成功<br/>不执行业务]
    E -->|冲突状态| G[抛出状态冲突异常]
    E -->|TRYING状态| H[执行业务逻辑<br/>更新状态+版本号]
    H --> J{更新成功?}
    J -->|是| K[提交事务]
    J -->|否| L[回滚事务<br/>触发重试]
    K --> M[释放全局锁]
    L --> N[释放全局锁]
    F --> M
    G --> N
    M --> O[返回成功结果]
    N --> P[返回失败结果]
    
  classDef orange fill:#883,stroke:#fff  
  classDef red fill:#f33,stroke:#fff
  classDef check fill:#49f,stroke:#fff, stroke-dasharray: 9,5,stroke-dashoffset: 900,animation: dash 25s linear infinite;
  class C,E,J check
  class A,O red
  class B,M,N orange

```



### TCC 状态机

```mermaid
---
title: 状态机说明
config:
  theme: dark
---
flowchart TD
    A([开始]) --> B[TRYING<br/>尝试预留资源]
    
    %% 正常状态转换
    B -->|Confirm成功| C[COMMITTED<br/>资源确认提交]
    B -->|Cancel成功| D[ROLLBACKED<br/>资源回滚释放]
    
    %% 特殊场景处理
    B -->|悬挂事务检测| E[SUSPENDED<br/>事务挂起]
    B -->|空回滚处理| E
    
    %% 幂等性处理
    C -->|重复Confirm请求| C
    D -->|重复Cancel请求| D
    
    %% 结束状态
    C --> F([事务成功结束])
    D --> G([事务回滚结束])
    E --> H([需人工干预])
    
    %% 状态详情标注
    subgraph 状态说明
        B -->|说明| I1["• 预留业务资源<br/>• 检查业务规则<br/>• 可逆操作"]
        C -->|说明| I2["• 确认预留资源<br/>• 执行实际业务<br/>• 不可逆操作"]
        D -->|说明| I3["• 释放预留资源<br/>• 清理中间状态<br/>• 可多次执行"]
        E -->|说明| I4["• Cancel先于Try到达<br/>• Try期间发生网络分区<br/>• 需要补偿处理"]
    end
    

    
    %% 样式定义
    classDef trying fill:#883,stroke:#000,stroke-width:2px
    classDef committed fill:#393,stroke:#228B22,stroke-width:2px
    classDef rollbacked fill:#f55,stroke:#DC143C,stroke-width:2px
    classDef suspended fill:#44f,stroke:#4682B4,stroke-width:2px
    classDef startend fill:#f00,stroke:#8A2BE2,stroke-width:2px,stroke-dasharray:5 5
    classDef note color:#fff, fill:#666,stroke:#1E90FF,stroke-dasharray:3 3
    
    class B trying
    class C committed
    class D rollbacked
    class E suspended
    class A,F,G,H startend
    class I1,I2,I3,I4,J1,J2,J3 note

```





### SAGA 模式

### XA 模式



