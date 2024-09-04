java.lang.System

`System`类提供的功能包括标准输入、标准输出和错误输出流；访问外部定义的属性和环境变量；加载文件和库的方法；以及快速复制数组的一部分的实用方法。

### 简要代码

```
package java.lang;
import ...
public final class System {
  private static native void registerNatives();
  static {
    registerNatives();
  }
  private System() {
  }
  ...
}
```
### 功能
#### 标准输入、标准输出和错误输出流
##### 标准输入 `System.in`
`System.in.available()`
`System.in.close()`
`System.in.read()`
`System.in.reset()`


##### 标准输出 `System.out`
`System.out.flush()`
`System.out.format()`
`System.out.print()`
`System.out.printf()`
`System.out.println()`

##### 错误输出 `System.err`
`System.err.flush()`
`System.err.format()`
`System.err.print()`
`System.err.printf()`
`System.err.println()`

#### 外部定义的属性和环境变量
##### 属性
`initProperties(Properties):Properties`

`getProperties():Properties`
`setProperties(Properties):void`

`checkKey(String):void`

`getProperty(String):String`
`clearProperty(String):String`

`getProperty(String,String):String`
`setProperty(String,String):String`


| key         | value描述 | value示例                |
| ----------- | --------- | ------------------------ |
| awt.toolkit | AWT工具包 | sun.awt.windows.WToolkit |
| ---         | ---       | ---                      |
| **文件相关** | ---       | ---                      |
| file.encoding        | 文件编码   | UTF-8                   |
| file.encoding.pkg        | 编码包    | sun.io                    |
| file.separator        | 文件路径分隔符 | \                      |
| ---      | ---       | ---                      |
| **java相关**    | ---       | ---                      |
| java.awt.graphicsenv | ---       | sun.awt.Win32GraphicsEnvironment |
| java.awt.printerjob | ---       | sun.awt.windows.WPrinterJob |
| ---      | ---       | ---                      |
| java.class.path | ---       | D:\application\java\jdk1.8.0_181\jre\lib\charsets.jar;<br>D:\application\java\jdk1.8.0_181\jre\lib\deploy.jar;<br>...... |
| java.class.version | class版本<br>50.0 : 1.6<br>51.0 : 1.7<br>52.0 : 1.8 | 52.0                  |
| ---      | ---  | ---                      |
| java.endorsed.dirs | ---       | D:\application\java\jdk1.8.0_181\jre\lib\endorsed |
| java.ext.dirs | ---       | D:\application\java\jdk1.8.0_181\jre\lib\ext;<br>C:\WINDOWS\Sun\Java\lib\ext |
| java.home | Java主目录                                          | D:\application\java\jdk1.8.0_181\jre |
| java.io.tmpdir | 临时文件路径 | C:\Users\xxxx\AppData\Local\Temp\ |
| java.library.path | ---       | D:\application\java\jdk1.8.0_181\bin;<br>C:\WINDOWS\Sun\Java\bin;...... |
| ---      | ---       | ---                      |
| java.runtime.name | 运行环境名称 | Java(TM) SE Runtime Environment |
| java.runtime.version | 运行环境版本 | 1.8.0_181-b13         |
| --- | ---       | --- |
| java.specification.name | 规范名称   | Java Platform API Specification |
| java.specification.vendor | 规范提供商 | Oracle Corporation |
| java.specification.version | 规范版本   | 1.8 |
| --- | ---       | --- |
| java.vendor | 提供商    | Oracle Corporation |
| java.vendor.url | 官网     | http://java.oracle.com/ |
| java.vendor.url.bug | bug报告 | http://bugreport.sun.com/bugreport/ |
| --- | ---       | --- |
| java.version | Java版本 | 1.8.0_181 |
| --- | ---       | --- |
| **JVM相关** | ---       | --- |
| java.vm.info | ---       | mixed mode |
| java.vm.name | JVM名称 | Java HotSpot(TM) 64-Bit Server VM |
| java.vm.specification.name | JVM规范名称 | Java Virtual Machine Specification |
| java.vm.specification.vendor | JVM规范提供商 | Oracle Corporation |
| java.vm.specification.version | JVM规范版本 | 1.8 |
| java.vm.vendor | JVM提供商 | Oracle Corporation |
| java.vm.version | JVM版本 | 25.181-b13 |
| --- | ---       | --- |
| line.separator | 行分隔符（在 UNIX 系统中是`\n`） | `\n` |
| --- | ---       | --- |
| **操作系统相关** | ---       | --- |
| os.arch | 操作系统架构 | amd64 |
| os.name | 操作系统名称 | Windows 10 |
| os.version | 操作系统版本 | 10.0 |
| --- | ---       | --- |
| path.separator | 路径分隔符（在 UNIX 系统中是`:`） | ; |
| --- | ---       | --- |
| **sun相关** | ---       | --- |
| sun.arch.data.model | 架构模式   | 64 |
| sun.boot.class.path | ---       | D:\application\java\jdk1.8.0_181\jre\lib\resources.jar;<br>D:\application\java\jdk1.8.0_181\jre\lib\rt.jar;<br>...... |
| sun.boot.library.path | ---       | D:\application\java\jdk1.8.0_181\jre\bin |
| sun.cpu.endian | CPU字节序 | little |
| sun.cpu.isalist | ---       | amd64 |
| sun.desktop | ---       | windows |
| sun.io.unicode.encoding | ---       | UnicodeLittle |
| sun.java.command | ---       |         |
| sun.java.launcher | ---       | SUN_STANDARD |
| sun.jnu.encoding | ---       | GBK |
| sun.management.compiler | ---       | HotSpot 64-Bit Tiered Compilers |
| sun.os.patch.level | ---       |         |
| --- | ---       | --- |
| **用户相关** | ---       | --- |
| user.country | 国家 | CN |
| user.dir | 用户的当前工作文件夹 |         |
| user.home | 用户的主文件夹 | C:\Users\xxxx |
| user.language | 语言     | zh |
| user.name | 账户名称 | xxxx |
| user.script | ---       |         |
| user.timezone | 时区 |         |
| user.variant | ---       |         |


##### 环境变量
`System.getenv():Map<String,String>`

`System.getenv(String):String`

#### 加载类文件和类库
##### 类加载
`System.load(String):void`

#### 类库加载
`System.loadLibrary(String):void`

#### 快速复制数组
`System.arraycopy(Object,int,Object,int,int):void`


#### 其他
##### gc
运行垃圾收集器。
##### exit
终止当前正在运行的JVM。


