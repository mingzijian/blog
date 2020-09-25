package com.ming.demo.dp.structural.proxy;

/**
 * Human
 *
 * @author ming
 * @date 2020-09-24 13:39
 */
public abstract class Human implements IMouth,IEars{

    /**
     * 说
     * @param to 对谁说
     * @param msg 说什么
     */
    public void say(Human to, String msg) {
        String s = String.format("%s对%s说：“%s”", this.getClass().getSimpleName(), to.getClass().getSimpleName(), msg);
        System.out.println(s);
        to.listen(this, msg);
    }

    /**
     * 听
     * @param from 听谁说
     * @param msg 说什么
     */
    public void listen(Human from, String msg) {
        String s = String.format("%s听%s说：“%s”", this.getClass().getSimpleName(), from.getClass().getSimpleName(), msg);
        System.out.println(s);
    }

    /**
     * 带话
     * @param from 帮谁带话
     * @param to 带话给谁
     * @param msg 什么话
     */
    public void take(Human from, Human to, String msg) {
        msg=String.format("%s让我对你说：‘%s’", from.getClass().getSimpleName(), msg);
        this.say(to,msg);
    }
}
