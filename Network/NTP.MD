NTP (Network Time Protocol) 网络时间协议
-----------------------------------------------------------------
NTP是用来同步计算机时间的一种协议。
它可以使计算机对其服务器或时钟源（如石英钟，GPS等等)做同步化，它可以提供高精准度的时间校正（LAN上与标准间差小于1毫秒，WAN上几十毫秒），且可介由加密确认的方式来防止恶毒的协议攻击。
时间按NTP服务器的等级传播。按照离外部UTC源的远近把所有服务器归入不同的Stratum（层）中。

### NTP Pool
pool.ntp.org 是一个以时间服务器的大虚拟集群为上百万的客户端提供可靠的 [易用的](https://www.ntppool.org/zh/use.html) 网络时间协议（NTP）服务的项目

NTP池正在为世界各地成百上千万的系统提供服务。 它是绝大多数主流Linux发行版和许多网络设备的默认“时间服务器” (请参阅 [服务供应商的信息](https://www.ntppool.org/zh/vendors.html)).

由于用户的数量庞大，我们需要更多的服务器。如果您有一台具有静态IP地址并且一直处于联网状态的服务器，请考虑 [将其加入我们的系统](https://www.ntppool.org/zh/join.html).

这个项目由 [Ask Bjørn Hansen](http://www.askask.com/) 与 [邮件列表](https://www.ntppool.org/mailinglists.html)中的一大群贡献者开发与维持. 这个系统的源代码可 [在此获取](http://github.com/abh/ntppool).

“总线”服务器的主机和带宽由 [Develooper网](http://develooper.com/) 和 [NetActuate](https://www.netactuate.com/)提供.

#### 洲际
- 非洲  [Africa](https://www.ntppool.org/zone/africa) — africa.pool.ntp.org (53)
- 南极洲  [Antarctica](https://www.ntppool.org/zone/antarctica) — antarctica.pool.ntp.org (0)
- 亚洲  [Asia](https://www.ntppool.org/zone/asia) — asia.pool.ntp.org (296)
- 欧洲  [Europe](https://www.ntppool.org/zone/europe) — europe.pool.ntp.org (2852)
- 南美洲  [North America](https://www.ntppool.org/zone/north-america) — north-america.pool.ntp.org (911)
- 大洋洲  [Oceania](https://www.ntppool.org/zone/oceania) — oceania.pool.ntp.org (137)
- 北美洲  [South America](https://www.ntppool.org/zone/south-america) — south-america.pool.ntp.org (62)

#### 国家（中国）
China — cn.pool.ntp.org
```
	server 0.cn.pool.ntp.org
	server 1.cn.pool.ntp.org
	server 2.cn.pool.ntp.org
	server 3.cn.pool.ntp.org
```

#### 企业
##### 阿里云
| 经典网络内网            | 专有网络VPC内网          | 公网            |
| :---------------------- | :----------------------- | :-------------- |
| -                       | ntp.cloud.aliyuncs.com   | ntp.aliyun.com  |
| ntp1.cloud.aliyuncs.com | ntp7.cloud.aliyuncs.com  | ntp1.aliyun.com |
| ntp2.cloud.aliyuncs.com | ntp8.cloud.aliyuncs.com  | ntp2.aliyun.com |
| ntp3.cloud.aliyuncs.com | ntp9.cloud.aliyuncs.com  | ntp3.aliyun.com |
| ntp4.cloud.aliyuncs.com | ntp10.cloud.aliyuncs.com | ntp4.aliyun.com |
| ntp5.cloud.aliyuncs.com | ntp11.cloud.aliyuncs.com | ntp5.aliyun.com |
| ntp6.cloud.aliyuncs.com | ntp12.cloud.aliyuncs.com | ntp6.aliyun.com |
| -                       | -                        | ntp7.aliyun.com |

参考 https://help.aliyun.com/document_detail/92704.html

##### 腾讯云
| 内网 NTP 服务器      | 外网 NTP 服务器         |
| -------------------- | ----------------------- |
| time1.tencentyun.com | time1.cloud.tencent.com |
| time2.tencentyun.com | time2.cloud.tencent.com |
| time3.tencentyun.com | time3.cloud.tencent.com |
| time4.tencentyun.com | time4.cloud.tencent.com |
| time5.tencentyun.com | time5.cloud.tencent.com |

参考 https://cloud.tencent.com/document/product/213/30392

##### 微软
`time.windows.com`

##### 谷歌
`time.google.com`

##### 苹果
`time.apple.com`

### NTP 相关命令
以CentOS7为例。

#### NTP Server (服务器-授时)

#### NTP Client (客户端-对时)

```shell
# 检查已安装的ntp相关组件
> yum list installed|grep ntp
<
ntp.x86_64                              4.2.6p5-29.el7.centos.2        @updates 
ntpdate.x86_64                          4.2.6p5-29.el7.centos.2        @updates 

# 安装ntp
> yum install -y ntp

# 安装ntpdate
> yum install -y ntpdate

# 检查ntpd运行状态 (start, stop, restart, try-restart, reload, force-reload, status)
> service ntpd status
<
● ntpd.service - Network Time Service
   Loaded: loaded (/usr/lib/systemd/system/ntpd.service; enabled; vendor preset: disabled)
   Active: active (running) since Thu 2020-10-29 14:25:25 CST; 2h 36min ago
...

```

```shell
# 当前NTP状态报告
> ntpstat
synchronised to NTP server (169.254.0.79) at stratum 4
   time correct to within 72 ms
   polling server every 256 s

```

```shell
> ntpq -p
     remote           refid      st t when poll reach   delay   offset  jitter
==============================================================================
*169.254.0.79    100.67.190.132   3 u   95  256  377    1.099   -1.043   0.346
-169.254.0.80    100.67.190.210   3 u  220  256  377    1.162   -8.044   0.799
-169.254.0.81    100.67.190.132   3 u   65  256  377    1.106   -1.775   0.147
+169.254.0.82    9.48.39.77       3 u  230  256  377    1.066   -1.097   0.392
+169.254.0.83    100.67.190.210   3 u  182  256  377    1.171   -0.975   0.160

```
- `remote` 即远端ntp server源的IP或主机名称
  - `+`表示下次优先使用的源
  - `*`表示当前正在使用的源
- `refid` 参考的上一层NTP主机的地址
- `st` 即stratum阶层，值越小表示ntp serve的精准度越高
- `t`
- `when` 指出从轮询源开始已过去的时间（秒）
- `poll` 指出轮询间隔时间。该值会根据本地时钟的精度相应增加
- `reach` 是一个八进制数字，指出源的可存取性。值 377 表示源已应答了前八个连续轮询
- `delay` 网络传输过程中延迟的时间
- `offset` 时间补偿
- `jitter` Linux系统时间与BIOS硬件时间的差异时间

```shell
# 设置开机启动ntpd
> systemctl enable ntpd.service
```

##### 配置文件
- `/etc/ntp.conf`
