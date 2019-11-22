package com.futao.basic.learn.thread._6线程通信._2交替打印;

import lombok.extern.slf4j.Slf4j;

/**
 * notify()与wait()配合交替打印数字
 *
 * @author futao
 * Created on 2019/11/21.
 */
@Slf4j
public class _1Printer implements Runnable {

    private static int num = 0;

    public static void main(String[] args) {
        _1Printer target = new _1Printer();
        new Thread(target).start();
        new Thread(target).start();
    }

    @Override
    public void run() {
        try {
            print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void print() throws InterruptedException {
        while (true) {
            notifyAll();
            if (num < 100) {
                log.info("{}", num);
                ++num;
            } else {
                break;
            }
            wait();
        }
    }
}
