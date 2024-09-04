CentOS 环境变量
-------------------------

```
# 显示全部环境变量
> env
# 查看环境变量
> echo $PATH
# 编辑配置环境变量
> /bin/vim /etc/profile
# 使编辑后的配置立即生效
> source /etc/profile

```

```
# root 用户环境变量
> vim /root/.bash_profile
# username 用户环境变量
> vim /homt/username/.bash_profile
```



### 参考

- [Linux-source命令详解--水车](https://www.cnblogs.com/shuiche/p/9436126.html)