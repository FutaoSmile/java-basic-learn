package com.futao.basic.learn.thread._6线程通信._1生产者消费者问题.core;

import com.futao.basic.learn.thread._6线程通信._1生产者消费者问题.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 消费者
 *
 * @author futao
 * Created on 2019/11/15.
 */
@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class Customer extends Thread {

    private Clerk clerk;

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(Constant.CONSUMER_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.sell();
        }
    }
}
