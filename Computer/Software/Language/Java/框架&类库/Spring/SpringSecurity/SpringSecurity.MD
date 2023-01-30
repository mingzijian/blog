Spring Security
------------------------
Spring Security是一个功能强大且高度可定制的身份验证和访问控制框架。
它是保护基于Spring的应用程序的事实标准。

Spring Security是一个专注于为Java应用程序提供身份验证和授权的框架。
与所有Spring项目一样，Spring安全性的真正威力在于它可以很容易地扩展以满足定制需求。

## 介绍

### 前提
Java8

### 社区
https://github.com/spring-projects/spring-security/issues

### 获取
#### 发行版本
MAJOR.MINOR.PATCH
 - MAJOR 重要版本 （重大更改，主动）
 - MINOR 次要版本 （功能增强，被动）
 - PATCH 修复版本 （完全兼容）


#### 搭配Maven使用

```xml

<properties>
    <!-- ... -->
    <spring-security.version>5.4.6</spring-security.version>
</dependencies>

<dependencies>
    <!-- ... other dependency elements ... -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
</dependencies>
```

#### 搭配Gradle使用

```groovy
dependencies {
    compile "org.springframework.boot:spring-boot-starter-security"
}

ext['spring-security.version']='5.4.6'
```

### 特性
#### 授权验证
##### 认证支持
Spring Security提供了用于验证用户的内置支持。

##### 密码存储
Spring Security的PasswordEncoder接口用于执行密码的单向转换，以便安全地存储密码。

#### 漏洞防范
##### CSRF
Cross-site request forgery (CSRF) 跨站请求伪造。

##### 安全HTTP响应头
有许多HTTP响应头可用于提高web应用程序的安全性。

##### HTTP
所有基于HTTP的通信（包括静态资源）都应使用TLS进行保护。

### 示例
 [boot示例-oauth2login](https://github.com/spring-projects/spring-security/tree/5.4.6/samples/boot/oauth2login)

## 特性

 - 全面和可扩展的身份验证和授权支持
 - 攻击防御（session fixation, clickjacking, cross site request forgery）
 - Servlet API集成
 - 与Spring Web MVC的可选集成
 - 更多

### 相关概念
#### Session fixation (固定Session)
会话固定攻击（session fixation attack）是利用应用系统在服务器的会话ID固定不变机制，借助他人用相同的会话ID获取认证和授权，然后利用该会话ID劫持他人的会话以成功冒充他人，造成会话固定攻击。

##### 防范
Session fixation攻击的根本原因就是直接利用了链接发送过来的sessionID生成session，并直接将这个sessionID返回。
解决方法也很简单，类似登录这种有敏感信息的接口重新生成（打破固定）一个新的sessionID。

#### ClickJacking 点击劫持
点击劫持（ClickJacking）是一种视觉上的欺骗手段。

大概有两种方式：
 - 1 攻击者使用一个透明的iframe，覆盖在一个网页上，然后诱使用户在该页面上进行操作，此时用户将在不知情的情况下点击透明的iframe页面；
 - 2 攻击者使用一张图片覆盖在网页上，遮挡网页原有位置的含义；

#### Cross-site request forgery (CSRF) 跨站请求伪造
跨站请求伪造，也被称为“One Click Attack”或者Session Riding，通常缩写为CSRF或者XSRF，是一种对网站的恶意利用。

##### 区别XSS
尽管听起来像跨站脚本（XSS），但它与XSS非常不同，XSS利用站点内的信任用户，而CSRF则通过伪装来自受信任用户的请求来利用受信任的网站。
与XSS攻击相比，CSRF攻击往往不大流行（因此对其进行防范的资源也相当稀少）和难以防范，所以被认为比XSS更具危险性。

##### 防范
目前防御 CSRF 攻击主要有三种策略：
 - 1. 验证 HTTP Referer 字段；
 - 2. 在请求参数中添加 token 并验证；
 - 3. 在 HTTP 头中自定义属性并验证

## 产品
- Spring Security Kerberos
- Spring Security OAuth
- Spring Security SAML


### 参考
 - [spring-security](https://spring.io/projects/spring-security)