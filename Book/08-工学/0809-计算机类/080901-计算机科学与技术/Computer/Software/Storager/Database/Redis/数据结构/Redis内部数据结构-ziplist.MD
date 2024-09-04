ziplist (压缩列表)
-------------------------
当一个列表键只包含少量列表项，并且每个列表项要么就是小整数值，要么就是长度比较短的字符串，那么Redis就会使用压缩列表来做列表键的底层实现。



```text
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
```

当有序集合中的元素数超过`zset-max-ziplist-entries`时，或其中任意一个元素的数据长度超过`zset-max-ziplist-value`时，就由ziplist自动转化为zset。