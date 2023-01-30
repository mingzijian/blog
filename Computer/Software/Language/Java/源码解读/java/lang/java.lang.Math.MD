java.lang.Math
-----------------------
Math类包含执行基本数值运算的方法，如初等指数、对数、平方根和三角函数。
其中许多方法委托给了StrictMath实现。

### 简要代码
```java
package java.lang;
import ...
public final class Math {
    /** 禁止实例化该类 */
    private Math() {}
    /** 最接近自然常数（e）的double值 */
    public static final double E = 2.7182818284590452354;
    /** 最接近圆周率（π）的double值 */
    public static final double PI = 3.14159265358979323846;
    /** 正弦 */
    public static double sin(double a) {
        return StrictMath.sin(a);
    }
    /** 余弦 */
    public static double cos(double a) {
        return StrictMath.cos(a);
    }
    /** 正切 */
    public static double tan(double a) {
        return StrictMath.tan(a);
    }
    /** 反正弦 */
    public static double asin(double a) {
        return StrictMath.asin(a);
    }
    /** 反余弦 */
    public static double acos(double a) {
        return StrictMath.acos(a);
    }
    /** 反正切 */
    public static double atan(double a) {
        return StrictMath.atan(a);
    }
    /** 角度转弧度 */
    public static double toRadians(double angdeg) {
        return angdeg / 180.0 * PI;
    }
    /** 弧度转角度 */
    public static double toDegrees(double angrad) {
        return angrad * 180.0 / PI;
    }
    /** 返回欧拉数e的a次幂 */
    public static double exp(double a) {
        return StrictMath.exp(a);
    }
    /** 返回a的自然对数（以e为底） */
    public static double log(double a) {
        return StrictMath.log(a); // default impl. delegates to StrictMath
    }
    /** 返回a的以10为底的对数 */
    public static double log10(double a) {
        return StrictMath.log10(a);
    }
    /** 平方根 */
    public static double sqrt(double a) {
        return StrictMath.sqrt(a);
    }
    /** 立方根 */
    public static double cbrt(double a) {
        return StrictMath.cbrt(a);
    }
    /** 计算IEEE754标准规定的两个参数的余数运算。 */
    public static double IEEEremainder(double f1, double f2) {
        return StrictMath.IEEEremainder(f1, f2);
    }
    /**  */
    public static double ceil(double a) {
        return StrictMath.ceil(a);
    }
    /**  */
    public static double floor(double a) {
        return StrictMath.floor(a);
    }
    /**  */
    public static double rint(double a) {
        return StrictMath.rint(a);
    }
    /**  */
    public static double atan2(double y, double x) {
        return StrictMath.atan2(y, x);
    }
    /**  */
    public static double pow(double a, double b) {
        return StrictMath.pow(a, b);
    }
    /**  */
    public static int round(float a) {
        ...
    }
    /**  */
    public static long round(double a) {
        ...
    }
    
    private static final class RandomNumberGeneratorHolder {
        static final Random randomNumberGenerator = new Random();
    }
    /** 返回带正号的双精度值，大于或等于0.0，小于1.0。返回值是从该范围内以（近似）均匀分布伪随机选择的。 */
    public static double random() {
        return RandomNumberGeneratorHolder.randomNumberGenerator.nextDouble();
    }
    /** 严谨相加，溢出抛异常 */
    public static int addExact(int x, int y) {
        int r = x + y;
        // 溢出判断666
        if (((x ^ r) & (y ^ r)) < 0) {
            throw new ArithmeticException("integer overflow");
        }
        return r;
    }
    /** 严谨相加，溢出抛异常 */
    public static long addExact(long x, long y) {
        long r = x + y;
        if (((x ^ r) & (y ^ r)) < 0) {
            throw new ArithmeticException("long overflow");
        }
        return r;
    }
    /** 严谨相减，溢出抛异常 */
    public static int subtractExact(int x, int y) {...}
    /** 严谨相减，溢出抛异常 */
    public static long subtractExact(long x, long y) {...}
    /** 严谨相乘，溢出抛异常 */
    public static int multiplyExact(int x, int y) {
        long r = (long)x * (long)y;
        if ((int)r != r) {
            throw new ArithmeticException("integer overflow");
        }
        return (int)r;
    }
    /** 严谨相乘，溢出抛异常 */
    public static long multiplyExact(long x, long y) {
        long r = x * y;
        long ax = Math.abs(x);
        long ay = Math.abs(y);
        if (((ax | ay) >>> 31 != 0)) {
            // 某些大于2^31的位可能导致溢出
            // 使用除法运算符检查结果
            // 特殊情况检查 Long.MIN_VALUE * -1
           if (((y != 0) && (r / y != x)) ||
               (x == Long.MIN_VALUE && y == -1)) {
                throw new ArithmeticException("long overflow");
            }
        }
        return r;
    }
    
    /** 严谨自增，溢出抛异常 */
    public static int incrementExact(int a) {
        if (a == Integer.MAX_VALUE) {
            throw new ArithmeticException("integer overflow");
        }
        return a + 1;
    }
     /** 严谨自增，溢出抛异常 */
    public static long incrementExact(long a) {
        if (a == Long.MAX_VALUE) {
            throw new ArithmeticException("long overflow");
        }
        return a + 1L;
    }
    /** 严谨自减，溢出抛异常 */
    public static int decrementExact(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException("integer overflow");
        }
        return a - 1;
    }
    /** 严谨自减，溢出抛异常 */
    public static long decrementExact(long a) {
        if (a == Long.MIN_VALUE) {
            throw new ArithmeticException("long overflow");
        }
        return a - 1L;
    }
    /** 严谨取负（反转指定数字的符号并返回它），溢出抛异常 */
    public static int negateExact(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException("integer overflow");
        }
        return -a;
    }
    /** 严谨取负（反转指定数字的符号并返回它），溢出抛异常 */
    public static long negateExact(long a) {
        if (a == Long.MIN_VALUE) {
            throw new ArithmeticException("long overflow");
        }
        return -a;
    }
    /** 严谨long转int，溢出抛异常 */
    public static int toIntExact(long value) {
        if ((int)value != value) {
            throw new ArithmeticException("integer overflow");
        }
        return (int)value;
    }
    /** 作商floor，第一个参数除以第二参数，然后针对结果执行floor操作，返回小于或等于商的整数（最接近正无穷大）。 */
    public static int floorDiv(int x, int y) {
        int r = x / y;
        // 如果符号不同且模不为零，则向下取整
        if ((x ^ y) < 0 && (r * y != x)) {
            r--;
        }
        return r;
    }
    /** 作商floor */
    public static long floorDiv(long x, long y) {
        long r = x / y;
        // 如果符号不同且模不为零，则向下取整
        if ((x ^ y) < 0 && (r * y != x)) {
            r--;
        }
        return r;
    }
    /** 取模floor */
    public static int floorMod(int x, int y) {
        int r = x - floorDiv(x, y) * y;
        return r;
    }
    /** 取模floor */
    public static long floorMod(long x, long y) {
        return x - floorDiv(x, y) * y;
    }
    /** 取绝对值（不会溢出） */
    public static int abs(int a) {
        return (a < 0) ? -a : a;
    }
    /** 取绝对值 */
    public static long abs(long a) {
        return (a < 0) ? -a : a;
    }
    /** 取绝对值 */
    public static float abs(float a) {
        return (a <= 0.0F) ? 0.0F - a : a;
    }
    /** 取绝对值 */
    public static double abs(double a) {
        return (a <= 0.0D) ? 0.0D - a : a;
    }
    
    /** 取大值 */
    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }
    /** 取大值 */
    public static long max(long a, long b) {
        return (a >= b) ? a : b;
    }
    
    // 在保证非NaN参数的情况下使用原始的位元转换
    private static long negativeZeroFloatBits  = Float.floatToRawIntBits(-0.0f);
    private static long negativeZeroDoubleBits = Double.doubleToRawLongBits(-0.0d);
    /** 取大值 */
    public static float max(float a, float b) {
        if (a != a)
            return a;   // NaN
        if ((a == 0.0f) &&
            (b == 0.0f) &&
            (Float.floatToRawIntBits(a) == negativeZeroFloatBits)) {
            // 原始转换正常，因为NaN无法映射到-0.0。
            return b;
        }
        return (a >= b) ? a : b;
    }
    /** 取大值 */
    public static double max(double a, double b) {
        if (a != a)
            return a;   // NaN
        if ((a == 0.0d) &&
            (b == 0.0d) &&
            (Double.doubleToRawLongBits(a) == negativeZeroDoubleBits)) {
            // 原始转换正常，因为NaN无法映射到-0.0。
            return b;
        }
        return (a >= b) ? a : b;
    }
    /** 取小值 */
    public static int min(int a, int b) {
        return (a <= b) ? a : b;
    }
    /** 取小值 */
    public static long min(long a, long b) {
        return (a <= b) ? a : b;
    }
    /** 取小值 */
    public static float min(float a, float b) {...}
    /** 取小值 */
    public static double min(double a, double b) {...}
    /** 
     * ulp(unit in the last place or unit of least precision (ULP))
     * ULP是计算机科学中的一个误差单位。
     * 计算机中保存的数和我们在数学上认为的数是不一样的；比方说2.0和3.0之间有多少个数，在数学中是无限的，但是在计算机中是有限的，因为计算机需要用一堆字节来表示double或者float，计算机表示不了无限的数（因为没有无限内存）。
     * 于是就有了ulp，假设在2.0f和3.0f之间有8,388,609个数，那么在2.0f和3.0f之间的数的ulp就是8,388,609/1.0约等于0.0000001。
     */
    public static double ulp(double d) {...}
    /** ulp */
    public static float ulp(float f) {...}
    /** 返回参数符号的函数；如果参数为 0，则返回 0；如果参数大于 0，则返回 1.0；如果参数小于 0，则返回 -1.0。 */
    public static double signum(double d) {
        return (d == 0.0 || Double.isNaN(d))?d:copySign(1.0, d);
    }
    /** 取符号 */
    public static float signum(float f) {
        return (f == 0.0f || Float.isNaN(f))?f:copySign(1.0f, f);
    }
    /** 双曲正弦 */
    public static double sinh(double x) {
        return StrictMath.sinh(x);
    }
    /** 双曲余弦 */
    public static double cosh(double x) {
        return StrictMath.cosh(x);
    }
    /** 
     * 双曲正切
     */
    public static double tanh(double x) {
        return StrictMath.tanh(x);
    }
    /** 返回没有中间溢出或下溢出的sqrt(x² +y²)。 */
    public static double hypot(double x, double y) {
        return StrictMath.hypot(x, y);
    }
    /** e的x次方 - 1 */
    public static double expm1(double x) {
        return StrictMath.expm1(x);
    }
    /** ln(x + 1) */
    public static double log1p(double x) {
        return StrictMath.log1p(x);
    }
    /** 返回带有第二个浮点参数符号的第一个浮点参数。 */
    public static double copySign(double magnitude, double sign) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(sign) &
                                        (DoubleConsts.SIGN_BIT_MASK)) |
                                       (Double.doubleToRawLongBits(magnitude) &
                                        (DoubleConsts.EXP_BIT_MASK |
                                         DoubleConsts.SIGNIF_BIT_MASK)));
    }
    /** 返回带有第二个浮点参数符号的第一个浮点参数。 */
    public static float copySign(float magnitude, float sign) {...}
    /** 返回参数的无偏指数 */
    public static int getExponent(float f) {...}
    /** 无偏指数 */
    public static int getExponent(double d) {...}
    /** 返回 start 和 direction 与 start 相邻的浮点值。若start==direction，则返回direction */
    public static double nextAfter(double start, double direction) {...}
    /** 下一个相邻浮点数 */
    public static float nextAfter(float start, double direction) {...}
    /** 返回 d 和正无穷大之间与 d 相邻的浮点值。 */
    public static double nextUp(double d) {...}
    /** 返回 f 和正无穷大之间与 f 相邻的浮点值。 */
    public static float nextUp(float f) {...}
    /** 返回 d 和负无穷大之间与 d 相邻的浮点值。 */
    public static double nextDown(double d) {...}
    /** 返回 f 和负无穷大之间与 f 相邻的浮点值。 */
    public static float nextDown(float d) {...}
    /** 以2的幂缩放自变量, 返回d乘以2的scaleFactor次方 */
    public static double scalb(double d, int scaleFactor) {...}
    /** 以2的幂缩放自变量 */
    public static float scalb(float f, int scaleFactor) {...}
    
    // 供 scalb 方法使用的常数
    static double twoToTheDoubleScaleUp = powerOfTwoD(512);
    static double twoToTheDoubleScaleDown = powerOfTwoD(-512);
    /** 返回一个正常范围内的2的次幂浮点数 */
    static double powerOfTwoD(int n) {
        assert(n >= DoubleConsts.MIN_EXPONENT && n <= DoubleConsts.MAX_EXPONENT);
        return Double.longBitsToDouble((((long)n + (long)DoubleConsts.EXP_BIAS) <<
                                        (DoubleConsts.SIGNIFICAND_WIDTH-1))
                                       & DoubleConsts.EXP_BIT_MASK);
    }
    /** 返回一个正常范围内的2的次幂浮点数 */
    static float powerOfTwoF(int n) {
        assert(n >= FloatConsts.MIN_EXPONENT && n <= FloatConsts.MAX_EXPONENT);
        return Float.intBitsToFloat(((n + FloatConsts.EXP_BIAS) <<
                                     (FloatConsts.SIGNIFICAND_WIDTH-1))
                                    & FloatConsts.EXP_BIT_MASK);
    }
    
}
```