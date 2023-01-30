package com.ming.demo.dp.structural.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * 三石
 * @author ming
 * @date 2020-09-24 13:53
 * <p>company: 杭州行装网络技术有限公司</p>
 * <p>修改履历：</p>
 */
public class William extends Human{
    private List<String> refuseListenList;

    public William() {
        refuseListenList=new ArrayList<>();
        refuseListenList.add("Pony");
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
