package com.ming.demo.dp.structural.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * 强子
 *
 * @author ming
 * @date 2020-09-24 13:45
 */
public class Richard extends Human {
    private List<String> refuseListenList;

    public Richard() {
        refuseListenList=new ArrayList<>();
        refuseListenList.add("Jack");
    }

    /**
     * 听
     *
     * @param from 听谁说
     * @param msg  说什么
     */
    @Override
    public void listen(Human from, String msg) {
        if(this.refuseListenList!=null && this.refuseListenList.contains(from.getClass().getSimpleName())){
            System.out.println(this.getClass().getSimpleName()+"：我不听");
            return;
        }
        super.listen(from,msg);
    }
}
