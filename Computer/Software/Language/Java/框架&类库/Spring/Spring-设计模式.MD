Spring 框架中使用到的设计模式
---------------------------------------------

1. 工厂设计模式：
	Spring使用工厂模式通过BeanFactory和ApplicationContext创建bean对象。

2. 代理设计模式：
	Spring AOP功能的实现。

3. 单例设计模式：
	Spring中的bean默认都是单例的。

4. 模板方法模式：
	Spring中的jdbcTemplate、hibernateTemplate等以Template结尾的对数据库操作的类，它们就使用到了模板模式。

5. 包装器设计模式：
	我们的项目需要连接多个数据库，而且不同的客户在每次访问中根据需要会去访问不同的数据库。这种模式让我们可以根据客户的需求能够动态切换不同的数据源。

6. 观察者模式：
	Spring事件驱动模型就是观察者模式很经典的一个应用。

7. 适配器模式：
	Spring AOP的增强或通知（Advice）使用到了适配器模式、Spring MVC中也是用到了适配器模式适配Controller。

...