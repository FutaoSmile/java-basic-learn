package com.futao.basic.learn.eventbus;

import com.futao.basic.learn.eventbus.listener.AL;
import com.futao.basic.learn.eventbus.listener.BL;
import com.futao.basic.learn.eventbus.listener.CL;
import com.google.common.eventbus.EventBus;

/**
 * @author futao
 * @date 2020/4/16.
 */
public class Guava {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("id");
        eventBus.register(new AL());
        eventBus.register(new BL());
        eventBus.register(new CL());

        eventBus.post(new EventMessage("mmm", "我是Data啊"));
    }
}
