# count 介绍

## 含义


## 使用场景



# count 准？

MVCC (Multiversion Concurrency Control)



# count 慢？

## count 比较

count(*) ≈ count(1) > count(id)

count(*) 会被优化成  count(0)
count 统计非 null 则 +1



