Dubbo 服务调用超时
-----------------------------
Consumer 调用 Provider 提供的服务，Provider 长时间（超过设置的超时时间）未响应。

### 测试代码

Dubbo Version : 2.6.2
com.alibaba:dubbo:2.6.2

#### Consumer 代码

```java
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMain.class)
public class RpcTimeoutTest {

	// Consumer 设置超时6s
    @Reference(version = "v20200721-test",timeout = 6000)
    private RpcTimeoutTestService rpcTimeoutTestService;

    @Test
    public void testTimeout(){
        long start = System.currentTimeMillis();
        try {
            String sleep = rpcTimeoutTestService.sleep(5);
            System.out.println(sleep);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("执行耗时"+(System.currentTimeMillis()-start));

    }
}
```
#### Provider 代码
```java
// Provider 设置超时3s
@Service(version = "v20200721-test",timeout = 3000)
public class RpcTimeoutTestServiceImpl implements RpcTimeoutTestService {
    @Override
    public String sleep(int second) {
        try {
            for (int i = 0; i < second; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            return "ignore error";
        }
        return "wake up";
    }
}
```


### 测试参数及打印结果
#### 场景1
##### 测试参数
Consumer 设置超时时间为：6s
Provider 设置超时时间为：3s
Provider 执行循环执行5次sleep1s
##### 打印结果
- consumer
wake up
执行耗时5067
- provider
0
1
2
3
4

#### 场景2
##### 测试参数
Consumer 设置超时时间为：3s
Provider 设置超时时间为：6s
Provider 执行循环执行5次sleep1s
##### 打印结果
- consumer
com.alibaba.dubbo.rpc.RpcException ...
Caused by: com.alibaba.dubbo.remoting.TimeoutException: Waiting server-side response timeout by scan timer. start time: 2020-07-21 10:46:10.923, end time: 2020-07-21 10:46:13.954, client elapsed: 40 ms, server elapsed: 2990 ms, timeout: 3000 ms,...
执行耗时3042
- provider
0
1
2
3
4


#### 场景3
##### 测试参数
(不设置超时时间，默认为0s，不会超时)
Consumer 设置超时时间为：0s 
Provider 设置超时时间为：0s
Provider 执行循环执行5次sleep1s
##### 打印结果
- consumer
wake up
执行耗时5155
- provider
0
1
2
3
4
#### 更多组合场景
略

### 结论
1. Consumer 设置超时时间 > 0 时
	以Consumer 设置的超时时间为准，覆盖Provider设置的超时时间
	
2. Consumer 未设置超时时间（或设置为：0）时
	以Provider设置的超时时间为准，若Provider也未设置超时时间，则不会触发超时
	
3. 调用超时后
	Consumer会出现一个RpcException，但是Provider会继续执行
	
4. 默认超时时间

    ```java
    // com.alibaba.dubbo.common.Constants
    public static final int DEFAULT_IDLE_TIMEOUT = 600 * 1000;
    public static final int DEFAULT_TIMEOUT = 1000;
    public static final int DEFAULT_CONNECT_TIMEOUT = 3000;
    public static final int DEFAULT_SHUTDOWN_TIMEOUT = 1000 * 60 * 15;
    public static final int DEFAULT_SESSION_TIMEOUT = 60 * 1000;
    public static final int DEFAULT_SERVER_SHUTDOWN_TIMEOUT = 10000;
    
    ```

	2147483647ms   (Integer.MAX_VALUE  2^31-1) 
```java
package com.alibaba.dubbo.rpc.filter;
...
@Activate(group = Constants.PROVIDER)
public class TimeoutFilter implements Filter {
	...
	@Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    	long start = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        long elapsed = System.currentTimeMillis() - start;
        if (invoker.getUrl() != null
                && elapsed > invoker.getUrl().getMethodParameter(invocation.getMethodName(),
                "timeout", Integer.MAX_VALUE)) {
            if (logger.isWarnEnabled()) {
                logger.warn("invoke time out. method: " + invocation.getMethodName()
                        + " arguments: " + Arrays.toString(invocation.getArguments()) + " , url is "
                        + invoker.getUrl() + ", invoke elapsed " + elapsed + " ms.");
            }
        }
        return result;
    }
	...
}
```


	1000ms
  ```java
  package com.alibaba.dubbo.rpc.protocol.dubbo;
  ...
  public class DubboInvoker<T> extends AbstractInvoker<T> {
  	@Override
   protected Result doInvoke(final Invocation invocation) throws Throwable {
   	...
   		int timeout = getUrl().getMethodParameter(methodName, Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT);
   	...
   	}
  }
  
  
  ```

  ```java
  package com.alibaba.dubbo.common;
  ...
  public class Constants {
  ...
   	public static final int DEFAULT_TIMEOUT = 1000;
   ...
   }
  ```

6. 优先级别(就近原则)

     - consumer 端配置 > provider 端配置 > 全局配置

       ​	consumer端未配置或配置为0时,provider端配置生效,

       ​	provider端未配置或配置为0时,全局配置生效.

     - method 配置 > Interface 配置
### 建议
一般情况下建议在Provider上设置超时时间。



### 参考
- http://dubbo.apache.org/zh-cn/docs/source_code_guide/service-invoking-process.html