package com.futao.basic.learn.eventbus.listener;

import com.futao.basic.learn.eventbus.EventMessage;
import com.google.common.eventbus.Subscribe;

/**
 * @author futao
 * @date 2020/4/16.
 */
public interface IEventMessage {

    @Subscribe
    void listener(EventMessage eventMessage);
}
