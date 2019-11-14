package com.futao.basic.learn.thread._4线程安全_线程同步;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class _1同步代码块_2继承 extends Thread {

    private static int ticket = 100;
    //当使用继承方式时，需要保证锁是唯一时，需要将锁设置成静态的
    static final Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {            //可以使用当前对象作为同步监视器    A.class
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
        new _1同步代码块_2继承().start();
        new _1同步代码块_2继承().start();
    }
}
