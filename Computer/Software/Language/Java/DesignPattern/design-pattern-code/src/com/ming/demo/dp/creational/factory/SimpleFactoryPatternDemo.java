package com.ming.demo.dp.creational.factory;

/**
 * 简单工厂模式(Simple Factory Pattern)
 * @author ming
 * @date 2020-09-21 20:17
 */
public class SimpleFactoryPatternDemo {

    public Movable createMovable(String name){
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

    public Animal createAnimal(String name){
        Animal a;
        switch (name) {
            case "cat":
                a=new Cat();
                break;
            case "dog":
                a=new Dog();
                break;
            default:
                throw new RuntimeException("wrong name");
        }
        return a;
    }

    public Vehicle createVehicle(String name){
        Vehicle v;
        switch (name) {
            case "tank":
                v=new Tank();
                break;
            case "plane":
                v=new Plane();
                break;
            default:
                throw new RuntimeException("wrong name");
        }
        return v;
    }

    public static void main(String[] args) {
        SimpleFactoryPatternDemo d=new SimpleFactoryPatternDemo();
        d.createMovable("tank").move();
        d.createMovable("plane").move();
        d.createMovable("cat").move();
        d.createMovable("dog").move();

        d.createAnimal("dog").move();

        d.createVehicle("tank").move();

        MovableFactory tankFactory=new TankFactory();
        tankFactory.createMovable().move();

        MovableFactory planeFactory=new PlaneFactory();
        planeFactory.createMovable().move();
    }
}
