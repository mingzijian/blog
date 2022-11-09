MySQL 忘记密码
-------------------------

### 忘记ROOT账户密码
1. 跳过授权表，编辑`/etc/my.cnf`配置文件，插入一行`skip-grant-tables`
`vim /etc/my.cnf`

其他可能的配置文件：
/etc/mysql/my.cnf
/etc/mysql/conf.d/mysql.cnf
/etc/mysql/conf.d/docker.cnf

```shell
[mysqld]
#跳过授权表
skip-grant-tables
```
2. 重启MySQL服务
`service mysqld restart`
3. 无密码直接进入MySQL控制台
`mysql -u root -p`
回车直接进入
4. 重新设置root账户密码
```mysql
mysql> alter user 'root'@'localhost' IDENTIFIED BY 'Root@123456';
mysql> exit
```
5. 启用授权表，编辑`/etc/my.cnf`配置文件，删除或注释行`skip-grant-tables`
`vim /etc/my.cnf`
```
[mysqld]
#跳过授权表
#skip-grant-tables
```
6. 输入密码进入MySQL控制台
`mysql -u root -p`
Enter password:
输入密码进入

### 忘记普通账户密码

1. 使用root账户进入MySQL控制台

   `mysql -u root -p`

2. 在MySQL控制台修改对应账户密码

   ```mysql
   mysql> alter user 'test'@'%' IDENTIFIED BY 'Test@123456';
   ```