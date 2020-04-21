package com.futao.basic.learn.thread.Java多线程编程核心技术;

import lombok.extern.slf4j.Slf4j;

/**
 * @author futao
 * @date 2020/4/7.
 */
@Slf4j
public class Interept {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000000; i++) {
//                log.info("i={}", i);
//                System.out.println("");
            }
        });
        thread.start();
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isInterrupted());

        //interrupted()是静态方法，判断的是运行interrupted()代码的线程是否是中断状态，且该方法会将中断标记重置
        System.out.println(thread.interrupted());
        System.out.println(thread.interrupted());
        System.out.println(Thread.interrupted());
    }

}
