for(;;) & while(true)
------------------------------
`Test.java`
```java
public class Test {
  public static void testFor(){
    for(;;);
  }

  public static void testWhile(){
    while(true);
  }
}
```
```shell
javac Test.java
javap -c Test.class
```
```java
Compiled from "Test.java"
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void testFor();
    Code:
       0: goto          0

  public static void testWhile();
    Code:
       0: goto          0
}
```

![for(;;) & while(true)](https://images.gitee.com/uploads/images/2020/0629/173752_2a5f47b1_536895.png "for(;;)&while(true).png")
