package com.ming.demo.dp.creational.singleton;

/**
 * 单例模式(Singleton Pattern)：
 * 单例模式确保某一个类只有一个实例，
 * 而且自行实例化并向整个系统提供这个实例，
 * 这个类称为单例类，它提供全局访问的方法。
 * @author ming
 * @date 2020-09-21 20:20
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {
        System.out.println("lazy:"
                +(LazySingleton.getInstance()==LazySingleton.getInstance()));

        System.out.println("hungry:"
                +(HungrySingleton.getInstance()==HungrySingleton.getInstance()));

        System.out.println("dcl:"
                +(DoubleCheckLockSingleton.getInstance()==DoubleCheckLockSingleton.getInstance()));

        System.out.println("sic:"
                +(StaticInnerClassSingleton.getInstance()==StaticInnerClassSingleton.getInstance()));

        System.out.println("es:"
                +(EnumSingleton.getInstance()==EnumSingleton.getInstance()));

        System.out.println("se:"
                +(SingletonEnum.INSTANCE==SingletonEnum.INSTANCE));

    }
}
