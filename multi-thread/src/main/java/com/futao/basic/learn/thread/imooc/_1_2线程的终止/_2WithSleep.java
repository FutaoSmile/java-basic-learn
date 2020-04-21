package com.futao.basic.learn.thread.imooc._1_2线程的终止;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 在JDK的设计过程中可以发现，所有能让线程阻塞的方法(sleep wait..)都可能会抛出InterruptException(并且清除线程的终止状态，所以JAVA的设计者才设计成抛出异常，让程序员自己来处理)，
 * 需要显示地处理
 * 是因为线程阻塞过程中，如果调用了线程的interrupt()方法，会抛出InterruptException异常
 *
 * @author futao
 * Created on 2019/11/20.
 */
@Slf4j
public class _2WithSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    log.info("{}是100的整数倍", num);
                }
                num++;
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
