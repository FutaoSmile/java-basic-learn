package com.futao.basic.learn.thread._1创建;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建多线程方式2-实现Runnable接口
 *
 * @author futao
 * Created on 2019/11/12.
 */
@Slf4j
public class _2ImplRunnable implements Runnable {         //1.实现Runnable接口

    @Override
    public void run() {                                 //2/重写run()方法
        log.info("线程启动...");
    }

    public static void main(String[] args) {
        //实现Runnable方法
        _2ImplRunnable runnable = new _2ImplRunnable();     //该对象仅仅作为Thread的target，因为Runnable接口中没有start方法
        Thread thread = new Thread(runnable);
        thread.setName("实现Runnable接口");
        thread.start();                                 //3.调用线程的start()方法启动线程


        //Lambda表达式的方式
        new Thread(() -> log.info("Lambda表达式的方式")).start();

        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("匿名内部类的方式");
            }
        }).start();

        new Thread() {
            @Override
            public void run() {
                log.info("---");
            }
        }.start();
    }

}
