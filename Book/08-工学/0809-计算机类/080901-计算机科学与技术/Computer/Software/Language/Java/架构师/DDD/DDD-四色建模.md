# DDD 四色建模



![DDD-四色建模](https://mingzijian-picgo.oss-rg-china-mainland.aliyuncs.com/img/1721371905370_88AA978A-1C3F-4f06-865C-C4594E2D9895.png)

在四色模型中，我们将抽象出来的对象分为四种原型（archetype）

## 四种原型

### <span style="background-color:pink">（1）业务关键时刻（Moment-Interval）</span>

这种对象表示那些在某个时间点**存在**，或者会存在一段时间的。这样的对象往往表示了一次外界的请求，比如一次询价，一次下单或者一次租赁。

Moment-Interval 是最重要的一类对象，因为他是系统业务价值所在，一般用**粉红色**表示。这样的对象一般有一个**起始**时间和**终止**时间，以及一个**唯一**的标识号，用来唯一的标识这一次用户请求，比如：订单号。

### <span style="background-color:yellow">（2）角色 （Role）</span>

这种对象表示的是一种角色，往往由人或者物来承担，会有相应的**责任和权利**，一般**一个 moment-interval** 对象会**关联多个Role**。例如一次下单涉及两个 Role ：客户-商品。

这类对象是除 moment-interval 对象外最重要的一类对象，一般用**黄色**表示。

### <span style="background:green;color:white">（3）人-事-物（Party,Place, or Thing）</span>

这种对象往往表示的是一种**客观存在**的事物，例如：人、组织、产品或者配件等，这些事物往往会在一种moment-interval 中扮演某个Role。例如，某个人会在一次购买中扮演Customer的角色，也可以在询价中扮演询价人的角色。一般用**绿色**来表示。

### <span style="background-color:blue;color:white">（4）描述（Description）</span>

这种对象一般是**分类**用或者描述性的对象，它的属性一般都是这一类事物都有的**属性**，这类对象一般用**蓝色**来表示。



## 四色建模步骤

### <span style="background-color:pink">1，确定需要分析的业务关键时刻</span>

![1](https://mingzijian-picgo.oss-rg-china-mainland.aliyuncs.com/img/a78a88a20bfdda2c05fbccbe4b7f3743.png)

### <span style="background-color:yellow">2，定位业务涉及的人/事/物</span>

![2](https://mingzijian-picgo.oss-rg-china-mainland.aliyuncs.com/img/a25adc2ef86858c0fbebec53b47b1a23.png)

### <span style="background:green;color:white">3，抽象出可以参与到不同流程中去的角色</span>



### <span style="background-color:blue;color:white">4，进一步描述补全信息</span>



## 示例

### 电商 DDD 四色图

![1721373750811_C7506BD2-ED1C-489f-927A-62321BB80BC2](https://mingzijian-picgo.oss-rg-china-mainland.aliyuncs.com/img/1721373750811_C7506BD2-ED1C-489f-927A-62321BB80BC2.png)
