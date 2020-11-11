Zookeeper Simple API
---------------------------------
ZooKeeper的设计目标之一是提供一个非常简单的编程接口。
因此，它只支持以下操作：
- create : creates a node at a location in the tree
	在树的某个位置创建节点
- delete : deletes a node
	删除某个节点
- exists : tests if a node exists at a location
	测试某个节点是否存在
- get data : reads the data from a node
	读取某个节点的数据
- set data : writes data to a node
	将数据写入某个节点
- get children : retrieves a list of children of a node
	检索节点的子节点列表
- sync : waits for data to be propagated
	等待数据传播