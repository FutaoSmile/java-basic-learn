package com.futao.basic.learn.thread.imooc._7线程状态;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 演示线程的状态-New, Runnable, Terminated
 *
 * @author futao
 * Created on 2019/11/21.
 */
@Slf4j
public class _2NEW_RUNABLE_TERMINATED {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println(i);
            }
        });
        log.info("{}", thread.getState());              //打印出NEW
        thread.start();
        //主线程等待10毫秒
        TimeUnit.MILLISECONDS.sleep(10L);
        log.info("{}", thread.getState());              //打印出RUNNABLE->（在JAVA中就绪和运行中线程的状都是RUNNABLE）
        //主线程等待2S
        TimeUnit.SECONDS.sleep(2L);
        log.info("{}", thread.getState());              //打印出TERMINATED->线程已经终止
    }
}
