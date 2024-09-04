Docker配置-网络模式
-----------------------
|  Docker网络模式   |              配置          |  说明 |
| ----------------- | -------------------------- | ----- |
|  host模式         |–net=host                   | 容器和宿主机共享Network namespace。|
|  container模式    |–net=container:NAME_or_ID   | 容器和另外一个容器共享Network namespace。 |
|  none模式         |–net=none                   | 容器有独立的Network namespace。 |
|  bridge模式       |–net=bridge                 | docker的默认网络模式。从docker0子网中分配一个IP给容器使用，并设置docker0的IP地址为容器的默认网关。 |

 - host模式<br/>
   ![host模式](https://images.gitee.com/uploads/images/2020/0512/151504_4fd3d1c3_536895.png "host模式.png")

 - container模式<br/>
   ![container模式](https://images.gitee.com/uploads/images/2020/0512/151516_3396ca33_536895.png "container模式.png")

 - none模式<br/>
   ![none模式](https://images.gitee.com/uploads/images/2020/0512/151523_e4463ed3_536895.png "none模式.png")  

 - bridge模式<br/>
   ![bridge模式](https://images.gitee.com/uploads/images/2020/0512/152106_5dd6a62d_536895.png "bridge模式.png")




#### 参考
 - https://www.jianshu.com/p/22a7032bb7bd