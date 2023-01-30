FutureTask
------------------



```java

	@Test
    public void testTask() {
        testTaskAsynchronous();//异步耗时：1037

        testTaskSynchronous();//同步耗时：7651
    }

    public void testTaskAsynchronous() {
            long start = System.currentTimeMillis();
            ExecutorService executor = Executors.newFixedThreadPool(10);
            Map<Integer, String> map = new HashMap<>();
            List<FutureTask> tasks = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final String id = Integer.toString(i);
                FutureTask<String> task = new FutureTask<>(() -> doTask(id));
                executor.submit(task);
                tasks.add(task);
            }
            for (int i = 0; i < tasks.size(); i++) {
                FutureTask<String> task = tasks.get(i);
                try {
                    map.put(i, task.get());
                } catch (Exception e) {
                    System.err.println("异步出错");
                    return ;
                }
            }
            System.out.println(JSON.toJSONString(map));
            System.out.println("异步耗时：" + (System.currentTimeMillis() - start));
            executor.shutdown();
            System.out.println("======================================");

        }

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