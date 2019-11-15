package com.futao.basic.learn.thread._4线程安全_线程同步;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class Practice extends Thread {

    private static int balance = 0;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Practice.class) {
                balance += 1000;
            }
            log.info("余额为[{}]", balance);
        }
    }

    public static void main(String[] args) {
        new Practice().start();
        new Practice().start();
    }
}

@Slf4j
class P2 implements Runnable {

    private int balance = 0;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            balance += 1000;
            log.info("余额为[{}]", balance);
        }
    }

    public static void main(String[] args) {
        P2 p2 = new P2();
        new Thread(p2).start();
        new Thread(p2).start();
        new Thread(p2).start();
        new Thread(p2).start();
    }
}

/**
 * 交替打印
 */
@Slf4j
class Printer1 extends Thread {

    /**
     * 从
     */
    private static int from = 1;
    /**
     * 到
     */
    private static final int TO = 100;
    /**
     * 锁
     */
    private static final ReentrantLock LOCK = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            LOCK.lock();
            if (from <= TO) {
                log.info("打印:[{}]", from);
                from++;
            } else {
                LOCK.unlock();
                break;
            }
            LOCK.unlock();
        }
    }

    public static void main(String[] args) {
        new Printer1().start();
        new Printer1().start();
        new Printer1().start();
        new Printer1().start();
        new Printer1().start();
    }
}


@Slf4j
class Printer2 implements Runnable {

    /**
     * 从
     */
    private int from = 1;
    /**
     * 到
     */
    private final int to = 100;

    @Override
    public void run() {
        while (true) {
            synchronized (Printer2.class) {
                if (from <= to) {
                    log.info("打印:[{}]", from);
                    from++;
                } else {
                    break;
                }
                //唤醒一个线程
                Printer2.class.notify();
                try {
                    //阻塞当前线程并释放锁（下面的代码暂时也不会执行，需要等到线程被唤醒并获取到锁才能继续执行）
                    Printer2.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}", "1231--------");
                log.info("{}", "1231--------");
            }
        }
    }

    public static void main(String[] args) {
        Printer2 target = new Printer2();
        new Thread(target).start();
        new Thread(target).start();
        new Thread(target).start();
    }
}