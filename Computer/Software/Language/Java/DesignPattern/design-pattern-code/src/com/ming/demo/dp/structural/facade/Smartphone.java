package com.ming.demo.dp.structural.facade;

/**
 * 智能手机
 * 功能整合
 * @author ming
 * @date 2020-09-25 11:01
 */
public class Smartphone {

    private final Mp4 mp4=new Mp4();
    private final GameMachine gameMachine=new GameMachine();
    private final Telephone telephone=new Telephone();
    private final Calculator calculator=new Calculator();

    public void playMusic(){
        mp4.playMusic();
    }
    public void playVideo(){
        mp4.playVideo();
    }
    public void playGame(){
        gameMachine.playGame();
    }
    public void call(){
        telephone.call();
    }
    public void calc(){
        calculator.calc();
    }
}
