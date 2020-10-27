Shell 流程控制
---------------------
- if-else 
- case
- for
- while
- until

### if-else
```shell
if condition
then
    # do something
else
    # do other
fi


if condition1
then
    # do something1
elif condition2 
then 
    # do something2
else
    # do other
fi
```
### case
```shell
case match_value in
match1)
    # do something1
    ;;
match2)
    # do something2
    ;;
*)
    # do other
    ;;
esac
```
- `in`
取值后面必须为关键字 in，每一模式必须以右括号结束。
取值可以为变量或常数。
- `;;`
意思是跳到整个 case 语句的最后，类似Java的`break;`
- `*)`
取值将检测匹配的每一个模式。
一旦模式匹配，则执行完匹配模式相应命令后不再继续其他模式。
如果无一匹配模式，使用星号`*` 捕获该值，再执行后面的命令。
- `esac`
case语句以case开头，并且以反写的（esac）结尾

### for
```shell
for i in 1 2 3 4 5
do
    echo "The value is: $i"
done


for ((i=1;i<=5;i++)) do
    echo "The value is: $i"
done
```

### while
```shell
while condition
do
    # do something
done
```
`while` 循环执行一系列命令直至条件为`false` 时停止。

- `continue`
	跳出当前本次循环，类似Java的`continue;`
- `break`
	跳出当前整个循环，类似Java的`break;`

### until
```shell
until condition
do
    # do something
done
```
`until` 循环执行一系列命令直至条件为`true`时停止。

