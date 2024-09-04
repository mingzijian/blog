package com.ming.demo.dp.creational.factory;

/**
 * VehicleFactory
 * @author ming
 * @date 2020-09-24 09:19
 */
public abstract class VehicleFactory implements MovableFactory {



    @Override
    public Movable createMovable() {
        return null;
    }
}
