Linux 文件描述符（file descriptor）
-------------------------
内核（kernel）利用文件描述符（file descriptor）来访问文件。
文件描述符是非负整数。
打开现存文件或新建文件时，内核会返回一个文件描述符。
读写文件也需要使用文件描述符来指定待读写的文件。

### Linux标准文件描述符

| 文件描述符 | 缩写   | 描述                                                |
| ---------- | ------ | --------------------------------------------------- |
| 0          | STDIN  | 标准输入 /dev/stdin                                 |
| 1          | STDOUT | 标准输出 /dev/stdout                                |
| 2          | STDERR | 标准错误输出 /dev/stderr （在命令出错情况下的输出） |

### 自定义文件描述符
```shell
# 创建文件并写入文件内容
> echo "this is a test line" > input.txt
# 创建描述符7打开并读取文件
> exec 7< input.txt
# 连接引用描述符7并打印到标准输出设备上
> cat <&7
<
this is a test line
```

### 黑洞 （/dev/null ）
`/dev/null` 是一个很特殊的文件，你写入的任何东西都会清空。就像一个黑洞。
示例
```
> echo hello >/dev/null
<

> echo "this is a test line" > input.txt
> cat input.txt
<
this is a test line
> cat /dev/null > input.txt
<

> cat input.txt
<

```

### 重定向
重定向的符号有两个：`>`或`>>`
- `>` 覆盖写入（先清空文件，再写入内容）
	`>` 默认等同于`>1`，即：重定向缺省文件名描述符为标准输出
- `>>` 追加写入 （不清空文件，追加到现有文件的尾部）

示例
`2>&1` 表示错误输出重定向到标准输出
`>/dev/null` 表示将标准输出重定向到“黑洞”
`>/dev/null 2>&1` 表示将标准输出及错误输出全部重定向到“黑洞”