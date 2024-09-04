java.math.RoundingMode
----------------------------------------

指定能够丢弃精度的数值运算的舍入模式。
舍入模式枚举：
- UP 貔貅模式（只进不出，遇到正数不会变小，遇到负数不会变大）0.1->1 -1.1->-2  -1.9->-2
- DOWN 丢弃模式（零头不要了）0.9->0 -1.1->-1  -1.9->-1
- CEILING 飞天模式（向正无穷舍入，结果不会变小））0.1->1 -1.1->-1  -1.9->-1
- FLOOR 遁地模式（向负无穷舍入））0.1->0 -1.1->-2  -1.9->-2
- HALF_UP 四舍五（>=0.5）入  0.50 -> 1  0.51 -> 1
- HALF_DOWN 四舍五（>0.5）入  0.50 -> 0  0.51 -> 1
- HALF_EVEN 银行家取整(四舍六入，遇五判左，偶舍奇入) 0.5->0  1.5->2  -0.5->0  -1.5->-2
- UNNECESSARY 无需舍入（如果结果不精确，则抛出算术异常。）


```java
package java.math;
public enum RoundingMode {
    UP(BigDecimal.ROUND_UP),
    DOWN(BigDecimal.ROUND_DOWN),
    CEILING(BigDecimal.ROUND_CEILING),
    FLOOR(BigDecimal.ROUND_FLOOR),
    HALF_UP(BigDecimal.ROUND_HALF_UP),
    HALF_DOWN(BigDecimal.ROUND_HALF_DOWN),
    HALF_EVEN(BigDecimal.ROUND_HALF_EVEN),
    UNNECESSARY(BigDecimal.ROUND_UNNECESSARY);
    
    // 与 BigDecimal 的舍入模式对应
    final int oldMode;
    
    private RoundingMode(int oldMode) {
        this.oldMode = oldMode;
    }
    
    public static RoundingMode valueOf(int rm) {
        switch(rm) {

        case BigDecimal.ROUND_UP:
            return UP;

        case BigDecimal.ROUND_DOWN:
            return DOWN;

        case BigDecimal.ROUND_CEILING:
            return CEILING;

        case BigDecimal.ROUND_FLOOR:
            return FLOOR;

        case BigDecimal.ROUND_HALF_UP:
            return HALF_UP;

        case BigDecimal.ROUND_HALF_DOWN:
            return HALF_DOWN;

        case BigDecimal.ROUND_HALF_EVEN:
            return HALF_EVEN;

        case BigDecimal.ROUND_UNNECESSARY:
            return UNNECESSARY;

        default:
            throw new IllegalArgumentException("argument out of range");
        }
    }
}
```