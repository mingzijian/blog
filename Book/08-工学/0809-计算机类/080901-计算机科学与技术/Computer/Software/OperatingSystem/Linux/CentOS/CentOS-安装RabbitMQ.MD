CentOS 安装 RabbitMQ
-----------------------------------
CentOS 版本：CentOS Linux release 7.4.1708 (Core)
RabbitMQ 版本：RabbitMQ 3.6.5
Erlang 版本：Erlang (SMP,ASYNC_THREADS,HIPE) (BEAM) emulator version 7.3


### 安装Erlang
```
# 查看版本（确定是否安装）
erl -version

# 下载（加速）
wget https://github.91chifun.workers.dev/https://github.com//rabbitmq/erlang-rpm/releases/download/v18.3.4.7/erlang-18.3.4.7-1.el7.centos.x86_64.rpm


# 安装
rpm -ivh erlang-18.3.4.7-1.el7.centos.x86_64.rpm 

```

### 安装socat
```
yum install -y socat
...
已安装:
  socat.x86_64 0:1.7.3.2-2.el7                                           
完毕！
```

### 安装RabbitMQ
```
# 查看版本
rabbitmqctl status|grep Console

# 下载
wget https://github.91chifun.workers.dev/https://github.com//rabbitmq/rabbitmq-server/releases/download/rabbitmq_v3_6_5/rabbitmq-server-3.6.5-1.noarch.rpm

# 安装
rpm -ivh rabbitmq-server-3.6.5-1.noarch.rpm


```

### 配置
```
#开机自启
chkconfig rabbitmq-server on

# 启动服务
rabbitmq-server start &
# 关闭服务
rabbitmq-server stop &

# 启动应用
rabbitmqctl start_app
# 关闭应用
rabbitmqctl stop_app
   

# 启用管理插件
rabbitmq-plugins enable rabbitmq_management

# 添加账号
rabbitmqctl add_user admin admin123
# 修改密码
rabbitmqctl  change_password  admin  admin123456
# 查看当前用户列表
rabbitmqctl  list_users
# 管理员授权
rabbitmqctl set_user_tags admin administrator

```

### 集群
Rabbitmq的集群是依附于erlang的集群来工作的,所以必须先构建起erlang的集群镜像。
Erlang的集群中各节点是经由过程一个magic cookie来实现的,这个cookie存放在 $home/.erlang.cookie 中。
erlang.cookie是erlang实现分布式的必要文件，erlang分布式的每个节点上要保持相同的.erlang.cookie文件，同时保证文件的权限是400,不然节点之间就无法通信。

```
# 同步 /var/lib/rabbitmq/.erlang.cookie
scp root@server-xxx:/var/lib/rabbitmq/.erlang.cookie /var/lib/rabbitmq/


# 加入集群
rabbitmqctl join_cluster rabbit@server1
# 移除集群
rabbitmqctl forget_cluster_node rabbit@server1

# 查看
rabbitmqctl cluster_status
```

### 参考

- [Clustering Guide — RabbitMQ](https://www.rabbitmq.com/clustering.html)