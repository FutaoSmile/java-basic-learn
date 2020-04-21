package com.futao.basic.learn.thread.imooc._7线程状态;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 演示线程状态 - BLOCKED，WAITING，TIMED_WAITING
 * <p>
 * WAITING有可能直接到BLOCKED状态，比如线程wait()后被唤醒，可能还不能获取到同步监视器，那么此时线程就处于BLOCKED状态，直到抢到同步监视器之后再转换到RUNNABLE状态
 * 如果发生异常，可以直接从WAITING/TIMED_WAITING跳转到TERMINATED状态
 *
 * @author futao
 * Created on 2019/11/21.
 */
@Slf4j
public class _3_1BLOCKED_WATING_TIMED_WATING implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        _3_1BLOCKED_WATING_TIMED_WATING blocked_wating_timed_wating = new _3_1BLOCKED_WATING_TIMED_WATING();

        Thread thread1 = new Thread(blocked_wating_timed_wating);
        Thread thread2 = new Thread(blocked_wating_timed_wating);

        thread1.start();
        //阻塞主线程，使得线程1先获得同步监视器
        TimeUnit.MILLISECONDS.sleep(100L);
        thread2.start();

        TimeUnit.MILLISECONDS.sleep(100L);
        log.info("{}", thread2.getState());         //BLOCKED

        log.info("{}", thread1.getState());         //TIMED_WAITING

        TimeUnit.SECONDS.sleep(1L);

        log.info("{}", thread1.getState());         //WAITING

    }

    private synchronized void syncMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1L);
        wait();
    }

    @Override
    public void run() {
        try {
            syncMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
