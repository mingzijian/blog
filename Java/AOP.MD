AOP (Aspect Oriented Programming) 面向切面编程
---------------------------------------------

### AOP 简介
  AOP，面向切面编程，往往被定义为促使软件系统实现关注点的分离的技术。系统是由许多不同的组件所组成的，每一个组件负责一块特定的功能。除了实现自身核心功能之外，这些组件还经常承担着额外的职责。例如日志、事务管理和安全这样的核心服务经常融入到自身具有核心业务逻辑的组件中去。
#### AOP 相关术语
 - 通知： 
   通知定义了切面是什么以及何时使用的概念。Spring 切面可以应用5种类型的通知：

 - 前置通知（Before）： 
   在目标方法被调用之前调用通知功能。

 - 后置通知（After）： 
   在目标方法完成之后调用通知，此时不会关心方法的输出是什么。

 - 返回通知（After-returning）： 
   在目标方法成功执行之后调用通知。

 - 异常通知（After-throwing）： 
   在目标方法抛出异常后调用通知。

 - 环绕通知（Around）： 
   通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为。

 - 连接点： 
   是在应用执行过程中能够插入切面的一个点。

 - 切点：  
   切点定义了切面在何处要织入的一个或者多个连接点。

 - 切面： 
   是通知和切点的结合。通知和切点共同定义了切面的全部内容。

 - 引入： 
   引入允许我们向现有类添加新方法或属性。

 - 织入： 
   是把切面应用到目标对象，并创建新的代理对象的过程。切面在指定的连接点被织入到目标对象中。在目标对象的生命周期中有多个点可以进行织入： 编译期： 在目标类编译时，切面被织入。这种方式需要特殊的编译器。AspectJ的织入编译器就是以这种方式织入切面的。

 - 目标对象（Target Object）: 
   包含连接点的对象。也被称作被通知或被代理对象。

 - 类加载期： 
   切面在目标加载到JVM时被织入。这种方式需要特殊的类加载器(class loader)它可以在目标类被引入应用之前增强该目标类的字节码。

 - 运行期：  
   切面在应用运行到某个时刻时被织入。一般情况下，在织入切面时，AOP容器会为目标对象动态地创建一个代理对象。SpringAOP就是以这种方式织入切面的。

#### AOP 使用场景
 - Authentication 权限
 - Caching 缓存
 - Context passing 内容传递
 - Error handling 错误处理
 - Lazy loading　懒加载
 - Debugging　　调试
 - logging, tracing, profiling and monitoring　记录跟踪　优化　校准
 - Performance optimization　性能优化
 - Persistence　　持久化
 - Resource pooling　资源池
 - Synchronization　同步
 - Transactions 事务

#### Spring AOP 和 AspectJ AOP 区别

Spring AOP是属于运行时增强，而AspectJ是编译时增强。Spring AOP基于代理（Proxying），而AspectJ基于字节码操作（Bytecode Manipulation）。

Spring AOP已经集成了AspectJ，AspectJ应该算得上是Java生态系统中最完整的AOP框架了。AspectJ相比于Spring AOP功能更加强大，但是Spring AOP相对来说更简单。

如果我们的切面比较少，那么两者性能差异不大。但是，当切面太多的话，最好选择AspectJ，它比SpringAOP快很多。
