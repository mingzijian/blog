springdoc
-----------------------------
springdoc-openapi

OpenAPI 3
&
Spring Boot


pom.xml
```xml
    </dependencies>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-ui</artifactId>
            <version>1.5.8</version>
        </dependency>
    </dependencies>
```

application.yml
```yml
server:
  port: 8810
  servlet:
    context-path: /

springdoc:
  api-docs:
    enabled: true
  swagger-ui:
    path: /swagger-ui
```

就是这么简单
http://127.0.0.1:8810/swagger-ui

### 参考
- [官网](https://springdoc.org/)
