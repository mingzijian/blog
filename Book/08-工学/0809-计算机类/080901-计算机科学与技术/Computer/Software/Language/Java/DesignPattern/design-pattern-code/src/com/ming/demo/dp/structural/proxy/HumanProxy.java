package com.ming.demo.dp.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理
 * @author ming
 * @date 2020-09-25 11:36
 */
public class HumanProxy implements InvocationHandler{

    private Human human;
    public HumanProxy(Human human){
        this.human=human;
    }

    public static void main(String[] args) {
        InvocationHandler handler=new HumanProxy(new Jack());
        IMouth mouth = (IMouth)Proxy.newProxyInstance(Human.class.getClassLoader(),Human.class.getInterfaces(), handler);
        mouth.say(new Pony(),"hi");

        IEars ears = (IEars)Proxy.newProxyInstance(Human.class.getClassLoader(),Human.class.getInterfaces(), handler);
        ears.listen(new William(),"hi");
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(human,args);
    }
}
