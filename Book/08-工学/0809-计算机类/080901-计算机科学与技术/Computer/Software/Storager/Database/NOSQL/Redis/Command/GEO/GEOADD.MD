GEOADD （添加空间元素）
-------------------------------------

### 参数说明
GEOADD key longitude latitude member [longitude latitude member ...]
- key 键
- longitude 元素经度 取值范围：[-180.00,180.00]
- latitude 元素纬度 取值范围：[-85.05112878,85.05112878]
- member 元素名

### 可用版本
3.2.0及以上

###  时间复杂度
每添加一个元素的复杂度为 O(log(N)) ， 其中 N 为键里面包含的位置元素数量。

###  返回值
新添加到键里面的空间元素数量（不包括那些已经存在但是被更新的元素）。

### 使用示例
#### 添加单个元素
```
> geoadd citi:locations 114.31 30.52 "wuhan"
< "1"
```
#### 添加已存在元素（返回0，但是会更新已存在元素的坐标）
```
> geoadd citi:locations 114.311 30.521 "wuhan"
< "0"
```
#### 添加多个元素（返回新添加元素个数）
```
> geoadd citi:locations 115.09 30.2 "huangshi" 116.28 39.55 "bejing" 114.29 38.02 "tianjing"
< "3"
```
#### 错误（经纬度超出取值范围）
- 经度取值范围：[-180.00,180.00]
- 纬度取值范围：[-85.05112878,85.05112878]
```
> geoadd citi:locations -180.01 -85.05112879 wrongcity
< "ERR invalid longitude,latitude pair -180.010000,-85.051129"
```