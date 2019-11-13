package com.futao.learn.thread._3常用方法;

import lombok.extern.slf4j.Slf4j;

/**
 * join()方法的使用
 * 在A线程中调用B.join()将阻塞线程A的执行，直到线程B执行完毕，A才继续执行
 *
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class _1join {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            log.info("{}", "start---");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{}", "end---");
        });
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100L);
            log.info("I:{}", i);
            if (i == 50) {
                threadA.start();
                threadA.join();      //main线程等待`threadA`执行完毕再继续执行
            }
        }
    }
}

