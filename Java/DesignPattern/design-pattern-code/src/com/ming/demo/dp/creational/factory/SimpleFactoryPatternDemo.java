package com.ming.demo.dp.creational.factory;

/**
 * 简单工厂模式(Simple Factory Pattern)
 * @author ming
 * @date 2020-09-21 20:17
 */
public class SimpleFactoryPatternDemo {

    public Movable create(String name){
        Movable m;
        switch (name) {
            case "tank":
                m=new Tank();
                break;
            case "plane":
                m=new Plane();
                break;
            case "cat":
                m=new Cat();
                break;
            case "dog":
                m=new Dog();
                break;
            default:
                throw new RuntimeException("wrong name");

        }
        return m;
    }

    public static void main(String[] args) {
        SimpleFactoryPatternDemo d=new SimpleFactoryPatternDemo();
        d.create("tank").move();
        d.create("plane").move();
        d.create("cat").move();
        d.create("dog").move();
    }
}
