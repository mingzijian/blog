CentOS 修改主机名
---------------------------
### CentOS 6
```shell
# 查看当前的hostname
> hostname
centos6

# 设置当前的hostname(临时生效）
> hostname centos6_newname
# 再次查看
> hostname
centos6_newname
```

### CentOS 7
```shell
# 查看当前的hostname
> hostname
centos7

# 设置当前的hostname(永久生效）
> hostnamectl set-hostname centos7_newname
# 再次查看
> hostname
centos7_newname
```
