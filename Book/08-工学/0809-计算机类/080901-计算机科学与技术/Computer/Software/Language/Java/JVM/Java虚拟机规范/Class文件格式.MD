## 字节码文件组成部分

- 魔数与Class文件版本
- 常量池
- 访问标志
- 类索引、父类索引、接口索引
- 字段表集合
- 方法表集合
- 属性表集合



## Class 文件结构

```java
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

### magic

魔数

提供标识类文件格式的魔数： `0xCAFEBABE` 

### minor_version, major_version

minor_version 次要版本号

major_version 主要版本号

### constant_pool_count

常量池计数

`constant_pool_count`等于`constant_pool`表中的条目数加一。

### constant_pool[]

常量池

表示各种字符串常量、类和接口名称、字段名称以及 结构及其子结构中引用的其他常量。

### access_flags

访问标志

表示此类或接口的访问权限和属性的标志掩码

| 标志名称         | 值     | 解释                                              |
| ---------------- | ------ | ------------------------------------------------- |
| `ACC_PUBLIC`     | 0x0001 | `public`；可以从其包外部访问。                    |
| `ACC_FINAL`      | 0x0010 | `final`；不允许有子类。                           |
| `ACC_SUPER`      | 0x0020 | *当由invokespecial*指令调用时，特殊对待超类方法。 |
| `ACC_INTERFACE`  | 0x0200 | 是一个接口，而不是一个类。                        |
| `ACC_ABSTRACT`   | 0x0400 | `abstract`；不得实例化。                          |
| `ACC_SYNTHETIC`  | 0x1000 | 声明为合成；源代码中不存在。                      |
| `ACC_ANNOTATION` | 0x2000 | 声明为注解类型。                                  |
| `ACC_ENUM`       | 0x4000 | 声明为`enum`类型。                                |

### this_class

本类

必须是constant_pool[]中的有效索引

该索引在constant_pool[]中对应的条目表示该文件定义的类或接口的`CONSTANT_Class_info`结构

### super_class

超类

对于类，`super_class` 必须为 0 或者必须是 `constant_pool[]`表中的有效索引。

如果 `super_class`为0，则该`class`文件必须表示 类 `Object`，唯一没有直接超类的类或接口。

### interfaces_count

interfaces[]的数量

给出了该类或接口类型的直接超级接口的数量。

### interfaces[]

接口表

实现的所有接口

### fields_count

fields[]的数量

### fields[]

字段表

`fields`表仅包含此类或接口声明的那些字段。它不包括从超类或超接口继承的字段。

### methods_count

`methods[]`的数量

### methods[]

方法表

包括此类或接口类型声明的所有方法，不包括表示从超类或超接口继承的方法。

### attributes_count

attributes[]的数量

### attributes[]

属性表

表中的每个值必须是一个 `attribute_info`结构

### 参考

- [字节码文件结构--陈树义](https://www.cnblogs.com/chanshuyi/p/jvm_serial_05_jvm_bytecode_analysis.html)