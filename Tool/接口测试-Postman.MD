接口测试工具-Postman
---------------------------------
### 快速开始
#### 介绍
#### 安装与升级
#### 导航栏

#### 发送请求

#### 管理账户

#### 同步工作

#### 模板
#### 收藏夹
#### 工作区
#### 设置

#### 数据导入/导出
#### 答疑

### 发送请求
#### 构建请求

#### 授权请求

#### 接收响应

#### 请求分组

#### 变量使用

- [理解变量](https://learning.postman.com/docs/sending-requests/variables/#understanding-variables)

- [变量作用域](https://learning.postman.com/docs/sending-requests/variables/#variable-scopes)
	Global > Collection > Environment > Data > Local
    - Global
    - Collection
    - Environment
    - Data
    - Local

- [定义变量](https://learning.postman.com/docs/sending-requests/variables/#defining-variables)

- [访问变量](https://learning.postman.com/docs/sending-requests/variables/#accessing-variables)

- [共享和持久化数据](https://learning.postman.com/docs/sending-requests/variables/#sharing-and-persisting-data)

- [记录变量](https://learning.postman.com/docs/sending-requests/variables/#logging-variables)

- [使用数据变量](https://learning.postman.com/docs/sending-requests/variables/#using-data-variables)

- [使用动态变量](https://learning.postman.com/docs/sending-requests/variables/#using-dynamic-variables)

    `{{$guid}}`：添加一个GUID(GUID 的格式为“xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx”，其中每个 x 是 0-9 或 a-f 范围内的一个[十六进制数](https://baike.baidu.com/item/十六进制数)。例如：6f9619ff-8b86-d011-f42d-00c04fd964ff 即为有效的 GUID 值)

    `{{$timestamp}}`：将当前的时间戳，精确到秒

    `{{$randomInt}}`：添加0和1000之间的随机整数

    更多内置动态变量...

![Postman动态变量Random](https://images.gitee.com/uploads/images/2020/0806/205634_43860519_536895.png "Postman动态变量Random.png")


- [下一步](https://learning.postman.com/docs/sending-requests/variables/#next-steps)
    有关如何管理和共享变量数据集的概述，请参阅管理环境
    有关在请求脚本中使用变量的详细信息，请参阅脚本简介
    有关如何在请求之间使用数据的更多信息，请参阅集合中的请求分组

#### 环境管理

#### 可视化响应

#### 详细示例

#### 使用cookie

#### 使用证书

#### 生成客户端代码

#### 请求故障排除

#### 请求数据捕获

#### API框架支持

### 编写脚本

#### 编写脚本

#### 编写预请求脚本

#### 编写测试用例

#### 脚本引用

### 运行集合

#### 使用运行集合

#### 使用监视器

#### 请求工作流

#### 导入数据

### 使用Newman CLI

### 发送请求

### Collaborating In Postman
### 设计和开发API
### 发布API
### 管理
### 开发者资源
### 整合

### 快捷键

|标签	|MACOS|	Windows / Linux|
| ------ | ---- | ---- |
|打开新标签页|	⌘T	|Ctrl + T                              |
|关闭标签|	⌘W	|Ctrl + W                                  |
|切换到下一个选项卡|	⇧⌘]	|Ctrl + Shift +]                   |
|切换到上一个选项卡|	⇧⌘[	|Ctrl + Shift + [                  |
|切换到位置的标签|	⌘1至⌘8	|Ctrl + 1到Ctrl + 8            |
|切换到最后一个选项卡|	⌘9	|Ctrl + 9                      |
|从新栏中的侧栏打开请求|	⌥⌘（点击）	|Ctrl + Shift +（点击）|
|请求| -------- | -------- |
|请求URL|	⌘L	|Ctrl + L                                  |
|保存请求|	⌘S	|Ctrl + S                                  |
|保存请求为|	⇧⌘S	|Ctrl + Shift + S                          |
|发送请求|	⌘↵	|Ctrl + Enter                              |
|发送和下载请求|	⌥⌘↵	|Ctrl + Alt + Enter                    |
|滚动到请求|	⌥⌘↑	|Ctrl + Alt +↑                         |
|滚动到响应|	⌥⌘↓	|Ctrl + Alt +↓                         |
|美化原始请求体|	⌘B	|Ctrl + B                              |
|侧边栏| -------- | -------- |
|搜索侧栏|	⌘F	|Ctrl + F                                  |
|切换边栏|	⌘\	|Ctrl + \                                  |
|下一个项目|	↓	|↓                                         |
|上一个项目|	↑	|↑                                         |
|展开项目|	→	|→                                         |
|折叠项目|	←	|←                                         |
|选择项目|	↵	|↵                                         |
|重命名项|	⌘E	|Ctrl + E                                  |
|组项目|	⌘G	|Ctrl + G                                      |
|切项目|	⌘X	|Ctrl + X                                      |
|复制|	⌘C	|Ctrl + C                                      |
|粘贴|	⌘V	|Ctrl + V                                      |
|重复|	⌘D	|Ctrl + D                                      |
|删除项目|	⌫	|Delete                                    |
|接口| -------- | -------- |
|放大|	⌘+	|Ctrl + +                                      |
|缩小|	⌘ -	|Ctrl + -                                      |
|重置缩放|	⌘0	|Ctrl + 0                                  |
|切换双面视图|	⌥⌘V	|Ctrl + Alt + V                        |
|切换到边栏|	⌥⌘1	|Ctrl + Alt + 1                            |
|切换到生成器|	⌥⌘2	|Ctrl + Alt + 2                        |
|窗口| -------- | -------- |
|新请求窗口|	⌘N	|Ctrl + N                                  |
|新的运行窗口|	⇧⌘N	|Ctrl + Shift + N                      |
|新的控制台窗口|	⌥⌘C	|Ctrl + Alt + C                        |
|导入|	⌘O	|Ctrl + O                                      |
|管理环境|	⌥⌘E	|Ctrl + Alt + E                            |
|设置|	⌘，	|Ctrl +，                                      |
|提交|	⌘↵	|Ctrl + Enter                                  |
|打开快捷键帮助|	⌘/	|Ctrl + /                              |


### 参考
- https://learning.postman.com/docs/getting-started/introduction/
