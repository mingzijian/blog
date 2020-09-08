GEODIST (计算距离)
----------------------------
### 参数说明
GEODIST key member1 member2 [unit]
- key 键
- member1 第一个位置（起点）
- member2 第二个位置（终点）
- unit 单位（可选，默认为：m）
  - m 米
  - km 千米
  - mi 英里
  - ft 英尺

### 可用版本
3.2.0及以上

### 复杂度
O(log(N))

### 返回值
计算出的最短球面距离会以双精度浮点数的形式被返回。
如果给定的位置元素不存在， 那么命令返回null。
计算距离时会假设地球为完美的球形， 在极限情况下会存在0.5% 的误差。

### 使用示例
```
> GEODIST citi:locations wuhan hangzhou km
< "564.8271"
```
```
> GEODIST citi:locations wuhan nothing km
< null 
```