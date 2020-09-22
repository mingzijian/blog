package com.ming.demo.dp.creational.singleton;

/**
 * 饿汉单例
 * @author ming
 * @date 2020-09-22 13:26
 * <p>company: 杭州行装网络技术有限公司</p>
 * <p>修改履历：</p>
 */
public class HungrySingleton {
    private static HungrySingleton singleton =new HungrySingleton();
    private HungrySingleton(){}

    private static HungrySingleton getInstance(){
        return singleton;
    }
}
