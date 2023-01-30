package com.ming.demo.dp.creational.singleton;

/**
 * 饿汉单例
 *
 * @author ming
 * @date 2020-09-22 13:26
 */
public class HungrySingleton {
    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return singleton;
    }
}
