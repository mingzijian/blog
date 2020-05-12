Docker配置-网络模式
-----------------------
|  Docker网络模式   |              配置          |  说明 |
| ----------------- | -------------------------- | ----- |
|  host模式         |–net=host                   | 容器和宿主机共享Network namespace。|
|  container模式    |–net=container:NAME_or_ID   | 容器和另外一个容器共享Network namespace。 kubernetes中的pod就是多个容器共享一个Network namespace。  |
|  none模式         |–net=none                   | 容器有独立的Network namespace，但并没有对其进行任何网络设置，如分配veth pair 和网桥连接，配置IP等。 |
|  bridge模式       |–net=bridge                 |（默认为该模式） |

 - host模式
![host模式](https://images.gitee.com/uploads/images/2020/0512/151504_4fd3d1c3_536895.png "屏幕截图.png")

 - container模式  
![container模式](https://images.gitee.com/uploads/images/2020/0512/151516_3396ca33_536895.png "屏幕截图.png")

 - none模式
![none模式](https://images.gitee.com/uploads/images/2020/0512/151523_e4463ed3_536895.png "屏幕截图.png")  

 - bridge模式
![bridge模式](https://images.gitee.com/uploads/images/2020/0512/151532_8be4a861_536895.png "屏幕截图.png")




#### 参考
 - https://www.jianshu.com/p/22a7032bb7bd