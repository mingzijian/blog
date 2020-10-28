# CentOS升级apache-maven-3.6.3

### 查看当前版本
$ mvn -version
```
Apache Maven 3.5.2 ................
Maven home: /usr/share/apache-maven
...
```

cd /usr/local
### 下载新版本
$ wget http://mirror.bit.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz

[更多版本](http://maven.apache.org/download.cgi)

### 解压
$ tar -zxvf apache-maven-3.6.3-bin.tar.gz

### 移出老版本
$ mv /usr/share/apache-maven /usr/share/apache-maven-3.5.2
### 移入新版本
$ cp -r /usr/local/apache-maven-3.6.3 /usr/share/apache-maven

### 查看当前版本
$ mvn -version
```
Apache Maven 3.6.3 ................
Maven home: /usr/share/apache-maven
...
```
