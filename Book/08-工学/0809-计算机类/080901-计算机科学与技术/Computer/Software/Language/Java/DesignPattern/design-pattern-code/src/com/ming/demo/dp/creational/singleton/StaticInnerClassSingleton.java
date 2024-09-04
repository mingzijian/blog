package com.ming.demo.dp.creational.singleton;

/**
 * 静态内部类单例
 * @author ming
 * @date 2020-09-22 13:56
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton(){}

    public static StaticInnerClassSingleton getInstance(){
        return StaticInnerClassSingletonHolder.singleton;
    }
    private static class StaticInnerClassSingletonHolder{
        private static final StaticInnerClassSingleton singleton=new StaticInnerClassSingleton();
    }
}
