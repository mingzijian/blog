Docker应用-搭建MySQL主从
-----------------------------

## 准备工作
- 服务器（物理机/云主机/虚拟机）
- Docker 安装
- MySQL
  - 查看版本 (当前为：5.7.21)
   ```sql # 客户端查看
   mysql> select version();
    +------------+
    | version()  |
    +------------+
    | 5.7.29-log |
    +------------+
    1 row in set (0.03 sec)
  
   ```
   ```shell # 服务器查看
   $ mysql --help | grep Distrib
   mysql  Ver 14.14 Distrib 5.7.21, for Linux (x86_64) using  EditLine wrapper
   ```
   ```shell
   $ mysql --version
   mysql  Ver 14.14 Distrib 5.7.21, for Linux (x86_64) using  EditLine wrapper
   ```

## 开始搭建
 - 拉取 MySQL 镜像 (注意版本)
   ```shell 
   # 拉取
   $ docker pull mysql:5.7
   # 查看
   $ docker images|grep mysql
     mysql               5.7                 f965319e89de        10 days ago         448MB
   ```
 - 启动‘主’‘从’两个容器  (每个容器有其独立的ip，容器内部尽量使用mysql默认的3306端口)
   - 主(Master) 对外映射端口：3306 密码：123456
     ```shell
     docker run -p 3306:3306 --name mysql-master -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
     ```
   - 从(Slave)  对外映射端口：33062 密码：123456
     ```shell
     docker run -p 33062:3306 --name mysql-slave -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
     ```

 - 容器查看/停止/删除/启动/重启/进入
   - 查看所有正在运行容器 `docker ps`
   - 查看所有容器(包括未运行的) `docker ps -a`
   - 停止容器 `docker stop xxx` xxx为容器id或name
   - 删除容器 `docker rm xxx` xxx为容器id或name ，删除之前需先停止容器运行
   - 启动容器 `docker start xxx` 
   - 重启容器 `docker restart xxx` 
   - 进入容器 
      - `docker attach ...`
      - `docker exec ...`  退出容器终端，不会导致容器的停止

 - 配置‘主’‘从’两个容器
   - 主容器(Master)配置
      - 通过 `docker exec -it mysql-master /bin/bash` 命令进入到 Master 容器内部
      - 更换容器内软件源
        ```shell
        # 备份（习惯）
        $ cp /etc/apt/sources.list /etc/apt/sources.list_bak
        # 替换 (mirrors.ustc.edu.cn 为 中国科学技术大学源)
        $ sed -i 's/deb.debian.org/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
        ```
      - 容器内安装 vim `apt update` `apt install vim`
      - 编辑配置文件 `vim /etc/mysql/my.cnf` 添加以下配置
        ```properties
        [mysqld]
        server-id = 1 # 表示是本机的序号为1，同一局域网内注意要唯一
        log_bin = mysql-bin
        binlog_format = mixed
        expire_logs_days = 30 # 超过30天的 binlog 删除
        ```
      - 重启 mysql 服务使配置生效 `service mysql restart`
      
          如果报: “service: command not found” ，执行下： `yum install initscripts -y`
      - 重启 mysql 服务时会使得 docker 容器停止，我们还需要启动容器 `docker start mysql-master`
      - 创建数据同步用户并授权
      
         - 进入 MySQL 控制台 `mysql -u root -p`
         - 创建用户 
            ```sql
                mysql> CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
                Query OK, 0 rows affected (0.00 sec)
            ```
         - 授权 
            ```sql
                mysql> GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'slave'@'%';
                Query OK, 0 rows affected (0.00 sec)
            ```
      
   - 从容器(Slave)配置
      - 通过`docker exec -it mysql-slave /bin/bash`命令进入到Slave容器内部
      - 同主容器(Master)配置一样，在从容器内更换软件源，安装 vim
      - 编辑配置文件 `vim /etc/mysql/my.cnf` 添加以下配置
        ```shell
        [mysqld]
        # 表示是本机的序号为2，同一局域网内注意要唯一
        server-id = 2 
        # 以备当前 Slave 作为其它 Slave 的 Master 时使用
        log_bin = mysql-bin 
        # MIXED模式（MBR）一般的复制使用STATEMENT模式保存binlog，对于STATEMENT模式无法复制的操作使用ROW模式保存binlog，MySQL会根据执行的SQL语句选择日志保存方式。
        binlog_format = mixed 
        # 超过30天的 binlog 删除
        expire_logs_days = 30 
        ```
      - 同主容器(Master)一样，重启 mysql 服务和对应容器
   
   - 链接‘主’‘从’
     - 查看主（Master）状态信息 
        <br/>File 和 Position 字段的值后面将会用到，在后面的操作完成之前，需要保证 Master 库不能做任何操作，否则将会引起状态变化（File 和 Position 字段的值变化）
     ```sql
        mysql> show master status;
        +------------------+----------+--------------+------------------+-------------------+
        | File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
        +------------------+----------+--------------+------------------+-------------------+
        | mysql-bin.000001 |      609 |              |                  |                   |
        +------------------+----------+--------------+------------------+-------------------+
        1 row in set (0.04 sec)
     ```
     - 查看容器信息 `docker inspect xxx`  xxx 为容器 ID/NAMES
       - 查看 mysql-master 容器ip地址信息 (172.17.0.2)
        ```shell
        $ docker inspect --format='{{.NetworkSettings.IPAddress}}' mysql-master
        172.17.0.2
        ```
       - 查看 mysql-slave 容器ip地址信息 (172.17.0.3)
        ```shell
        $ docker inspect --format='{{.NetworkSettings.IPAddress}}' mysql-slave
        172.17.0.3
        ```
     - 配置‘从’链向‘主’
        ```sql
        mysql> change master to master_host='172.17.0.2', master_user='slave', master_password='123456', master_port=3306, master_log_file='mysql-bin.000001', master_log_pos= 609, master_connect_retry=30;
        Query OK, 0 rows affected (0.08 sec)
        ```
     - 查看‘从’状态信息  `show slave status`
       <br/>![‘从’状态信息1](https://images.gitee.com/uploads/images/2020/0509/125014_0a858e10_536895.png "屏幕截图.png")
       - Slave_IO_Running: I/O线程是否被启动并成功地连接到主服务器上
       - Slave_SQL_Running: SQL线程是否被启动
     - 开启主从复制 `start slave`
        ```sql
        mysql> start slave;
        Query OK, 0 rows affected (0.00 sec)
        ```
     - 再次查看‘从’状态信息  `show slave status`
       <br/>![‘从’状态信息2](https://images.gitee.com/uploads/images/2020/0509/125634_c13e4c6e_536895.png "屏幕截图.png")
       <br>SlaveIORunning 和 SlaveSQLRunning 都是 Yes ，主从复制已启动，可以进一步测试数据同步是否能成功。


   - 主从复制排错
     - 网络方面 
       检查 `ip` `port` 等配置
     - 权限方面 
       检查账号/密码/授权等
     - 其他
       - 检查 Master 的 Position

   - 主从复制测试
     - 在 Master 上创建一个数据库，然后检查 Slave 上是否存在此数据库
     ```sql
        # 在 Master 上创建一个库
        mysql> create database js;
        Query OK, 1 row affected (0.01 sec)
     
        # 在 Master 上能看到该库
        mysql> show databases like 'js';
        +---------------+
        | Database (js) |
        +---------------+
        | js            |
        +---------------+
        1 row in set (0.04 sec)
     
        # 在 Slave 上也能看到该库
        mysql> show databases like 'js';
        +---------------+
        | Database (js) |
        +---------------+
        | js            |
        +---------------+
        1 row in set (0.04 sec)
     
     ``
     ```


#### 参考
 - https://www.cnblogs.com/songwenjie/p/9371422.html

