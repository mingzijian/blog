java.math.BigDecimal
----------------------------------
不可变的、任意精度的有符号十进制数。

### 简要源码
```java
package java.math;
import ...
public class BigDecimal extends Number implements Comparable<BigDecimal> {

    // 构造
    
    // 运算
    // 加 add
    // 减 subtract
    // 乘 multiply
    // 除 divide
    
    // 幂 pow
    // 绝对值 abs
    
    // 比较 compareTo
    
    // 设置精度 setScale
    
    // 类型转换
    
    // 最大和最小长整型值的一半
    private static final long HALF_LONG_MAX_VALUE = Long.MAX_VALUE / 2;
    private static final long HALF_LONG_MIN_VALUE = Long.MIN_VALUE / 2;
    
    // 10^0 至 10^18
    private static final long[] LONG_TEN_POWERS_TABLE = {
        1,                     // 0 / 10^0
        10,                    // 1 / 10^1
        100,                   // 2 / 10^2
        1000,                  // 3 / 10^3
        10000,                 // 4 / 10^4
        100000,                // 5 / 10^5
        1000000,               // 6 / 10^6
        10000000,              // 7 / 10^7
        100000000,             // 8 / 10^8
        1000000000,            // 9 / 10^9
        10000000000L,          // 10 / 10^10
        100000000000L,         // 11 / 10^11
        1000000000000L,        // 12 / 10^12
        10000000000000L,       // 13 / 10^13
        100000000000000L,      // 14 / 10^14
        1000000000000000L,     // 15 / 10^15
        10000000000000000L,    // 16 / 10^16
        100000000000000000L,   // 17 / 10^17
        1000000000000000000L   // 18 / 10^18
    };
    // 10^19 至 10^38
    private static final long[][] LONGLONG_TEN_POWERS_TABLE = {
        {   0L, 0x8AC7230489E80000L },  //10^19
        {       0x5L, 0x6bc75e2d63100000L },  //10^20
        {       0x36L, 0x35c9adc5dea00000L },  //10^21
        {       0x21eL, 0x19e0c9bab2400000L  },  //10^22
        {       0x152dL, 0x02c7e14af6800000L  },  //10^23
        {       0xd3c2L, 0x1bcecceda1000000L  },  //10^24
        {       0x84595L, 0x161401484a000000L  },  //10^25
        {       0x52b7d2L, 0xdcc80cd2e4000000L  },  //10^26
        {       0x33b2e3cL, 0x9fd0803ce8000000L  },  //10^27
        {       0x204fce5eL, 0x3e25026110000000L  },  //10^28
        {       0x1431e0faeL, 0x6d7217caa0000000L  },  //10^29
        {       0xc9f2c9cd0L, 0x4674edea40000000L  },  //10^30
        {       0x7e37be2022L, 0xc0914b2680000000L  },  //10^31
        {       0x4ee2d6d415bL, 0x85acef8100000000L  },  //10^32
        {       0x314dc6448d93L, 0x38c15b0a00000000L  },  //10^33
        {       0x1ed09bead87c0L, 0x378d8e6400000000L  },  //10^34
        {       0x13426172c74d82L, 0x2b878fe800000000L  },  //10^35
        {       0xc097ce7bc90715L, 0xb34b9f1000000000L  },  //10^36
        {       0x785ee10d5da46d9L, 0x00f436a000000000L  },  //10^37
        {       0x4b3b4ca85a86c47aL, 0x098a224000000000L  },  //10^38
    };
    
    // 常用小BigDecimal值的缓存。
    private static final BigDecimal zeroThroughTen[] = {
        new BigDecimal(BigInteger.ZERO,       0,  0, 1),
        new BigDecimal(BigInteger.ONE,        1,  0, 1),
        new BigDecimal(BigInteger.valueOf(2), 2,  0, 1),
        new BigDecimal(BigInteger.valueOf(3), 3,  0, 1),
        new BigDecimal(BigInteger.valueOf(4), 4,  0, 1),
        new BigDecimal(BigInteger.valueOf(5), 5,  0, 1),
        new BigDecimal(BigInteger.valueOf(6), 6,  0, 1),
        new BigDecimal(BigInteger.valueOf(7), 7,  0, 1),
        new BigDecimal(BigInteger.valueOf(8), 8,  0, 1),
        new BigDecimal(BigInteger.valueOf(9), 9,  0, 1),
        new BigDecimal(BigInteger.TEN,        10, 0, 2),
    };
    // 0
    public static final BigDecimal ZERO = zeroThroughTen[0];
    // 1
    public static final BigDecimal ONE = zeroThroughTen[1];
    // 10
    public static final BigDecimal TEN = zeroThroughTen[10];
    
    // 舍入模式，参考 java.math.RoundingMode 枚举类
    public final static int ROUND_UP =           0;
    public final static int ROUND_DOWN =         1;
    public final static int ROUND_CEILING =      2;
    public final static int ROUND_FLOOR =        3;
    public final static int ROUND_HALF_UP =      4;
    public final static int ROUND_HALF_DOWN =    5;
    public final static int ROUND_HALF_EVEN =    6;
    public final static int ROUND_UNNECESSARY =  7;
    
    
}

```

### 最佳实践
尽量使用 BigDecimal 代替 double/float 。
尽量使用参数类型为String的构造函数。
除法（divide）运算时，尽量指定保留小数位数和舍入模式。
多步运算时，尽量把除法运算放在最后。

**注意**
BigDecimal 是不可变的（Immutable），运算完成之后别忘记赋值运算结果。

