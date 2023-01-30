Redis 内存数据淘汰策略
----------------------------------
1. no-eviction
	默认策略。禁止驱逐，保证数据不会丢失
2. allkeys-lru 
	针对所有key，优先删除最近最少使用(less recently used) 的key
3. volatile-lru
	针对设置了过期时间的key，优先删除最近最少使用(less recently used) 的key
4. allkeys-random 
	针对所有key，随机删除
5. volatile-random
	针对设置了过期时间的key，随机删除
6. volatile-ttl
	针对设置了过期时间的key，优先删除存活时间(time to live)最短的key

#### LRU (less recently used)
根据数据的历史访问记录来进行淘汰数据
核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高”。

#### TTL (time to live)
Redis 数据集数据结构中保存了键值对过期时间的表，即 redisDb.expires。
快要过期的先淘汰。

#### Random
随机淘汰。
众生平等。
