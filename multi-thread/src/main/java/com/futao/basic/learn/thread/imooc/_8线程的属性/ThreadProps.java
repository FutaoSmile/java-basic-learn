package com.futao.basic.learn.thread.imooc._8线程的属性;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author futao
 * Created on 2019/11/22.
 */
@Slf4j
public class ThreadProps extends Thread {

    @Override
    public void run() {
        log.info("---");
    }


    /**
     * 线程ID，从1开始，往后自增
     * 不可修改
     */
    @Test
    public void testThreadId() {
        log.info("{}", Thread.currentThread().getId());

        ThreadProps threadProps1 = new ThreadProps();
        log.info("{}", threadProps1.getId());

        ThreadProps threadProps2 = new ThreadProps();
        log.info("{}", threadProps2.getId());

        ThreadProps threadProps3 = new ThreadProps();
        log.info("{}", threadProps3.getId());
    }

    /**
     * 线程名称
     */
    @Test
    public void testThreadName() {
        log.info("{}", Thread.currentThread().getName());

        ThreadProps threadProps1 = new ThreadProps();
        log.info("{}", threadProps1.getName());

        ThreadProps threadProps2 = new ThreadProps();
        log.info("{}", threadProps2.getName());

        ThreadProps threadProps3 = new ThreadProps();
        log.info("{}", threadProps3.getName());
    }

    /**
     * 守护线程
     * <p>
     * 用户线程都结束时，JVM将结束，即使存在守护线程
     * 我们不应该人为的修改线程为守护线程，因为可能导致程序不正常终止，比如线程正在读取文件，IO等
     */
    @Test
    public void testThreadDaemon() {
        Thread mainThread = Thread.currentThread();
        log.info("{}", mainThread.isDaemon());
//        mainThread.setDaemon(true);     //只有在线程还未调用start()之前才可以设置是否为守护线程

        ThreadProps threadProps1 = new ThreadProps();
        log.info("{}", threadProps1.isDaemon());

        //子线程默认继承父线程的是否守护线程
        DaemonThread target = new DaemonThread();
        Thread thread = new Thread(target);
        thread.setDaemon(true);
        thread.start();
    }

    class DaemonThread implements Runnable {

        @Override
        public void run() {
            Thread thread = new Thread();
            log.info("{}", thread.isDaemon());
        }
    }

    /**
     * 线程优先级
     * 不应该手动修改线程的优先级，首先由于JAVA是Write Once，Run EveryWhere，虽然java中定义了10个优先级，但是映射到不同的操作系统不一定都是10个，所以不能依赖于优先级的设定
     * [不可靠]
     * <p>
     * <p>
     * 优先级[1,10]，默认是5
     */
    @Test
    public void testPriority() {
        log.info("{}", Thread.currentThread().getPriority());

        ThreadProps threadProps1 = new ThreadProps();
        threadProps1.setPriority(Thread.MAX_PRIORITY);
        log.info("{}", threadProps1.getPriority());

        ThreadProps threadProps2 = new ThreadProps();
        threadProps2.setPriority(Thread.MIN_PRIORITY);
        log.info("{}", threadProps2.getPriority());

        ThreadProps threadProps3 = new ThreadProps();
        threadProps3.setPriority(Thread.NORM_PRIORITY);
        log.info("{}", threadProps3.getPriority());

    }

}
