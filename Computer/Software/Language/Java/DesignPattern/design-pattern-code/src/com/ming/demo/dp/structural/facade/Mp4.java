package com.ming.demo.dp.structural.facade;

/**
 * Mp4
 * @author ming
 * @date 2020-09-25 10:55
 */
public class Mp4 {

    private final Mp3 mp3=new Mp3();
    public void playMusic(){
        mp3.playMusic();
    }

    public void playVideo(){
        System.out.println("play video");
    }
}
