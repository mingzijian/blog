## 传统方式

### 传统的数据复制

![传统的数据复制方法](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/figure1.gif)



### 传统的上下文切换

![传统的上下文切换](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/figure2.gif)



## 零拷贝方式

### 单拷贝数据复制

![使用transferTo()复制数据](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/figure3.gif)

### 零拷贝上下文切换

![使用transferTo()时的上下文切换](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/figure4.gif)

### 零拷贝数据复制
![使用transferTo()和gather操作时的数据副本](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/figure5.gif)




## 参考
[通过零拷贝实现高效数据传输](https://developer.ibm.com/articles/j-zerocopy/)