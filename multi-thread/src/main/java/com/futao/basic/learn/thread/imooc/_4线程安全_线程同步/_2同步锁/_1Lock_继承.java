package com.futao.basic.learn.thread.imooc._4线程安全_线程同步._2同步锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class _1Lock_继承 extends Thread {

    private static int ticket = 1000;
    private static ReentrantLock lock = new ReentrantLock(true);    //如果是采用继承的方式，则需要将锁设置成静态的，保证锁的唯一性(true false 是否公平锁)

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
        new _1Lock_继承().start();
        new _1Lock_继承().start();
        new _1Lock_继承().start();
    }
}
