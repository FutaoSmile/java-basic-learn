package com.futao.basic.learn.thread.imooc._3常用方法;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019/11/22.
 */
@Slf4j
public class _2Join原理 implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{}", "子线程执行完毕");
        synchronized (this) {
            this.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        _2Join原理 target = new _2Join原理();
        Thread thread = new Thread(target);
        thread.start();
        log.info("等待子线程执行完毕");
//        thread.join();
        //不用join()来实现join()的效果
        synchronized (target) {
            target.wait();
        }
        log.info("子线程已经执行完毕了");
    }
}


//--------------------------------------------------------
//更简单的方法---
//--------------------------------------------------------
@Slf4j
class MoreSimple implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException("~~");             //演示即使线程中发生了异常，也会被唤醒
        log.info("子线程执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MoreSimple());
        thread.start();
        log.info("等待子线程执行完毕~");
//        thread.join();
        synchronized (thread) {
            thread.wait();      //当Thread的run()方法执行完毕之后，会自动调用Thread的notifyAll()方法--即时线程中发生了异常，也会被唤醒
        }
        log.info("子线程已经执行完毕了~");
    }
}