package com.futao.basic.learn.thread.imooc._4线程安全_线程同步._1同步代码块同步方法;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程安全问题：多个线程同时操作同一个共享账户
 *
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class _2同步方法_1实现 implements Runnable {

    private static int ticket = 10000;


    @SneakyThrows
    private synchronized int sell() {
//        TimeUnit.MILLISECONDS.sleep(5L);
        if (ticket > 0) {
            log.info("卖出第{}张票", ticket);
            ticket--;
        }
        return ticket;
    }

    @Override
    public void run() {
        while (sell() > 0) {
            sell();
        }
    }

    public static void main(String[] args) {
        _2同步方法_1实现 同步方法 = new _2同步方法_1实现();
        new Thread(同步方法).start();
        new Thread(同步方法).start();
        new Thread(同步方法).start();
    }
}

