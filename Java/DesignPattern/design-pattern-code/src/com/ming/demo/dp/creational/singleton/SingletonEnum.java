package com.ming.demo.dp.creational.singleton;

/**
 * 单例枚举(枚举对象天生就是单例的)
 *
 * @author ming
 * @date 2020-09-22 14:11
 * <p>company: 杭州行装网络技术有限公司</p>
 * <p>修改履历：</p>
 */
public enum SingletonEnum {
    INSTANCE;
    public void run(){
        System.out.println("run code");
    }
}
