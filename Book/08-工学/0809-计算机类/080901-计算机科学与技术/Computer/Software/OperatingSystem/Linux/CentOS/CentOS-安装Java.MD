CentOS 安装Java
-------------------------
以centos8安装jdk-8为例

### 通过 yum/dnf 安装 (以jdk-8为例)
```shell

> dnf install -y java-1.8.0-openjdk.x86_64
...
Complete!

> dnf list java-*
Last metadata expiration check: 2:29:55 ago on Tue 27 Apr 2021 08:19:47 AM CST.
Installed Packages
java-1.8.0-openjdk.x86_64                                                                         1:1.8.0.292.b10-0.el8_3                                                      @appstream
java-1.8.0-openjdk-headless.x86_64                                                                1:1.8.0.292.b10-0.el8_3                                                      @appstream
Available Packages
java-1.8.0-openjdk-demo.x86_64                                                                    1:1.8.0.292.b10-0.el8_3                                                      appstream 
...
java-11-openjdk.x86_64                                                                            1:11.0.11.0.9-0.el8_3                                                        appstream 
...
java-latest-openjdk.x86_64                                                                        1:16.0.0.0.36-1.rolling.el8                                                  epel           
...

> java -version
openjdk version "1.8.0_292"
OpenJDK Runtime Environment (build 1.8.0_292-b10)
OpenJDK 64-Bit Server VM (build 25.292-b10, mixed mode)


```

### 通过二进制包安装 (以jdk-8为例)

[【推荐】华为云JDK镜像](https://mirrors.huaweicloud.com/java/jdk/)
[Java SE Downloads](https://www.oracle.com/java/technologies/javase-downloads.html)
[javase-jdk8-downloads](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)


```shell
# 下载
> wget https://mirrors.huaweicloud.com/java/jdk/8u181-b13/jdk-8u181-linux-x64.tar.gz

# 解压（建议目录/usr/local/）
> tar -zxvf jdk-8u181-linux-x64.tar.gz
> mv jdk1.8.0_181/ /usr/local/

# 配置环境变量（建议备份）
> cp /etc/profile /etc/profile.back;vim /etc/profile
export JAVA_HOME=/usr/local/jdk1.8.0_181
export JRE_HOME=${JAVA_HOME}
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

# 重新加载配置（使配置立即生效）
> source /etc/profile

# 验证
> java -version
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
```