package com.futao.basic.learn.eventbus.listener;

import com.alibaba.fastjson.JSON;
import com.futao.basic.learn.eventbus.EventMessage;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author futao
 * @date 2020/4/16.
 */
@Slf4j
public class BL implements IEventMessage {
    @Subscribe
    @Override
    public void listener(EventMessage eventMessage) {
        log.info("接收到消息了{}", JSON.toJSONString(eventMessage));
    }
}
