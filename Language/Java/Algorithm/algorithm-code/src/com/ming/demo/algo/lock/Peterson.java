package com.ming.demo.algo.lock;

public class Peterson implements Runnable {
    private static int threadCount = 2;
    private static int ticketsCount = 100;
    private static int turn = 0;// 客观地表示哪一个线程有权使用资源
    private static boolean[] ts = new boolean[threadCount];// 主观地表示某一个线程是否希望使用资源

    private int id;

    public Peterson(int id) {
        this.id = id;
    }

    public static void main(String[] args) {

        System.out.println("Peterson Algorithm");

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Peterson(i), "Thread-" + i).start();
        }

        Peterson.sleep(500);
        System.exit(0);
    }

    private void ticketsCountdown(String name) {
        ticketsCount--;
        System.out.println(name + " ticketsCount:" + ticketsCount);
        if (ticketsCount == 0) {
            System.out.println("tickets clear");
            Peterson.sleep(100);
            System.exit(0);
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (; ; ) {
            ts[id] = true; // 本线程主观希望使用资源
            turn = threadCount - id;// 客观让资源给其他线程
            while (ts[id] && turn == (threadCount - id)) {
                Peterson.sleep(5);
                System.out.println("thread-" + id + " ready");
            }
            ticketsCountdown("thread-" + id);
            ts[id] = false;// 本线程执行完毕，主观愿望达成

        }
    }
}