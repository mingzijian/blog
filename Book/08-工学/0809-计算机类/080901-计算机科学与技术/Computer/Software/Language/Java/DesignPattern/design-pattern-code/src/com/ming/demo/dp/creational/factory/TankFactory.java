package com.ming.demo.dp.creational.factory;

/**
 * TankFactory
 * @author ming
 * @date 2020-09-24 09:18
 */
public class TankFactory extends VehicleFactory {
    @Override
    public Tank createMovable() {
        return new Tank();
    }
}
