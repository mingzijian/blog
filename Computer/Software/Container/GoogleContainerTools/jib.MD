jib
----
容器化你的Java应用程序

Jib无需Docker守护程序即可为Java应用程序构建优化的Docker和OCI镜像。
它可以作为Maven与Gradle的插件 和 Java库使用。

### 目标
- 快（快速部署。 将应用程序分层，将依赖项与类分开。不必等待Docker重建整个Java应用程序-只需部署已更改的层。）
- 稳（用相同的内容重建容器镜像始终会生成相同的镜像）
- 轻（无守护程序，减少CLI依赖）


### 使用
#### maven
```xml
    ...
    <properties>
        <jib-maven-plugin.version>3.0.0</jib-maven-plugin.version>
        ...
    </properties>
    ...
    <build>
       <plugins>
           <plugin>
               <groupId>com.google.cloud.tools</groupId>
               <artifactId>jib-maven-plugin</artifactId>
               <version>${jib-maven-plugin.version}</version>
               ...
           </plugin>
           ...
       </plugins>
       ...
    <build>
    ...
```

### 参考

- [jib --github](https://github.com/GoogleContainerTools/jib)
- [jib-maven-plugin#quickstart](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#quickstart)