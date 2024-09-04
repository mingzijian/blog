JSON库Gson&FastJSON&Jackson性能对比
-----------------------------------
### 介绍
#### Gson
Gson是一个Java库，可用于将Java对象转换为其JSON表示。
它还可以用于将JSON字符串转换为等效的Java对象。
Gson可以处理任意Java对象，包括没有源代码的预先存在的对象。
 - 源代码 https://github.com/google/gson

#### FastJSON
Fastjson是一个Java库，可用于将Java对象转换为其JSON表示。
它还可以用于将JSON字符串转换为等效的Java对象。
Fastjson可以处理任意Java对象，包括没有源代码的预先存在的对象。
 - 源代码 https://github.com/alibaba/fastjson

#### Jackson
Jackson是一套Java（和JVM平台）的数据处理工具，
包括旗舰的流式JSON解析器/生成器库、
匹配数据绑定库（POJO到JSON和从JSON到POJO）和其他数据格式模块，
用于处理以Avro、BSON、CBOR、CSV、Smile、（Java）属性、Protobuf、XML或YAML编码的数据。
 - 源代码 https://github.com/FasterXML/jackson

### 性能对比
#### JSON库序列化性能对比
   （Gson 慢于 FastJSON 慢于 Jackson）
![JSON库序列化性能对比](https://images.gitee.com/uploads/images/2020/0618/190559_5b5ac7f1_536895.png "JSON库序列化性能对比图.png")

#### JSON库反序列化性能对比
   （Gson 慢于 Jackson 慢于 FastJSON）
![JSON库反序列化性能对比](https://images.gitee.com/uploads/images/2020/0618/190636_bd30528c_536895.png "JSON库反序列化性能对比图.png")

#### 参考
 - https://blog.csdn.net/accountwcx/article/details/50252657
