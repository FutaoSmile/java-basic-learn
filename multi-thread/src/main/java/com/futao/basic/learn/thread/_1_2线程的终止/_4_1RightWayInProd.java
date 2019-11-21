package com.futao.basic.learn.thread._1_2线程的终止;

import lombok.extern.slf4j.Slf4j;

/**
 * 生产环境使用-1
 * 传递中断-将中断信息上报，而不是自己处理（生吞)
 *
 * @author futao
 * Created on 2019/11/20.
 */
@Slf4j
public class _4_1RightWayInProd {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                log.info("go");
                try {
                    throwInMethod();
                } catch (InterruptedException e) {
                    //记录日志。。。
                    e.printStackTrace();
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
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Thread.sleep(3000L);
    }

}
