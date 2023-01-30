CentOS 磁盘挂载
-------------------------

### 本地磁盘挂载

将 /dev/hda1 挂在 /mnt 之下

```shell
mount /dev/hda1 /mnt
```


### 网络磁盘挂载（文件共享）
示例：
将 
【192.168.20.100】上的  /data/share 目录 
挂载到 
【192.168.20.101】上的  /data/link-share 目录 
下

#### 服务端【192.168.20.100】（来源）
NFS : 网络文件系统(Network File System)
rpcbind : 通用地址和RPC程序号码之间的映射器

```shell
# 安装服务依赖
yum install -y nfs-utils rpcbind

# 设置服务开机启动
chkconfig nfs on 
chkconfig rpcbind on

# 启动服务（注意先后顺序）
service rpcbind start 
service nfs start

# 创建共享目录
mkdir /data/share

# 配置共享文件 
vim /etc/exports
# 将`/data/share`目录共享给客户端`192.168.20.101`
/data/share 192.168.20.101(rw,no_root_squash)

# 执行全部挂载/卸载
exportfs -a
# 显示共享目录
exportfs -v
# 查看挂载情况
showmount -e

```

#### 客户端【192.168.20.101】（目标）
```shell
# 安装服务依赖
yum install -y nfs-utils rpcbind

# 设置服务开机启动
chkconfig nfs on 
chkconfig rpcbind on

# 启动服务（注意先后顺序）
service rpcbind start 
service nfs start

# 显示NFS服务器的输出清单
showmount -e 192.168.20.100

# 创建映射目录
mkdir /data/link-share

# 手动挂载
mount -t nfs 192.168.20.100:/data/share /data/link-share

# 配置挂载文件（开机自动挂载）
vim /etc/fstab 
192.168.20.100:/data/share /data/link-share nfs rw,tcp,intr 0 1

```

### 参考

 - [Linux mount命令](https://www.runoob.com/linux/linux-comm-mount.html)
 - [rpcbind说明](http://linux.51yip.com/search/rpcbind)