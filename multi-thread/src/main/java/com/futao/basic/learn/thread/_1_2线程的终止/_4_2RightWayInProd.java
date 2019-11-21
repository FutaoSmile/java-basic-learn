package com.futao.basic.learn.thread._1_2线程的终止;

import lombok.extern.slf4j.Slf4j;

/**
 * 生产环境使用-2
 * 恢复中断，在catch中调用Thread.currentThread().interrupt()来恢复中断
 * 以便在后续的执行中，能够检查到刚才发生了中断
 *
 * @author futao
 * Created on 2019/11/20.
 */
@Slf4j
public class _4_2RightWayInProd {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                log.info("go");
                try {
                    throwInMethod();
                } catch (InterruptedException e) {
                    //记录日志。。。
                    e.printStackTrace();
                    //与方法1不同的地方-重新中断线程
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(1000L);
        thread.interrupt();
    }

    /**
     * 传递中断
     *
     * @throws InterruptedException
     */
    private static void throwInMethod() throws InterruptedException {
        //不应该在方法中把异常给吞了，这样线程无法正确感知到中断信号，应该交给上层处理
        //或者在catch中重新调用interrupt()方法
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        //与方法1不同的地方-重新中断线程
//        Thread.currentThread().interrupt();
//        }

        Thread.sleep(3000L);
    }
}
