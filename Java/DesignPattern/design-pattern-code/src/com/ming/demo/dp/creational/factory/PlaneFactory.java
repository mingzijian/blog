package com.ming.demo.dp.creational.factory;

/**
 * PlainFactory
 * @author ming
 * @date 2020-09-24 09:18
 */
public class PlaneFactory extends VehicleFactory {
    @Override
    public Plane createMovable() {
        return new Plane();
    }
}
