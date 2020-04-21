package com.futao.basic.learn.thread.imooc._3常用方法;

import lombok.extern.slf4j.Slf4j;

/**
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class _0Other {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            log.info("{}", "--");
            Thread.yield();     //*向调度程序提示当前线程愿意放弃当前对处理器的使用。所有线程(包括当前线程)继续争抢cpu的执行权
        });
        //设置线程优先级，高优先级的线程只是概率上比较优先
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        log.info("isAlive:{}", thread.isAlive());
        thread.setName("设置线程名称");
        log.info("getId:{}", thread.getId());
        //通过thread.setDaemon(boolean on)可以将线程设置为用户线程或者守护线程，但是必须在线程启动之前调用，当线程正在运行时调用会产生IllegalThreadStateException异常
        //守护线程依赖于用户线程,用户线程结束，守护线程都结束。即当JVM中的线程都是守护线程时，JVM将退出。
        log.info("isDaemon是否是守护线程：{}", thread.isDaemon());
        log.info("toString：{}", thread.toString());
    }
}
