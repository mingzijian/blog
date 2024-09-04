package com.ming.demo.dp.creational.factory;

/**
 * Animal 动物
 * @author ming
 * @date 2020-09-23 19:33
 */
public abstract class Animal implements Movable{
    @Override
    public void move() {
        System.out.println("->moveAnimal:"+this.getClass().getSimpleName());
    }
}
