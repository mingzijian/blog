JDK
------
Java Development Kit (JDK) 是Sun公司（已被Oracle收购）针对Java开发员的软件开发工具包。自从Java推出以来，JDK已经成为使用最广泛的Java SDK（Software development kit）。


### 文档
- [JDK1.6-zh](https://tool.oschina.net/apidocs/apidoc?api=jdk-zh)
- [OracleJavaSE14-en](https://docs.oracle.com/en/java/javase/14/)

### CentOS配置JDK

#### 下载

```shell
wget https://repo.huaweicloud.com/java/jdk/8u181-b13/jdk-8u181-linux-x64.tar.gz
```

#### 解压
```shell
tar -zxvf jdk-8u181-linux-x64.tar.gz
# 移动到指定目录
mv jdk1.8.0_181/  /usr/local/
```

#### 配置
```shell
vim /etc/profile
```
添加以下配置
```
# Java
export JAVA_HOME=/usr/local/jdk1.8.0_181
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib
```
```shell
# 使变更配置生效
source /etc/profile
# 验证
> java -version
<
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)

```

### 镜像
- [华为云](https://repo.huaweicloud.com/java/jdk/8u181-b13/) 
- [Oracle官方](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

### 国内 JDK 发行版
- [阿里云-龙井](https://www.aliyun.com/product/dragonwell)
- [华为云-毕昇](https://www.huaweicloud.com/kunpeng/software/bishengjdk.html)
- [腾讯云-Kona](https://cloud.tencent.com/product/tkjdk)

### 参考

- [openjdk](http://openjdk.java.net/projects/jdk/)
- [oracle javase](https://docs.oracle.com/en/java/javase/)
- [oracle java download](https://www.oracle.com/java/technologies/oracle-java-archive-downloads.html)