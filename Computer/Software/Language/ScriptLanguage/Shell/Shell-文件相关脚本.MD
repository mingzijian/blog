Shell 文件相关脚本
---------------------------
[TOC]

### 文件夹合并
#### 脚本
```shell
# 将source-folder文件夹的内容同步（合并）到target-folder文件夹下
rsync -av /source-folder/ /target-folder/

# 检查同步（合并）结果
ls -l -h /target-folder/

# 移除source-folder文件夹
rm -rf /source-folder/
```
#### 解释
`rsync`  （remote synchronize）是一个远程数据同步工具，可通过LAN/WAN快速同步多台主机间的文件。
`-v`  --verbose 详细模式输出。
`-a`  --archive 归档模式，表示以递归方式传输文件，并保持所有文件属性。

### 列出当前文件夹下的重复文件
#### 脚本
```shell
find -not -empty -type f -printf "%s\n" | sort -rn | uniq -d | xargs -I{} -n1 find -type f -size {}c -print0 | xargs -0 md5sum | sort | uniq -w32 --all-repeated=separate
```

#### 解释
- `find -not -empty -type f -printf "%s\n"`
`find`是查找命令；
`-not -empty`是要寻找非空文件；
`-type f`是指寻找常规文件；
`-printf "%s\n"`，这里的`%s`表示的是文件的大小，单位为bytes，`\n`是换行符（win下为:`\r\n`）。
所以这句话的意思是输出所有非空文件的大小。

- `sort -rn`
`sort`是排序，`-n`是指按大小排序，`-r`是指从大到小排序（逆序reverse）。

- `uniq -d`
`uniq`是把重复的只输出一次，
`-d`指只输出重复的部分（如9出现了5次，那么就输出1个9，而2只出现了1次，并非重复出现的数字，故不输出）。

- `xargs -I{} -n1 find -type f -size {}c -print0`
这一部分分两部分看，第一部分是`xargs -I{} -n1`，`xargs`命令将之前的结果转化为参数，供后面的`find`调用，其中`-I{}`是指把参数写成`{}`，而`-n1`是指将之前的结果一个一个地输入给下一个命令（`-n8`就是8个8个地输入给下一句，不写`-n`就是把之前的结果一股脑的给下一句）。
后半部分是`find -type f -size {}c -print0`，`find`指令我们前面见过，-`size{}`是指找出大小为`{}`bytes的文件，而`-print0`则是为了防止文件名里带空格而写的参数。

- `xargs -0 md5sum`
`xargs`我们之前说过，是将前面的结果转化为输入，那么这个`-0`又是什么意思？man一下`xargs`，我们看到`-0`表示读取参数的时候以`null`为分隔符读取，这也不难理解，毕竟`null`的二进制表示就是00。后面的`md5sum`是指计算输入的`md5`值。

- `sort`
排序(默认正序)

- `uniq -w32 --all-repeated=separate`
`uniq -w32`是指寻找前32(`md5`值一定是32位)个字符相同的行，
`--all-repeated=separate`是指将重复的部分放在一类，分类输出。

### 当前文件夹下文件大小排序
#### 脚本
```shell 
du -sh ./* | sort -rn
```
#### 解释
- `du -sh ./*`
`du` 报告磁盘空间使用情况; 
`-s` 只统计指定参数的在同一设备上所使用的空间; 
`-h` human，以人类可读的友好方式输出;
`./*` 当前目录下的所有文件。

- `sort -rn`
`sort`是排序; 
`-n`是指按大小排序; 
`-r`是指从大到小排序（逆序reverse）。

### 按名称查找文件
#### 脚本
```shell 
find  ./ -name \*likename\*
```
#### 解释
- `find ./`
递归地在当前层次目录中处理文件

- `-name \*likename\*`
按基本的文件名(将路径去掉了前面的目录)与shell模式pattern相匹配。
`*`需要`\*`转义,`\*likename`表示以`likename`结尾，`likename\*`表示以`likename`开头。