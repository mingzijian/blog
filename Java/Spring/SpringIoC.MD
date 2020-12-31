Spring IoC (`Inversion of Control`)
--------------------------------------------

#### 释义
  控制反转（Inversion of Control，缩写为IoC），是面向对象编程中的一种设计原则，可以用来减低计算机代码之间的耦合度。其中最常见的方式叫做依赖注入（Dependency Injection，简称DI），还有一种方式叫“依赖查找”（Dependency Lookup）。通过控制反转，对象在被创建的时候，由一个调控系统内所有对象的外界实体将其所依赖的对象的引用传递给它。也可以说，依赖被注入到对象中。

#### 理解
  简单理解就是：将对象的依赖关系控制权交给 Spring ，由 Spring 容器统一管理。

#### 方式实现
 - 依赖注入（Dependency Injection，简称DI）
   
   <pre>容器负责组件的装配。
 - 依赖查找（Dependency Lookup，简称DL）
   
   <pre>需要用户使用 API 进行查找资源和组装对象，有侵入性。

#### 优劣分析
 - 优点
   
   <pre>降低组件耦合度，提高组件可复用率
 - 缺点
   
   <pre>创建对象的步骤变多，轻微性能损耗

#### 循环依赖
##### 释义
  两个或两个以上的 bean 互相依赖，最终形成闭环。比如 A 依赖于 B ， B 又依赖于 A 。
```java
@Service
public class A {  
    @Autowired  
    private B b;
}
```
```java
@Service
public class B {  
    @Autowired  
    private A a;
}
```
 ![循环依赖](https://images.gitee.com/uploads/images/2020/0603/193915_934bd61f_536895.png "循环依赖.png")

##### 解决方案
三级缓存
 ![三级缓存](https://images.gitee.com/uploads/images/2020/0603/194458_ab305929_536895.png "三级缓存.png")

