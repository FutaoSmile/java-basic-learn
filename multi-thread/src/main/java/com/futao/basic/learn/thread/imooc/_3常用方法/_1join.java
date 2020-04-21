package com.futao.basic.learn.thread.imooc._3常用方法;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * join()方法的使用
 * 在A线程中调用B.join()将阻塞线程A的执行，直到线程B执行完毕，A才继续执行
 *
 *
 * -----
 * 拔高：通过阅读源码可以发现，join()调用的是wait()。但是为什么没有看到notify()，线程却可以再次被唤醒?
 *
 * 因为在run()方法执行完毕之后，JVM会自动调用notifyAll()唤醒线程.
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


//--------------------------------------------------
//join()遇到中断
//--------------------------------------------------

@Slf4j
class _1_2Join implements Runnable {

    Thread mainThread = Thread.currentThread();

    @Override
    public void run() {

        //中断主线程
        mainThread.interrupt();
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("我执行完毕了");
    }

    public static void main(String[] args) {
        _1_2Join target = new _1_2Join();
        Thread thread = new Thread(target);
        thread.start();
        log.info("等待子线程运行完毕");
        try {
            thread.join();  //将子线程join()到主线程，等待子线程执行完毕      //但是如果此时主线程被中断，则join()的效果会失效，主线程会继续往下执行，所以我们应该把这个中断通知给子线程
        } catch (InterruptedException e) {
            log.info("我被中断了");
            thread.interrupt();
            e.printStackTrace();
        }
        log.info("子线程执行完毕");
    }
}

