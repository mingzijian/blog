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
 - 授予权限

 - 撤销权限

 - 查看权限




#### 参考
 - https://dev.mysql.com/doc/
 - https://www.mysqlzh.com/
 - https://www.cnblogs.com/wangyueping/p/11258028.html