MySQL-用户与权限
-------------------------
#### 用户管理
 - 创建用户 `create user`
    ```
    # 指定用户 test 在 ip 127.0.0.1 上使用密码 123456 登录
    mysql> create user 'test'@'127.0.0.1' identified by '123456';
    Query OK, 0 rows affected (0.00 sec)

    # 指定用户 test 在以 192.168.4 开头的网段 ip 上使用密码 123456 登录
    mysql> create user 'test'@'192.168.4.%' identified by '123456';
    Query OK, 0 rows affected (0.00 sec)

    # 指定用户 test 在任意 ip 上使用密码 123456 登录
    mysql> create user 'test'@'%' identified by '123456';
    Query OK, 0 rows affected (0.00 sec)
    ```

 - 修改用户
   - 修改用户名称
    ```sql
    mysql> rename user 'test'@'127.0.0.1'  to 'test'@'%';
    Query OK, 0 rows affected (0.00 sec)
    ```
   - 修改用户密码
    ```sql
    # 重置用户 'test'@'%' 的密码为 12345678
    mysql> set password for 'test'@'%'=Password('12345678');
    Query OK, 0 rows affected (0.00 sec)
    ```

 - 查看用户
    ```
    mysql> select host,user from mysql.user where user = 'test';
    +-----------+------+
    | host      | user |
    +-----------+------+
    | 127.0.0.1 | test |
    +-----------+------+
    1 row in set (0.05 sec)
    ```

 - 删除用户 `drop user`
    ```
    mysql> drop user 'test'@'127.0.0.1';
    Query OK, 0 rows affected (0.00 sec)
    ```

 - 使用 `mysql -u 用户名 -p 密码 -P 端口号` (注意大小写，小写的`p`是指定密码 password，大写的`P`是指定端口号 Port)


#### 权限管理
 - 授予权限 `grant`
    ```sql
    # 授权 test 用户仅对 js.js_admin 文件进行查询、插入和更新操作
    mysql> grant select,insert,update on js.js_admin to 'test'@'%';
    Query OK, 0 rows affected (0.00 sec)
    
    # 授权 test 用户对所有数据库中文件进行查询、插入和更新操作
    mysql> grant select,insert,update on *.* to 'test'@'%';
    Query OK, 0 rows affected (0.00 sec)
    
    # 授权 test 用户对所有数据库中文件进行任何操作
    mysql> grant all privileges  on *.*  to 'test'@'%';
    Query OK, 0 rows affected (0.00 sec)
    
    ```

 - 撤销权限 `revoke`
    ```sql
    # 撤销 test 用户对 js.js_admin 文件的所有权限
    revoke all on js.js_admin from 'test'@'%';
    Query OK, 0 rows affected (0.00 sec)
    
    # 撤销 test 用户所有权限
    mysql> revoke all privileges on *.* from 'test'@'%';
    Query OK, 0 rows affected (0.00 sec)
    
    ```

 - 查看权限 `show grants for '用户'@'IP地址'`
    ```sql
    mysql> show grants for 'test'@'%';
    +---------------------------------------------------------------+
    | Grants for test@%                                             |
    +---------------------------------------------------------------+
    | GRANT USAGE ON *.* TO 'test'@'%'                              |
    | GRANT SELECT, INSERT, UPDATE ON `js`.`js_admin` TO 'test'@'%' |
    +---------------------------------------------------------------+
    2 rows in set (0.06 sec)
    ```
 - 刷新权限 
	`flush privileges;`

 - 权限列表

|权  限|作用范围|作  用|
| --- | --- | --- |
|all	|服务器	|所有权限|
|select	|表、列	|选择行  |
|insert	|表、列	|插入行  |
|update	|表、列	|更新行  |
|delete	|表		|删除行  |
|create	|数据库、表、索引|	创建 |
|drop	|数据库、表、视图|	删除 |
|reload		|服务器|	允许使用flush语句|
|shutdown	|服务器|	关闭服务         |
|process	|服务器|	查看线程信息     |
|file		|服务器|	文件操作         |
|grant option	|数据库、表、存储过程	|授权            |
|references		|数据库、表				|外键约束的父表  |
|index			|表					|创建/删除索引   |
|alter			|表					|修改表结构      |
|show databases	|服务器	|查看数据库名称 |
|super			|服务器	|超级权限       |
|create temporary tables	|表			|创建临时表 |
|lock tables				|数据库		|锁表       |
|execute					|存储过程	|执行       |
|replication client	|服务器	|允许查看主/从/二进制日志状态 |
|replication slave	|服务器	|主从复制|
|create view		|视图	|创建视图|
|show view			|视图	|查看视图|
|create routine	|存储过程	|创建存储过程|
|alter routine	|存储过程	|修改/删除存储过程|
|create user	|服务器	|创建用户                 |
|event			|数据库	|创建/更改/删除/查看事件  |
|trigger		|表		|触发器             |
|create tablespace	|服务器|	创建/更改/删除表空间/日志文件 |
|proxy				|服务器|	代理成为其它用户     |
|usage				|服务器|	没有权限            |

#### 参考

 - https://dev.mysql.com/doc/

   - MySQL5.7权限参考 https://dev.mysql.com/doc/refman/5.7/en/privileges-provided.html

   - MySQL8.0权限参考 https://dev.mysql.com/doc/refman/8.0/en/privileges-provided.html

 - https://www.mysqlzh.com/

 - https://www.cnblogs.com/wangyueping/p/11258028.html