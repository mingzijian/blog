项目管理工具软件-Maven
---------------------
## Maven 私服
### Maven 私服简介
Nexus是一个强大的Maven仓库管理器，它极大的简化了本地内部仓库的维护和外部仓库的访问。
#### Nexus 仓库
 - hosted，本地仓库
   - Releases 稳定版本库（生产环境使用）
   - Snapshots 临时版本库 （开发&测试环境使用）
   - 3rdParty 第三方库 （管理中央仓库中没有的第三方包）
 - proxy，代理仓库
 - group，仓库组

### Maven 私服搭建 
#### 下载 Nexus Repository OSS
官方下载：https://help.sonatype.com/repomanager3/download
 - Unix : https://download.sonatype.com/nexus/3/latest-unix.tar.gz
 - Windows : https://download.sonatype.com/nexus/3/latest-win64.zip
 - OSX : https://download.sonatype.com/nexus/3/latest-mac.tgz
#### 安装

#### 启动

#### 配置

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

#### maven setting.xml 配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <pluginGroups></pluginGroups>

  <proxies></proxies>

  <servers>
    <server>
      <id>ming-deploy-release</id>
      <username>username</username>
      <password>password</password>
    </server>
    <server>
      <id>ming-deploy-snapshot</id>
      <username>username</username>
      <password>password</password>
    </server>
  </servers>

  <mirrors>
    <mirror>
      <id>nexus</id>
      <mirrorOf>*</mirrorOf>
      <url>http://192.168.20.9:8081/nexus/content/groups/public/</url>
    </mirror>
    <mirror>
      <id>huaweicloud</id>
      <mirrorOf>*</mirrorOf>
      <url>https://mirrors.huaweicloud.com/repository/maven/</url>
    </mirror>
  </mirrors>

  
  <profiles>
	<profile>
		<id>ming</id>
		<repositories>  
			<repository>
				<id>ming-repository</id>
				<releases>
					<enabled>true</enabled>
					<updatePolicy>always</updatePolicy>
				</releases>
				<snapshots>
					<enabled>true</enabled>
					<updatePolicy>always</updatePolicy>
				</snapshots>
				<url>http://192.168.20.9:8081/nexus/content/groups/public/</url>
			</repository>
		</repositories>

		<pluginRepositories>
			<pluginRepository>
			  <id>central</id>
			  <url>http://192.168.20.9:8081/nexus/content/groups/public/</url>
			  <releases>
			    <enabled>true</enabled>
			    <updatePolicy>always</updatePolicy>
			  </releases>
			  <snapshots>
			    <enabled>true</enabled>
			    <updatePolicy>always</updatePolicy>
			  </snapshots>
			</pluginRepository>
		</pluginRepositories>
	</profile>
  </profiles>
  
  <activeProfiles>
    <activeProfile>ming</activeProfile>
  </activeProfiles>

</settings>

```
#### 工程 pom.xml 配置
```xml
<project ...>
    ...
    <properties>
        <!-- maven仓库地址 -->
        <mvn.repositories>http://192.168.20.9:8081/nexus/content/repositories</mvn.repositories>
        ...
    </properties>
    ...
    <distributionManagement>
        <repository>
            <id>ming-deploy-release</id>
            <url>${mvn.repositories}/releases/</url>
        </repository>
        <snapshotRepository>
            <id>ming-deploy-snapshot</id>
            <url>${mvn.repositories}/snapshots/</url>
        </snapshotRepository>
    </distributionManagement>
</project>
```