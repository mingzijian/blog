Markdown 制图
------------------------

| 字母表示 | 含义                   |
| -------- | ---------------------- |
| TB       | 从上到下 Top -> Bottom |
| BT       | 从下到上               |
| LR       | 从左到右 Left -> Right |
| RL       | 从右到左               |

| 表述       | 说明         | 含义                                               |
| ---------- | ------------ | -------------------------------------------------- |
| id[文字]   | 矩形节点     | 表示过程                                           |
| id(文字)   | 圆角矩形节点 | 表示开始与结束                                     |
| id((文字)) | 圆形节点     | 表示连接。为避免流程过长或有交叉，可将流程切开成对 |
| id{文字}   | 菱形节点     | 表示判断、决策                                     |
| id>文字 ]  | 右向旗帜节点 |                                                    |

| 消息线类型 | 含义                     |
| ---- | -------------------------- |
| ->   | 无箭头的实线               |
| -->  | 无箭头的虚线               |
| ->>  | 有箭头的实线(主动发出消息) |
| -->> | 有箭头的虚线(响应) |
| -x | 末端为X的实线(主动发出异步消息) |
| --x | 有箭头的实线(以异步形式响应消息) |

### 示例

#### 流程图
```mermaid
graph TB

  id1(圆角矩形)--普通线-->id2[矩形];
  subgraph 子图
   id2==粗线==>id3{菱形}
   id3-.虚线.->id4>右向旗帜]
   id3--无箭头---id5((圆形))
  end
  
```

#### 架构图
```mermaid
graph TD

    subgraph SVN Architecture
    client1-->|read / write|SVN((SVN server))
    client2-->|read only|SVN
    client3-->|read / write|SVN
    client4-->|read only|SVN
    client5(...)-->SVN
    SVN---|store the data|sharedrive
    end

```

#### 饼图
```mermaid
pie
 title Pie Chart
 "Dogs" : 386
 "cats" : 567
 "rabbit" : 700
 "pig":365
 "tiger" : 15

```


#### 甘特图
```mermaid
gantt
 dateFormat  YYYY-MM-DD
 title     软件开发任务进度安排 
 excludes   weekends

 section 软硬件选型 
 硬件选择      :done,desc1, 2021-01-01,6w 
 软件设计      :active,desc2, after desc1,3w

 section 编码准备
 软件选择       :crit,done,desc3,2021-01-01,2021-01-29
 编码和测试软件   :1w
 安装测试系统    :2021-02-12,1w

 section 完成论文
 编写手册      :desc5,2021-01-01,10w
 论文修改      :crit,after desc3,3w
 论文定稿      :after desc5,3w

```