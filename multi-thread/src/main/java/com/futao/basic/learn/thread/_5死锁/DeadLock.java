package com.futao.basic.learn.thread._5死锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 死锁示例
 * 死锁：不同的线程分别占用对方需要的资源不放弃，且都在等待对方放弃自己需要的资源
 *
 * @author futao
 * Created on 2019/11/14.
 */
@Slf4j
public class DeadLock {

    private static final Class<DeadLock> LOCK_1 = DeadLock.class;
    private static final Class<Object> LOCK_2 = Object.class;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (LOCK_1) {          //获取锁1
                log.info("获取到锁1");
                try {
                    TimeUnit.MILLISECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_2) {      //获取锁2，但是此时锁2已经被第二个线程持有，所以会一直处于等待状态
                    log.info("获取到锁2");
                }
            }

        }).start();


        //死锁不是一定发生，条件是需要第一个线程还在执行的过程中，第二个线程也在执行。
        //所以如果加上这行代码，等第一个线程执行完毕之后，把锁都释放了，再执行第二个线程，就不会发生死锁
        //TimeUnit.MILLISECONDS.sleep(100L);

        new Thread(() -> {
            synchronized (LOCK_2) {      //获取到锁2
                log.info("获取到锁2");
                try {
                    TimeUnit.MILLISECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_1) {      //获取锁1，但是因为线程1还处在运行状态，所以会一直等待线程1释放锁
                    log.info("获取到锁1");
                }
            }

        }).start();
    }
}
