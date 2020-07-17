Dubbo 随机端口
------------------------

### 场景
dubbo 端口占用
``` java
com.alibaba.dubbo.rpc.RpcException: Fail to start server
...
Failed to bind NettyServer on /xx.xx.xx.xx:20884, cause: Failed to bind to: /0.0.0.0:20884
...
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol.createServer(DubboProtocol.java:289)
...
	at com.alibaba.dubbo.registry.integration.RegistryProtocol.export(RegistryProtocol.java:107)
...
	at com.alibaba.dubbo.config.ServiceConfig.export(ServiceConfig.java:143)
	at com.alibaba.dubbo.config.spring.ServiceBean.onApplicationEvent(ServiceBean.java:109)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172)
...
	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4853)
...
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	
```

### 源码分析

com.alibaba.dubbo.config.ServiceConfig
```java
		...
		Integer port = protocolConfig.getPort();
        if (provider != null && (port == null || port == 0)) {
            port = provider.getPort();
        }
        final int defaultPort = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension(name).getDefaultPort();
        if (port == null || port == 0) {
            port = defaultPort;
        }
        if (port == null || port <= 0) {
        	// 端口号<0时,随机一个端口号
            port = getRandomPort(name);
            if (port == null || port < 0) {
                port = NetUtils.getAvailablePort(defaultPort);
                putRandomPort(name, port);
            }
            logger.warn("Use random available port(" + port + ") for protocol " + name);
        }
        ...

```

com.alibaba.dubbo.common.utils.NetUtils
```java
private static final int RND_PORT_START = 30000;
    
    private static final int RND_PORT_RANGE = 10000;
    
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    // 获取随机端口号
    public static int getRandomPort() {
        return RND_PORT_START + RANDOM.nextInt(RND_PORT_RANGE);
    }
```
com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol
```java
	// dubbo协议默认端口号 20880
	public static final int DEFAULT_PORT = 20880;
```

com.alibaba.dubbo.common.utils.NetUtils
```java
	public static int getAvailablePort() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket();
            ss.bind(null);
            return ss.getLocalPort();
        } catch (IOException e) {
            return getRandomPort();
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                }
            }
        }
    }
```




### 解决方案


#### 方案一 (基于XML配置,利用Dubbo源码实现)
```xml
<!-- 配置一个小于0的端口号,以触发生成随机端口号 -->
<dubbo:provider protocol="dubbo" port="-1"/>
```
参考 https://dubbo.apache.org/zh-cn/blog/dubbo-registry-nacos-integration.html

#### 方案二 (基于Java代码,手动调用Dubbo的getAvailablePort()方法)
```java
 
import java.util.Map;
import java.util.Map.Entry;
 
import javax.annotation.PostConstruct;
 
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
 
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.config.ProtocolConfig;
 
/**
 * @PostConstruct注解修饰的方法在构造器之后执行;
 * 在init()方法中调用Dubbo的NetUtils.getAvailablePort()方法获取一个可用端口;
 * 设置端口
 */
@Component
public class DynamicDubboPortReaderImpl implements ApplicationContextAware {
 
	@Autowired
	private ApplicationContext applicationContext;
	private int port = 20880;
 
	@PostConstruct
	public void init() {
		Map<String, ProtocolConfig> beansOfType = applicationContext.getBeansOfType(ProtocolConfig.class);
		for (Entry<String, ProtocolConfig> item : beansOfType.entrySet()) {
			port = NetUtils.getAvailablePort();
			System.out.println("====================================");
			System.out.println("===> dubbo available port:" + port);
			System.out.println("====================================");
			item.getValue().setPort(port);
		}
	}
 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
}
```




