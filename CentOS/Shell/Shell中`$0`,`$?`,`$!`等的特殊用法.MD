Shell中`$0`,`$?`,`$!`等的特殊用法
-------------------------

 - `$$`
    Shell本身的PID（ProcessID）
 - `$!`
    Shell最后运行的后台Process的PID
 - `$?`
    最后运行的命令的结束代码（返回值）
 - `$-`
    使用Set命令设定的Flag一览
 - `$*`
    所有参数列表。如"$*"用「"」括起来的情况、以"`$1 $2 … $n`"的形式输出所有参数。
 - `$@`
    所有参数列表。如"$@"用「"」括起来的情况、以"`$1`" "`$2`" … "`$n`" 的形式输出所有参数。
 - `$#`
    添加到Shell的参数个数
 - `$0`
    Shell本身的文件名
 - `$1 ～ $n`
    添加到Shell的各参数值。$1是第1参数、$2是第2参数、$n是第n参数…。
 - `$[ ]`
    表达式求值。
    ```shell 
     a=1
     b=2
     c=$[a+b]
     echo $c

     >>> 3
    ```
 - `${ }`
   - `${var}`
     限定变量。
    ```shell
    var="hello"
    var_01="hello01"
    echo  $var $var_01 ${var}_01
    
    >>> hello hello01 hello_01
    ```
   - `${#var}`
     得到变量var的长度。
    ```shell
    var="hello"
    echo ${#var}
    
    >>> 5
    ```
   - `${var#}`
    删除变量首部的字符（串），正则表达式（惰性匹配）。


   - `${var##}`
    删除变量首部的字符（串），正则表达式（贪婪匹配）。

   - `${var%}`
    删除变量尾部的字符（串），正则表达式（惰性匹配）。

   - `${var%%}`
    删除变量尾部的字符（串），正则表达式（贪婪匹配）。

   - `${var::}`
    指定长度的子串（${var:start:length}），截取字符串。
    ```shell
    var="~/res_pos/path/from/something"
    echo ${var:0:5}
    
    >>> ~/res
    
    echo ${var:1:5}
    
    >>> /res_      # 可见，字符串的索引是从0开始的
    
    echo ${var::5}
    
    >>> ~/res      # 省略"start"，默认为从0开始
    
    echo ${var:1:}
    
    >>>            # 省略"length"，默认长度为0，所以输出为空
    ```
   - `${var//}`
    将var中的首个字符串A替换成字符串B（${var/A/B}）。
    ```shell
    var="hello world"
    echo ${var/hello/hi}
    
    >>> hi world      # 字符串"hello"替换成了"hi"
    
    echo ${var/l/X}
    
    >>> heXlo world  # 第一个"l"替换成了"X"
    ```
   - `${var///}`
    将var中的所有字符串A替换成字符串B（${var/A/B}）。
    ```shell
    var="hello world"
    echo ${var//l/X}
    
    >>> heXXo worXd  # 所有的"l"都替换成了"X"
    ```
   - `${var/#/}`
    替换字符串首部的串A为串B（${var/#A/B}）。


   - `${var/%/}`
    替换字符串尾部的串A为串B（${var/%A/B}）。


#### 参考
 - https://blog.csdn.net/qq_40907977/article/details/95947807
 - https://blog.csdn.net/lihonghai2392/article/details/77868445

