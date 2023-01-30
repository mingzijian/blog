Redis 哨兵日志
---------------------

### 日志说明

| 状态                | 说明                              | 示例 |
| ------------------- | --------------------------------- | ---- |
| +sdown              | 添加 对某节点被主观下线（给定的实例现在处于主观下线状态。）           |      |
| -sdown              | 撤销 对某节点被主观下线（给定的实例已经不再处于主观下线状态。）           |      |
| +odown              | 添加 对某节点被客观下线（给定的实例现在处于客观下线状态。）           |      |
| -odown              | 撤销 对某节点被客观下线（给定的实例已经不再处于客观下线状态。）           |      |
| +config-update-from |                                   |      |
| +switch-master      | 添加 更新主节点。配置变更，主服务器的 IP 和地址已经改变。 **这是绝大多数外部用户都关心的信息。** |      |
| +slave              | 添加 一个新的从节点被发现并关联   |      |
| +sentinel           | 添加 一个新的哨兵节点被发现并关联 |      |
| +convert-to-slave   |                                   |      |
| +reset-master | 主服务器已被重置 |      |
| +failover-state-reconf-slaves |                                   |      |
| +failover-detected |                                   |      |
| +slave-reconf-inprog |                                   |      |
| +slave-reconf-done | 从服务器已经成功完成对新主服务器的同步。 |      |
| -dup-sentinel | 对给定主服务器进行监视的一个或多个 Sentinel 已经因为重复出现而被移除 —— 当 Sentinel 实例重启的时候，就会出现这种情况。 |      |
| +new-epoch | 当前的纪元（epoch）已经被更新。 |      |
| +try-failover | 一个新的故障迁移操作正在执行中，等待被大多数 Sentinel 选中（waiting to be elected by the majority）。 |      |
| +elected-leader | 赢得指定纪元的选举，可以进行故障迁移操作了。 |      |
| +failover-state-select-slave |                                   |      |
| no-good-slave | Sentinel 操作未能找到适合进行升级的从服务器。Sentinel 会在一段时间之后再次尝试寻找合适的从服务器来进行升级，又或者直接放弃执行故障转移操作。 |      |
| selected-slave | Sentinel 顺利找到适合进行升级的从服务器 |      |
| failover-state-send-slaveof-noone |                                   |      |
| failover-end-for-timeout | 故障转移因为超时而中止，不过最终所有从服务器都会开始复制新的主服务器 |      |
| failover-end | 故障转移操作顺利完成。所有从服务器都开始复制新的主服务器了。 |      |
| +tilt | 进入 tilt 模式 |  |
| -tilt | 退出 tilt 模式 |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |
|    |                                   |      |



```
1197:X 16 Mar 20:54:21.220 # -sdown sentinel a81cc58c7c6baf93c1c7953e2d00a82fac36e53f 10.0.0.47 26379 @ mymaster 10.0.0.47 6379
1197:X 16 Mar 20:54:21.220 # -sdown sentinel faf26aa784e6953319280bc813f59b12527ce0ca 10.0.0.47 26380 @ mymaster 10.0.0.47 6379
1197:X 17 Mar 22:28:51.114 # +sdown master mymaster 10.0.0.47 6379
1197:X 17 Mar 22:28:51.115 # +sdown sentinel a81cc58c7c6baf93c1c7953e2d00a82fac36e53f 10.0.0.47 26379 @ mymaster 10.0.0.47 6379
1197:X 17 Mar 22:28:51.115 # +sdown sentinel faf26aa784e6953319280bc813f59b12527ce0ca 10.0.0.47 26380 @ mymaster 10.0.0.47 6379
1197:X 17 Mar 22:29:01.115 # -sdown master mymaster 10.0.0.47 6379
1197:X 17 Mar 22:29:01.115 # -sdown sentinel a81cc58c7c6baf93c1c7953e2d00a82fac36e53f 10.0.0.47 26379 @ mymaster 10.0.0.47 6379
1197:X 17 Mar 22:29:01.115 # -sdown sentinel faf26aa784e6953319280bc813f59b12527ce0ca 10.0.0.47 26380 @ mymaster 10.0.0.47 6379


1205:X 16 Mar 20:53:51.865 # +config-update-from sentinel a81cc58c7c6baf93c1c7953e2d00a82fac36e53f 10.0.0.47 26379 @ mymaster 10.0.0.43 6379
1205:X 16 Mar 20:53:51.865 # +switch-master mymaster 10.0.0.43 6379 10.0.0.47 6379
1205:X 16 Mar 20:53:51.865 * +slave slave 10.0.0.43:6379 10.0.0.43 6379 @ mymaster 10.0.0.47 6379
1205:X 16 Mar 20:54:21.143 # -sdown sentinel 98c0cc2d5c95a0de0266daf842798b827b59cce9 10.0.0.43 26379 @ mymaster 10.0.0.47 6379
101783:X 16 Mar 20:54:21.194 # -sdown sentinel 98c0cc2d5c95a0de0266daf842798b827b59cce9 10.0.0.43 26379 @ mymaster 10.0.0.47 6379
1205:X 16 Mar 20:54:31.164 * +convert-to-slave slave 10.0.0.43:6379 10.0.0.43 6379 @ mymaster 10.0.0.47 6379
101783:X 17 Mar 22:28:51.809 # +sdown slave 10.0.0.43:6379 10.0.0.43 6379 @ mymaster 10.0.0.47 6379
101783:X 17 Mar 22:28:51.809 # +sdown sentinel 98c0cc2d5c95a0de0266daf842798b827b59cce9 10.0.0.43 26379 @ mymaster 10.0.0.47 6379
1205:X 17 Mar 22:28:52.030 # +sdown slave 10.0.0.43:6379 10.0.0.43 6379 @ mymaster 10.0.0.47 6379
1205:X 17 Mar 22:28:52.030 # +sdown sentinel 98c0cc2d5c95a0de0266daf842798b827b59cce9 10.0.0.43 26379 @ mymaster 10.0.0.47 6379
101783:X 17 Mar 22:29:01.122 # -sdown slave 10.0.0.43:6379 10.0.0.43 6379 @ mymaster 10.0.0.47 6379
101783:X 17 Mar 22:29:01.122 # -sdown sentinel 98c0cc2d5c95a0de0266daf842798b827b59cce9 10.0.0.43 26379 @ mymaster 10.0.0.47 6379
1205:X 17 Mar 22:29:01.136 # -sdown slave 10.0.0.43:6379 10.0.0.43 6379 @ mymaster 10.0.0.47 6379
1205:X 17 Mar 22:29:01.136 # -sdown sentinel 98c0cc2d5c95a0de0266daf842798b827b59cce9 10.0.0.43 26379 @ mymaster 10.0.0.47 6379
```

