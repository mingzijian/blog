package com.ming.demo.dp.structural.proxy;

/**
 * 代理模式(Proxy Pattern)：
 * 给某一个对象提供一个代理，
 * 并由代理对象控制对原对象的引用。
 * @author ming
 * @date 2020-09-21 20:24
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Human jack=new Jack();
        Human pony=new Pony();
        Human william=new William();
        Human richard=new Richard();
        // jack直接对pony说
        jack.say(pony,"hi");
        // william替jack对pony说
        william.take(jack,pony,"hi");
        // jack把pony的话带给richard
        jack.take(pony,richard,"hi");
        // pony直接对richard说
        pony.say(richard,"hi");
    }
}
