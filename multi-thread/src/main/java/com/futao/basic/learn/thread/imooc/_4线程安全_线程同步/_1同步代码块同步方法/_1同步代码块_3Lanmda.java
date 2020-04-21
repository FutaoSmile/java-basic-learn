package com.futao.basic.learn.thread.imooc._4线程安全_线程同步._1同步代码块同步方法;

import lombok.extern.slf4j.Slf4j;

/**
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class _1同步代码块_3Lanmda {

    private static int ticket = 100;
    static final Object obj = new Object();

    private static void sale() {
        while (true) {
            try {               //可让结果更直观
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {        //同步监视器(锁)，任意对象。但是要求需要同步的对象使用的是同一把锁(即同一个对象)
                if (ticket > 0) {
                    log.info("卖出第{}张票", ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> sale());
//        t1.setDaemon(true);
        t1.start();

        Thread t2 = new Thread(() -> sale());
//        t2.setPriority(Thread.MAX_PRIORITY);
//        t2.setDaemon(true);
        t2.start();

    }
}
