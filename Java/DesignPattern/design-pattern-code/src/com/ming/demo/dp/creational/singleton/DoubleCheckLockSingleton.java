package com.ming.demo.dp.creational.singleton;

/**
 * DCL（双重检查锁）单例
 *
 * @author ming
 * @date 2020-09-22 13:31
 */
public class DoubleCheckLockSingleton {
    private static volatile DoubleCheckLockSingleton singleton;

    private DoubleCheckLockSingleton() {
    }

    public static DoubleCheckLockSingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckLockSingleton();
                }
            }
        }
        return singleton;
    }
}
