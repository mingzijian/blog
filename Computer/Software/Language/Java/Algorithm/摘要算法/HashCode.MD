HashCode 
---------
#### Java 源码
Object.hashCode()
```java
    /**
     * Returns a hash code for this string. The hash code for a
     * {@code String} object is computed as
     * <blockquote><pre>
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     * </pre></blockquote>
     * using {@code int} arithmetic, where {@code s[i]} is the
     * <i>i</i>th character of the string, {@code n} is the length of
     * the string, and {@code ^} indicates exponentiation.
     * (The hash value of the empty string is zero.)
     *
     * @return  a hash code value for this object.
     */
    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }  
```

#### 实验
```java

    public static void main(String[] args) {
        System.out.println("a".hashCode());// 97
        System.out.println("b".hashCode());// 98
        System.out.println((int)'a');// 97
        System.out.println(((int)'b');// 98
        System.out.println("ab".hashCode());// 3105
        System.out.println((31*0) + ('a'));// 0+97 = 97
        System.out.println((31*((31*0) + ('a'))) + ('b'));// 31*97 + 98 = 3105
        System.out.println(getHashCode("ab"));// 3105  
    }

    static int getHashCode(String s){
        int h =0;
        char[] value = "ab".toCharArray();
        if ( value.length > 0) {
            for (char c : value) {
                h = 31 * h + c;
            }
        }
        return h;
    }
```