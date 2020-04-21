package com.futao.basic.learn.thread.imooc._1_1创建;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建多线程方式1-继承Thread
 *
 * @author futao
 * Created on 2019/11/12.
 */
@Slf4j
public class _1ExtendsThread extends Thread {     //1.继承自Thread类

    @Override
    public void run() {                         //2.重写run()方法
        log.info("线程启动。。。");
    }

    public static void main(String[] args) {
        _1ExtendsThread extendsThread = new _1ExtendsThread();      //该对象即是线程对象
        extendsThread.setName("继承Thread方式");                //设置线程名称
        extendsThread.start();                                  //3.调用start()方法启动线程(注意这里不是调用run()方法，如果调用了run()方法相当于调用普通方法，并不会启动新的线程)
    }
}
