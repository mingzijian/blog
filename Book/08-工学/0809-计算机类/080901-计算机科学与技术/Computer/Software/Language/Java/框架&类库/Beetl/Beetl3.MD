Beetl
---------
Beetl是Bee Template Language的缩写，它绝不是简单的另外一种模板引擎，而是新一代的模板引擎，它功能强大，性能良好，超过当前流行的模板引擎。而且还易学易用。---- ibeetl.com

#### 特性

- 功能完备：作为主流模板引擎，Beetl具有相当多的功能和其他模板引擎不具备的功能。适用于各种应用场景，从对响应速度有很高要求的大网站到功能繁多的CMS管理系统都适合。Beetl本身还具有很多独特功能来完成模板编写和维护，这是其他模板引擎所不具有的。
- 非常简单：类似Javascript语法和习俗，只要半小时就能通过半学半猜完全掌握用法。拒绝其他模板引擎那种非人性化的语法和习俗。同时也能支持html 标签，使得开发CMS系统比较容易
- 超高的性能：Beetl 远超过主流java模板引擎性能(引擎性能5-6倍于FreeMarker，2倍于JSP。参考附录），而且消耗较低的CPU。
- 易于整合：Beetl能很容易的与各种web框架整合，如Spring MVC，SpringBoot，ACT.JFinal，Struts，Nutz，Jodd，Servlet等。
- 扩展和个性化：Beetl支持自定义方法，格式化函数，虚拟属性，标签，和HTML标签. 同时Beetl也支持自定义占位符和控制语句起始符号也支持使用者完全可以打造适合自己的工具包。
- 模板引擎可以个性化定制,比如定制语法特性，定制语法实现。
- 可以扩展为脚本引擎，规则引擎，能定制引擎从而实现高级功能。

#### maven

```xml
<dependency>
  <groupId>com.ibeetl</groupId>
  <artifactId>beetl</artifactId>
  <version>${version}</version>
</dependency>
```
https://search.maven.org/artifact/com.ibeetl/beetl/3.3.2.RELEASE/jar



### 参考
- [Beetl3官方文档](https://www.kancloud.cn/xiandafu/beetl3_guide/1992542)
