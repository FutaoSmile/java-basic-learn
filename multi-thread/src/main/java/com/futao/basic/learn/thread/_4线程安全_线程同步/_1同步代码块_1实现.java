package com.futao.basic.learn.thread._4线程安全_线程同步;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class _1同步代码块_1实现 implements Runnable {

    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {       //使用`_1同步代码块_1实现.class`也行，因为类也是对象，且这个对象只会被JVM加载一次
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
        _1同步代码块_1实现 同步代码块_1实现 = new _1同步代码块_1实现();
        new Thread(同步代码块_1实现, "一号").start();
        new Thread(同步代码块_1实现, "二号").start();
        new Thread(同步代码块_1实现, "三号").start();
        new Thread(同步代码块_1实现, "四号").start();
    }

}
