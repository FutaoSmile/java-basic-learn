package com.futao.basic.learn.thread.imooc._10synchronized.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author futao
 * @date 2020/3/14.
 */
public class LockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.tryLock();
        lock.lock();
        lock.unlock();
    }
}
