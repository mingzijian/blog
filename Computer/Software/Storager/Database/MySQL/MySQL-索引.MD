MySQL 索引
------------------

### 索引类型
#### FULLTEXT (全文索引)
	检索长文本时,效果好
#### NORMAL (普通索引)
	常用的一般索引
#### SPATIAL (空间索引)
	空间数据类型的字段建立的索引
##### 空间数据类型
 - GEOMETRY
 - POINT
 - LINESTRING
 - POLYGON
#### UNIQUE (唯一索引)
	不允许重复值
#### PRIMARY (主键索引)
	特殊的唯一索引,一张表只能有一个主键索引.
### 索引方法
#### HASH (Hash)

哈希索引（hash index）基于哈希表实现，只有精确匹配索引所有列的查询才有效。



#### BTREE (B+Tree)
##### 为什么说B+树比B树更适合数据库索引
1、B+树的磁盘读写代价更低：B+树的内部节点并没有指向关键字具体信息的指针，因此其内部节点相对B树更小，如果把所有同一内部节点的关键字存放在同一盘块中，那么盘块所能容纳的关键字数量也越多，一次性读入内存的需要查找的关键字也就越多，相对IO读写次数就降低了。

2、B+树的查询效率更加稳定：由于非终结点并不是最终指向文件内容的结点，而只是叶子结点中关键字的索引。所以任何关键字的查找必须走一条从根结点到叶子结点的路。所有关键字查询的路径长度相同，导致每一个数据的查询效率相当。

3、由于B+树的数据都存储在叶子结点中，分支结点均为索引，方便扫库，只需要扫一遍叶子结点即可，但是B树因为其分支结点同样存储着数据，我们要找到具体的数据，需要进行一次中序遍历按序来扫，所以B+树更加适合在区间查询的情况，所以通常B+树用于数据库索引。

### 强制、忽略索引
#### 强制索引 FORCE INDEX
SELECT * FROM TABLE1 FORCE INDEX (FIELD1) …
只使用建立在FIELD1上的索引，而不使用其它字段上的索引。

#### 忽略索引  IGNORE INDEX
SELECT * FROM TABLE1 IGNORE INDEX (FIELD1, FIELD2) …
TABLE1表中FIELD1和FIELD2上的索引将不被使用。

### 聚簇索引(聚集索引)
- 聚簇索引与非聚簇索引结构对比
![聚簇索引与非聚簇索引对比](https://images.gitee.com/uploads/images/2020/0730/133257_44f56e2e_536895.png "聚簇索引与非聚簇索引对比.png")
#### MyISAM 
![非聚簇索引-PK](https://images.gitee.com/uploads/images/2020/0730/170039_da21321b_536895.png "非聚簇索引-PK.png")
![非聚簇索引-SK](https://images.gitee.com/uploads/images/2020/0730/164916_1286aa51_536895.png "非聚簇索引-SK.png")
#### InnoDB
![聚簇索引-PK](https://images.gitee.com/uploads/images/2020/0730/164931_462c2606_536895.png "聚簇索引-PK.png")
![聚簇索引-SK](https://images.gitee.com/uploads/images/2020/0730/164945_be04fae1_536895.png "聚簇索引-SK.png")

### 索引的优缺点
#### 索引的优点
 - 可以支持行级锁(InnoDB)
 - 可以将随机IO变为顺序IO,提升IO效率
 - 可以避免排序和临时表
 - 可以减小扫描的数据量
#### 索引的缺点(MySQL里同一个数据表里的索引总数限制为16个)
 - 轻微降低了表的更新速度(更新数据的同时需要更新索引)
 - 轻微增加的表的占用空间(保存数据的同事需要保存索引)


### 参考
 - https://juejin.im/post/5b55b842f265da0f9e589e79
 - http://blog.codinglabs.org/articles/theory-of-mysql-index.html