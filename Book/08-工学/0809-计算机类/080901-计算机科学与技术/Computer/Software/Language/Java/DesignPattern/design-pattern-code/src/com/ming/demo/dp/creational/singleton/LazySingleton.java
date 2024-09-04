package com.ming.demo.dp.creational.singleton;

/**
 * 懒汉单例
 * @author ming
 * @date 2020-09-22 13:17
 */
public class LazySingleton {
    private static LazySingleton singleton;
    private LazySingleton(){}

    public static synchronized LazySingleton getInstance(){
        if(singleton==null){
            singleton=new LazySingleton();
        }
        return singleton;
    }
}
