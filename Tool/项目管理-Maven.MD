项目管理工具软件-Maven
---------------------
## Maven 私服
### Maven 私服搭建

### Maven 私服使用

#### 上传 第三方jar 至 Maven私服
```xml
<dependency>
    <groupId>it.sauronsoftware</groupId>
    <artifactId>jave</artifactId>
    <version>1.0.2</version>
</dependency>
```
 - 参考链接 http://mvn.localhost.com/nexus/#view-repositories;thirdparty~uploadPanel
 - 参考步骤
   - 以管理员身份登录Maven私服
   - 打开第三方包上传面板 `/nexus/#view-repositories;thirdparty~uploadPanel`
   - Select GAV Definition Source （配置需要上传的jar信息）
     - 选择GAV Definition: `GAV Parameters`
     - 填充GAV Parameters
       - Auto Guess:`√`
       - Group:`it.sauronsoftware`
       - Artifact:`jave`
       - Version:`1.0.2`
       - Packaging:`jar`


   - Select Artifact(s)for Upload （选择附件jar上传）
     - select 
     - add
     - upload
 - 步骤图示
![上传第三方jar](https://images.gitee.com/uploads/images/2020/0611/152244_d23375f5_536895.png "上传第三方jar.png")