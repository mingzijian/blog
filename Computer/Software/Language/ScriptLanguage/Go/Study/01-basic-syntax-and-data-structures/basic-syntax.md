Go 语言 基础语法
-------------------------

### Go 语言结构

#### Hello World

```go
package main

import "fmt"

/*
我是多行注释
*/
func main() {
	// 我是单行注释
	var msg String = "Hello, World!"
	fmt.Println(msg)
}
```



#### Go语言基础组成部分

- 包声明 
```go
  package main
```
- 引入包
```go
  import "fmt"
```
- 函数
```go
  func main() {...}
```
- 变量
```go
  var msg String = "Hello, World!"
```
- 语句&表达式
```go
  fmt.Println(msg)
```
- 注释
```go
  // 我是单行注释

  /*
  我是多行注释
  */
```

#### 变量声明
##### 指定变量类型
指定变量类型，如果没有初始化，则变量默认为零值（变量没有做初始化时系统默认设置的值）。
常用类型的零值：
- 数值类型（包括complex64/128） ：0
- 布尔类型：false
- 字符串："" (空字符串)
- 以下几种类型的零值为：nil
  ```go
    var a *int
    var a []int
    var a map[string] int
    var a chan int
    var a func(string) int
    var a error // error 是接口
  ```

##### 根据值自行判定变量类型

```go
var boolVal = true
aNewBoolVal := true
intVal := 1
strVal := "hello"
```
缺省var，使用:=声明的变量必须是新的变量，否则将产生编译错误 (如果你声明了一个局部变量却没有在相同的代码块中使用它，同样会得到编译错误)

##### 多变量声明

```go
// 类型相同多个变量, 非全局变量
var vname1, vname2, vname3 type
vname1, vname2, vname3 = v1, v2, v3

// 不需要显示声明类型，自动推断
var vname1, vname2, vname3 = v1, v2, v3 

// 出现在 := 左侧的变量不应该是已经被声明过的，否则会导致编译错误
vname1, vname2, vname3 := v1, v2, v3 


// 这种因式分解关键字的写法一般用于声明全局变量
var (
    vname1 v_type1
    vname2 v_type2
)
```

#### 常量声明
常量是一个简单值的标识符，在程序运行时，不会被修改的量。

常量中的数据类型只可以是布尔型、数字型（整数型、浮点型和复数）和字符串型。

##### 常量的定义格式：
```go
const identifier [type] = value
```
##### 特殊常量 iota
可以认为是一个可以被编译器修改的常量。
iota 在 const关键字出现时将被重置为 0(const 内部的第一行之前)，const 中每新增一行常量声明将使 iota 计数一次(iota 可理解为 const 语句块中的行索引)。

iota 用法：

```go
package main

import "fmt"

func main() {
    const (
            a = iota   //0
            b          //1 ，缺省，同上 (等同 b = iota)
            c          //2
            d = "ha"   //独立值，iota += 1
            e          //"ha"   iota += 1
            f = 100    //iota +=1
            g          //100  iota +=1
            h = iota   //7,恢复计数
            i          //8
    )
    fmt.Println(a,b,c,d,e,f,g,h,i)
}
```
运行结果：
`0 1 2 ha ha 100 100 7 8`

#### 出入参

##### 入参（传入值）
- Go语言中所有的传参都是值传递。
    但是传递的类型如果是bool、int、string、struct等这些值类型，在函数中无法修改原参数内容数据;
    如果是指针、map、slice、chan等这些引用类型，在函数中可以修改原参数内容数据。

- 入参可以没有，也可以有一个或多个，入参类型可以有多种
- 参数支持缺省声明原型，支持不定长度变参
- 函数（方法）不支持重载（overload）,不支持嵌套（nested），不支持默认参数（default parameters）

##### 出参（返回值）
- 可以有多个返回值（不想要的返回值，可以扔垃圾桶 _ ）
- 可以在函数头声明返回参数（不需要强制命名返回值，但是命名后的返回值可以让代码更加清晰，可读性更强），return语句可以缺省 （当有return语句时，返回值的顺序优先使用return语句的返回值顺序）

#### 关键字（保留字）

| break    | default     | func   | interface | select |
| -------- | ----------- | ------ | --------- | ------ |
| case     | defer       | go     | map       | struct |
| chan     | else        | goto   | package   | switch |
| const    | fallthrough | if     | range     | type   |
| continue | for         | import | return    | var    |

#### 预定义标识符

| append | bool    | byte    | cap     | close  | complex | complex64 | complex128 | uint16  |
| ------ | ------- | ------- | ------- | ------ | ------- | --------- | ---------- | ------- |
| copy   | false   | float32 | float64 | imag   | int     | int8      | int16      | uint32  |
| int32  | int64   | iota    | len     | make   | new     | nil       | panic      | uint64  |
| print  | println | real    | recover | string | true    | uint      | uint8      | uintptr |


### 参考

- https://golang.google.cn
- https://www.runoob.com/go/go-basic-syntax.html
- https://blog.go-zh.org/gos-declaration-syntax