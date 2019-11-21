package com.futao.basic.learn.thread._1_2线程的终止;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 错误的停止方法
 *
 * @author futao
 * Created on 2019/11/20.
 */
@Slf4j
public class _5WrongWay {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while (true) {
                log.info("--");
                try {
                    TimeUnit.MILLISECONDS.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        //已被弃用，（会释放所有的监视器）
        thread.stop();
    }


}
