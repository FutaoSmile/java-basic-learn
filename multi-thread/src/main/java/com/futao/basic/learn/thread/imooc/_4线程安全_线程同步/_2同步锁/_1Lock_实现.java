package com.futao.basic.learn.thread.imooc._4线程安全_线程同步._2同步锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock与synchronized
 * Lock需要手动启动同步获取锁并且手动释放锁
 * Lock只能锁定代码块，synchronized还可以锁定方法
 *
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class _1Lock_实现 implements Runnable {

    private static int ticket = 100;

    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (ticket > 0) {
                log.info("卖出第{}张票", ticket);
                ticket--;
            } else {
                lock.unlock();
                break;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        _1Lock_实现 t1 = new _1Lock_实现();

        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t1).start();

    }
}
