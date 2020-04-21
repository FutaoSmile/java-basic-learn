package com.futao.basic.learn.thread.Java多线程编程核心技术;

import lombok.extern.slf4j.Slf4j;

/**
 * @author futao
 * @date 2020/4/7.
 */
@Slf4j
public class MT extends Thread {

    private boolean isContinue = true;

    @Override
    public void run() {
        while (isContinue) {
            // TODO: 2020/4/8 加上这行代码，就可以正常终止，可能是方法出栈之后，线程会再次读取公共堆栈上的数据
            log.info("-");
        }
        log.info("终止运行.......................................");
    }

    public static void main(String[] args) throws InterruptedException {
        MT mt = new MT();
        mt.start();
        Thread.sleep(2000L);
        log.info("{}", "尝试终止MT新线程...");
        mt.isContinue = false;
    }
}
