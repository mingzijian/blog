安装maven私服nexus
--------------------------------

```
# 下载
wget https://download.sonatype.com/nexus/oss/nexus-2.14.20-02-bundle.tar.gz
# 解压
tar -zxvf nexus-2.14.20-02-bundle.tar.gz

# 修改配置（按需）
vim ./nexus-2.14.20-02/conf/nexus.properties


# RUN_AS_USER 不推荐使用root账户，可以创建一个nexus账户
vim ./nexus-2.14.20-02/bin/nexus
RUN_AS_USER=root

vim /etc/profile
export RUN_AS_USER=root


# 启动
./nexus-2.14.20-02/bin/nexus start
****************************************
WARNING - NOT RECOMMENDED TO RUN AS ROOT
****************************************
Starting Nexus OSS...
Started Nexus OSS.

```

### 访问
#### 默认URL
http://{ip}:8081/nexus
#### 默认账号密码
admin/admin123

### 下载
[Download Archives - Repository Manager OSS (sonatype.com)](https://help.sonatype.com/repomanager2/download/download-archives---repository-manager-oss)





