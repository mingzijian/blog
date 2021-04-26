Tomcat
---------

Tomcat是JavaServlet、JSP、JavaEL和JavaWebSocket技术的开源实现。

### 架构

 ![Tomcat_Architecture](https://gitee.com/mingzijian/resources/raw/master/picgo/image-20210426154315543.png)

```
→ Server 
  → Service
    → Container
      → Engine
        → Host
          → Context
            → Wrapper
              → Servlet
```

### 文件结构
以 `apache-tomcat-9.0.16` 为例：

```
bin/ 二进制（可执行）
  bootstrap.jar  引导启动（程序入口）

  catalina.bat
  catalina.sh  服务的启动/停止脚本（检查设置环境变量，内部调用setclasspath.sh）
  catalina-tasks.xml  Ant任务
  
  ciphers.bat
  ciphers.sh  加密
  
  commons-daemon.jar  公共守护程序
  commons-daemon-native.tar.gz
  
  configtest.bat
  configtest.sh  配置测试
  
  daemon.sh  公共守护程序包装脚本
  
  digest.bat
  digest.sh  指定密码摘要算法（内部调用tool-wrapper.sh）
  
  service.bat  Windows服务
  
  setclasspath.bat
  setclasspath.sh  设置类路径（环境变量）
  
  shutdown.bat
  shutdown.sh  停止（内部调用catalina.sh）
  
  startup.bat
  startup.sh  启动（内部调用catalina.sh）
  
  tcnative-1.dll  本地调用优化（win）
  tomcat9.exe  服务可执行程序（win）
  tomcat9w.exe  图形界面可执行程序（win）
  
  tomcat-juli.jar  日志实现（Java Util Logging Implements）
  
  tomcat-native.tar.gz  本地优化
  
  tool-wrapper.bat
  tool.wrapper.sh  命令行工具包装器（org.apache.catalina.startup.Tool）
  
  version.bat
  version.sh  版本信息（Tomcat版本/OS版本/JVM版本）
  
conf/  配置
  Catalina/
    localhost/
  catalina.policy  政策
  catalina.properties  属性配置
  
  context.xml  上下文配置（监视变更，重新加载）
  
  jaspic-providers.xml  第三方身份验证集成
  jaspic-providers.xsd
  
  logging.properties  日志属性配置
  
  server.xml  服务主配置（启动时加载）
  
  tomcat-users.xml  用户配置
  tomcat-users.xsd
  
  web.xml  WEB程序配置（启动时加载）
  
lib/  依赖库
  annotations-api.jar
  
  catalina.jar  Servlet容器实现
  catalina-ant.jar  Ant任务
  catalina-ha.jar  高可用
  catalina-storeconfig.jar  从当前状态生成XML配置
  catalina-tribes.jar  组内通信
  
  ecj-4.9.jar  Eclipse的Java编译器（Eclipse Compiler for Java）
  
  el-api.jar  表达式API
  
  jasper.jar  JSP编译器和运行时
  jasper-el.jar  JSP编译器表达式实现
  
  jsp-api.jar
  servlet-api.jar
  
  tomcat-api.jar
  tomcat-coyote.jar  连接器（郊狼框架）
  tomcat-dbcp.jar  数据库连接池（Database Connection Pool）
  
  tomcat-i18n-cs.jar 捷克语
  tomcat-i18n-de.jar 德语
  tomcat-i18n-es.jar 西班牙语
  tomcat-i18n-fr.jar 法语
  tomcat-i18n-ja.jar 日语
  tomcat-i18n-ko.jar 韩语
  tomcat-i18n-pt-BR.jar 葡萄牙语(巴西)
  tomcat-i18n-ru.jar 俄语
  tomcat-i18n-zh-CN.jar 华语(中国)
  
  tomcat-jdbc.jar  数据库连接池解决方案
  tomcat-jni.jar  本地接口（Java Native Interface）
  tomcat-util.jar  工具包
  tomcat-util-scan.jar  包扫描工具
  tomcat-websocket.jar  WebSocket实现
  websocket-api.jar
  

logs/  日志
  catalina.out  控制台输出
  
temp/  临时
  safeToDelete.tmp  安全删除
  
webapps/  应用

work/  工作
  Catalina/
    localhost/
    
LICENSE  许可证
README.md  
RELEASE-NOTES  发行说明
```


### 组件

 - Server
 - Service
 - Connector
 - Engine
 - Host
 - Context
 - Wrapper

### 容器
 - Engine
 - Host
 - Context
 - Wrapper

### 配置


#### 参考
 - [apache tomcat]https://tomcat.apache.org
 - [Tomcat 组件简介
 - [Tomcat 启动](https://www.cnblogs.com/fantiantian/p/3638016.html)