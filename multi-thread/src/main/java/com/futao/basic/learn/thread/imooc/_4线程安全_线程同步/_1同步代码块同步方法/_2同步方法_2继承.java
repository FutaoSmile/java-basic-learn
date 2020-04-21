package com.futao.basic.learn.thread.imooc._4线程安全_线程同步._1同步代码块同步方法;

import lombok.extern.slf4j.Slf4j;

/**
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class _2同步方法_2继承 extends Thread {

    private static int ticket = 1000;

    @Override
    public void run() {
        while (sell() > 0) {
            sell();
        }
    }

    private static synchronized int sell() {            //如果是静态的方法synchronized锁的是类`A.class（_2同步方法_2继承.class)`对象，如果是实例方法则锁住的是当前对象
        if (ticket > 0) {
            log.info("卖出第{}张票", ticket);
            ticket--;
        }
        return ticket;
    }

    public static void main(String[] args) {
        new _2同步方法_2继承().start();
        new _2同步方法_2继承().start();
        new _2同步方法_2继承().start();
    }
}
