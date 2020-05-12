Docker配置-网络模式
-----------------------

|  Docker网络模式   |   配置  |  说明   |
| --- | --- | --- |
|  host模式   |  –net=host   |  容器和宿主机共享Network namespace。   |
|  container模式   |   –net=container:NAME_or_ID  | 容器和另外一个容器共享Network namespace。 kubernetes中的pod就是多个容器共享一个Network namespace。    |
|  none模式   |  –net=none   |   容器有独立的Network namespace，但并没有对其进行任何网络设置，如分配veth pair 和网桥连接，配置IP等。  |
|  bridge模式   |  –net=bridge   |  （默认为该模式）   |

 - host模式
   容器将不会虚拟出自己的网卡，配置自己的IP等，而是使用宿主机的IP和端口。但是，容器的其他方面，如文件系统、进程列表等还是和宿主机隔离的。
使用host模式的容器可以直接使用宿主机的IP地址与外界通信，容器内部的服务端口也可以使用宿主机的端口，不需要进行NAT，host最大的优势就是网络性能比较好，但是docker host上已经使用的端口就不能再用了，网络的隔离性不好
![输入图片说明](https://images.gitee.com/uploads/images/2020/0512/151504_4fd3d1c3_536895.png "屏幕截图.png")


 - container模式  
![输入图片说明](https://images.gitee.com/uploads/images/2020/0512/151516_3396ca33_536895.png "屏幕截图.png")

 - none模式
![输入图片说明](https://images.gitee.com/uploads/images/2020/0512/151523_e4463ed3_536895.png "屏幕截图.png")  

 - bridge模式
![输入图片说明](https://images.gitee.com/uploads/images/2020/0512/151532_8be4a861_536895.png "屏幕截图.png")




#### 参考
 - https://www.jianshu.com/p/22a7032bb7bd