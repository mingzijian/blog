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

##### [项目插件管理](https://maven.apache.org/plugin-tools/plugin-management.html)

###### org.apache.maven.plugins

- [maven-antrun-plugin](http://maven.apache.org/plugins/maven-antrun-plugin/) 
运行Ant任务

- [maven-assembly-plugin](https://maven.apache.org/plugins/maven-assembly-plugin/)
将项目输出合并到单个可分发的归档文件中
- [maven-changes-plugin](https://maven.apache.org/plugins/maven-changes-plugin/)
通知用户 项目不同版本之间发生的更改
- [maven-checkstyle-plugin](https://maven.apache.org/plugins/maven-checkstyle-plugin/)
生成代码检查报告
- [maven-clean-plugin](https://maven.apache.org/plugins/maven-clean-plugin/)
清除。删除在项目目录中生成的文件
-  [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/)
编译项目的源代码
- [maven-dependency-plugin](https://maven.apache.org/plugins/maven-dependency-plugin/)
依赖管理。将工件从本地或远程存储库复制和/或解包到指定位置
- [maven-deploy-plugin](http://maven.apache.org/plugins/maven-deploy-plugin/)
部署。用于将工件添加到远程仓库中，以便与其他开发人员和项目共享
- [maven-docck-plugin](http://maven.apache.org/plugins/maven-docck-plugin/)
检查项目是否符合插件文档标准
- [maven-ear-plugin](https://maven.apache.org/plugins/maven-ear-plugin/)
生成 javaee EAR 
- [maven-enforcer-plugin](http://maven.apache.org/enforcer/maven-enforcer-plugin)
控制特定环境约束
- [maven-failsafe-plugin](https://maven.apache.org/surefire/maven-failsafe-plugin/)
故障保护
- [maven-gpg-plugin](http://maven.apache.org/plugins/maven-gpg-plugin/)
用GnuPG标记项目的所有附加构件
- [maven-help-plugin](https://maven.apache.org/plugins/maven-help-plugin/)
获取有关项目或系统的相关信息
- [maven-install-plugin](http://maven.apache.org/plugins/maven-install-plugin/)
安装。将工件安装到本地仓库中
- [maven-jar-plugin](https://maven.apache.org/plugins/maven-jar-plugin/)
构建jar
- [maven-javadoc-plugin](https://maven.apache.org/plugins/maven-javadoc-plugin/)
为指定的项目生成Javadoc
- [maven-jxr-plugin](http://maven.apache.org/jxr/maven-jxr-plugin/)
生成项目源的交叉引用
- [maven-project-info-reports-plugin](https://maven.apache.org/plugins/maven-project-info-reports-plugin/)
生成有关项目的报告信息
- [maven-release-plugin](http://maven.apache.org/maven-release/maven-release-plugin/)
用于用Maven发布一个项目。发布一个项目分为两个步骤：准备和执行。
- [maven-remote-resources-plugin](http://maven.apache.org/plugins/maven-remote-resources-plugin/)
用于从远程仓库检索资源的jar，处理这些资源，并将它们合并到使用Maven构建的jar中
- [maven-resources-plugin](https://maven.apache.org/plugins/maven-resources-plugin/)
将项目资源复制到输出目录
- [maven-scm-plugin](http://maven.apache.org/scm/maven-scm-plugin/)
对常用SCM命令提供独立于供应商的访问
- [maven-scm-publish-plugin](https://maven.apache.org/plugins/maven-scm-publish-plugin/)
将maven网站发布到任何受支持的scm
- [maven-shade-plugin](https://maven.apache.org/plugins/maven-shade-plugin/)
将工件打包到`uber-jar`中
```
在很多编程语言中会把super叫做uber （因为suber可能是关键字）， 比如管superman叫uberman。
uber-jar从字面上理解就是super-jar，这样的jar不但包含自己代码中的class ，也会包含一些第三方依赖的jar，也就是把自身的代码和其依赖的jar全打包在一个jar里面了，所以就很形象的称其为super-jar 。
```
- [maven-site-plugin](https://maven.apache.org/plugins/maven-site-plugin/)
为项目生成站点
- [maven-source-plugin](https://maven.apache.org/plugins/maven-source-plugin/)
创建当前项目源文件的jar存档
- [maven-toolchains-plugin](http://maven.apache.org/plugins/maven-toolchains-plugin/)
跨插件共享配置
- [maven-war-plugin](https://maven.apache.org/plugins/maven-war-plugin/)
打war包。收集web应用程序的所有工件依赖项、类和资源，并将它们打包到web应用程序存档中。


###### org.apache.rat
[apache-rat-plugin](http://creadur.apache.org/rat/apache-rat-plugin/)

###### org.codehaus.modello
[modello-maven-plugin](http://codehaus-plexus.github.io/modello/modello-maven-plugin/)

###### org.codehaus.mojo
[clirr-maven-plugin](http://www.mojohaus.org/clirr-maven-plugin/clirr-maven-plugin)
[findbugs-maven-plugin](https://gleclaire.github.io/findbugs-maven-plugin/)
[taglist-maven-plugin](http://mojo.codehaus.org/taglist-maven-plugin)

###### org.codehaus.plexus
[plexus-component-metadata](http://codehaus-plexus.github.io/plexus-containers/plexus-component-metadata/)

###### org.eclipse.m2e
lifecycle-mapping



### 参考
- http://maven.apache.org/plugins/
- https://maven.apache.org/plugin-tools/index.html
- https://blog.csdn.net/zhuralll112/article/details/83505487



