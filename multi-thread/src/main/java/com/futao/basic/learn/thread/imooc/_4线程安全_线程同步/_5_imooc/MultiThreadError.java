package com.futao.basic.learn.thread.imooc._4线程安全_线程同步._5_imooc;

/**
 * @author futao
 * Created on 2019/11/27.
 */
public class MultiThreadError implements Runnable {
    private int index = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            ++index;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError multiThreadError = new MultiThreadError();
        Thread thread1 = new Thread(multiThreadError);
        Thread thread2 = new Thread(multiThreadError);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(multiThreadError.index);
    }

}
