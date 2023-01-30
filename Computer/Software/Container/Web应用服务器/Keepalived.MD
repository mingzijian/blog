

### 简介
Keepalived是一个用C语言编写的路由软件，该项目的主要目标是为Linux系统和基于Linux的基础设施提供简单而健壮的负载平衡和高可用性。

### 快速开始
#### 下载
```
> cd /usr/local/src
> wget https://www.keepalived.org/software/keepalived-2.1.5.tar.gz
```
更多版本 https://www.keepalived.org/download.html
#### 解压
```
> tar -zxvf keepalived-2.1.5.tar.gz
```
#### 安装
```
> cd keepalived-2.1.5
> ./configure --prefix=/usr/local/keepalived
> make && make install
```
#### 配置
##### 将 keepalived 安装成 Linux 系统服务
```
> mkdir /etc/keepalived
> cp /usr/local/keepalived/etc/keepalived/keepalived.conf /etc/keepalived/
```
##### 复制 keepalived 服务脚本到默认的地址
```
> cp /usr/local/keepalived/etc/rc.d/init.d/keepalived /etc/init.d/
> cp /usr/local/keepalived/etc/sysconfig/keepalived /etc/sysconfig/
> ln -s /usr/local/sbin/keepalived /usr/sbin/
> ln -s /usr/local/keepalived/sbin/keepalived /sbin/
```

#####  修改 Keepalived 配置文件
```
> vi /etc/keepalived/keepalived.conf
```

##### 设置 keepalived 服务开机启动
```
> chkconfig keepalived on
```

#### 启动/停止
- 启动
`service keepalived start`
- 停止
`service keepalived stop`


### 参考
- https://www.keepalived.org