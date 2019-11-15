package com.futao.basic.learn.thread._6线程通信._1生产者消费者问题.core;

import com.futao.basic.learn.thread._6线程通信._1生产者消费者问题.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 店员
 *
 * @author futao
 * Created on 2019/11/15.
 */
@Slf4j
public class Clerk {

    /**
     * 当前持有--（这是线程共享数据)
     */
    private static int currentHold = 0;

    /**
     * 进货
     */
    synchronized void add() {
        if (currentHold < Constant.MAX_HOLD) {
            currentHold++;
            log.info("取货，当前店内产品数量为[{}]个", currentHold);
            //通知消费者继续消费
            notify();
        } else {
            //当前持有的产品数量已经是最大持有量，则通知生产者等一下
            try {
                log.info("{}店内产品已经达到最大可容纳数量，请生产者暂停生产", StringUtils.repeat("-", 30));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 售货
     */
    synchronized void sell() {
        if (currentHold > 0) {
            //售卖
            log.info("售卖第[{}]个产品，剩余[{}]个", currentHold, --currentHold);
            //通知生产者继续生产
            notify();
        } else {
            //店内产品不足，通知消费者等等
            try {
                log.info("{}店内产品不足，请消费者等待", StringUtils.repeat("=", 30));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
