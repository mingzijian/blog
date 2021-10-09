[TOC]

MySQL 锁
---------------

 - 死锁
 - 间隙锁
 - 行锁 & 页锁 & 表锁 & 全局锁
 - 乐观锁 & 悲观锁
 - 共享锁 & 排它锁(独占锁)

### 死锁

多个进行相互等待对方资源，在得到所有资源继续运行之前，都不会释放自己已有的资源，这样造成了循环等待的现象，称为死锁。


#### 死锁产生

##### 条件:

1. 资源互斥
2. 占有和等待
3. 资源不可剥夺
4. 环路等待

##### 示例:

1, [17:00:47] sessionA 获取 t2成功

2, [17:00:49] sessionB 获取t1成功

3, [17:00:57] sessionA 获取t1等待(死锁)

4, [17:00:59] sessionB 获取t2成功



```sql
-- sessionA
start transaction ;

update t2 set age = 1 where id = 1;
select sleep(10);
update t1 set age = 2 where id = 1;

commit ;

-- sessionB
start transaction ;

update t1 set age = 1 where id = 1;
select sleep(10);
update t2 set age = 2 where id = 1;

commit ;
```

#### 死锁影响



#### 死锁检测



#### 死锁恢复



#### 死锁避免



### 间隙锁



### 参考
 - https://juejin.im/post/5b55b842f265da0f9e589e79