package com.ming.demo.dp.creational.singleton;

/**
 * 枚举单例
 * @author ming
 * @date 2020-09-22 14:18
 * <p>company: 杭州行装网络技术有限公司</p>
 * <p>修改履历：</p>
 */
public class EnumSingleton {
    private EnumSingleton(){}

    private enum SingletonEnum{
        INSTANCE;
        private EnumSingleton instance;
        SingletonEnum(){
            instance=new EnumSingleton();
        }
        private EnumSingleton getInstance(){
            return instance;
        }
    }
    public static  EnumSingleton getInstance(){
        return SingletonEnum.INSTANCE.getInstance();
    }
}
