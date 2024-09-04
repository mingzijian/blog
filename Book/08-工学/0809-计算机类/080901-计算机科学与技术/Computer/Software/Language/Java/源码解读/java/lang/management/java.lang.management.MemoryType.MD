java.lang.management.MemoryType
---------------------------------------------------------
内存池的类型枚举
- 堆内存
- 非堆内存(Java虚拟机管理的堆外内存)

### 简要代码
```java
package java.lang.management;
/**
 * 内存池的类型
 */
public enum MemoryType {
    /** 堆内存类型 */
    HEAP("Heap memory"),
    /** 非堆内存类型 */
    NON_HEAP("Non-heap memory");
    
    // 内存类型描述
    private final String description;

    private MemoryType(String s) {
        this.description = s;
    }
    
    /**
     * 返回内存类型描述
     */
    public String toString() {
        return description;
    }

    private static final long serialVersionUID = 6992337162326171013L;

}
```

