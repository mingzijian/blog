FutureTask
------------------



```java
public void testTaskSynchronous() {
    long start = System.currentTimeMillis();
    Map<Integer, String> map = new HashMap<>();
    for (int i = 0; i < 10; i++) {
        final String id = Integer.toString(i);
        try {
            map.put(i, doTask(id));
        } catch (Exception e) {
            System.err.println("同步出错");
            return ;
        }
    }
    System.out.println(JSON.toJSONString(map));
    System.out.println("同步耗时：" + (System.currentTimeMillis() - start));
    System.out.println("======================================");
}


private String doTask(String s) throws InterruptedException {
    // 模拟耗时业务
    int i = new Random().nextInt(500) + 500;
    TimeUnit.MILLISECONDS.sleep(i);
    return s + i;
}
```