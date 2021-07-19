ClassLoader 类加载器
--------------------------------

### 类加载过程
Java 的类加载过程可以分为 5 个阶段：载入、验证、准备、解析和初始化。
这 5 个阶段一般是顺序发生的，但在动态绑定的情况下，解析阶段发生在初始化阶段之后。
#### 载入（Loading）
将类载入JVM内存

#### 验证（Verification）
确保类符合JVM规范

#### 准备（Preparation）
分配内存,设置默认值(区别于:初始值)

#### 解析（Resolution）
符号引用转化为直接引用(地址引用)

#### 初始化（Initialization）
设置初始值,执行类构造方法

##### 初始化顺序
- 1、父类的静态变量
- 2、父类的静态代码块
- 3、子类的静态变量
- 4、子类的静态代码块
- 5、父类的非静态变量
- 6、父类的非静态代码块
- 7、父类的构造方法
- 8、子类的非静态变量
- 9、子类的非静态代码块
- 10、子类的构造方法


### 双亲委派机制
当某个类加载器需要加载某个.class文件时，它首先把这个任务委派给他的上级(双亲)类加载器，递归这个操作，如果上级的类加载器没有加载，自己才会去加载这个类。
#### 好处
 - 有序
 - 唯一
 - 安全


### 打破双亲委派
#### 方法
1. 自定义 CustomerClassLoader 继承 ClassLoader
2. 重写 loadClass() 和 findClass() 方法

#### 场景（什么时候需要打破双亲委派）

##### Tomcat 破坏双亲委派
##### JDBC 破坏双亲委派

### JVM提供的3种类加载器
 - 启动类加载器(Bootstrap ClassLoader)
	负责加载 JVM 运行时核心类,加载`System.getProperty("sun.boot.class.path")`所指定的路径或jar。

 - 扩展类加载器(Extendsion ClassLoader)
	负责加载 JVM 扩展类，比如 swing 系列、内置的 js 引擎、xml 解析器 等等，这些库名通常以 javax 开头，它们的 jar 包位于 `$JAVA_HOME/lib/rt.jar`文件中。
加载`System.getProperty("java.ext.dirs")`所指定的路径或jar。在使用Java运行程序时，也可以指定其搜索路径，例如：`java -Djava.ext.dirs=d:\projects\test\classes HelloWorld`。

 - 应用程序类加载器(Application ClassLoader)
	负责加载`System.getProperty("java.class.path")`所指定路径下的类或jar包。在使用Java运行程序时，也可以加上-cp来覆盖原有的Classpath设置，例如： `java -cp ./test/classes HelloWorld`。

### 自定义类加载器
#### 场景（什么时候需要自定义类加载器）
#### 好处 （为什么要自定义类加载器）
 - 模块隔离
 - 热部署
 - 代码加密
 - 更灵活地加载类
#### 方法 （怎么样去自定义类加载器）

1. 自定义 CustomerClassLoader 继承 ClassLoader
2. 重写 loadClass() 方法

