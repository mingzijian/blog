Mybatis 缓存
-------------------

## 缓存分级
### 一级缓存 （本地缓存）
#### 作用范围
- SqlSession
- Statement

#### 开启/关闭
强制开启,无法关闭

### 二级缓存 （默认开启）
#### 作用范围
namespace
#### 开启/关闭
- 开启（默认开启）
```xml
<setting name="cacheEnabled" value="true"/></settings>
```
- 关闭（显示关闭）
```xml
<setting name="cacheEnabled" value="false"/></settings>
```
#### 缓存策略

- `LRU` – 最近最少使用：移除最长时间不被使用的对象。
- `FIFO` – 先进先出：按对象进入缓存的顺序来移除它们。
- `SOFT` – 软引用：基于垃圾回收器状态和软引用规则移除对象。
- `WEAK` – 弱引用：更积极地基于垃圾收集器状态和弱引用规则移除对象。

#### 缓存复用

使用`cache-ref`在多个命名空间中共享相同的缓存，实现复用。


### 自定义缓存
#### 作用范围
自定义

#### 集成Redis缓存
