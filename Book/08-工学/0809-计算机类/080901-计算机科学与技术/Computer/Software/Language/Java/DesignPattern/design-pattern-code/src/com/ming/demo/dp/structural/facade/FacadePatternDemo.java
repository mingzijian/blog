package com.ming.demo.dp.structural.facade;

/**
 * 外观模式(Facade Pattern)：
 * 门面模式
 * 外部与一个子系统的通信必须通过一个统一的外观对象进行，
 * 为子系统中的一组接口提供一个一致的界面，
 * 外观模式定义了一个高层接口，
 * 这个接口使得这一子系统更加容易使用。
 * @author ming
 * @date 2020-09-21 20:26
 */
public class FacadePatternDemo {

    public static void main(String[] args) {
        Smartphone smartphone=new Smartphone();
        smartphone.calc();
        smartphone.call();
        smartphone.playGame();
        smartphone.playMusic();
        smartphone.playVideo();
    }
}
