package com.ming.demo.dp.creational.factory;

/**
 * Vehicle 交通工具
 * @author ming
 * @date 2020-09-23 19:19
 */
public abstract class Vehicle implements Movable {
    @Override
    public void move() {
        System.out.println("->moveVehicle:"+this.getClass().getSimpleName());
    }
}
