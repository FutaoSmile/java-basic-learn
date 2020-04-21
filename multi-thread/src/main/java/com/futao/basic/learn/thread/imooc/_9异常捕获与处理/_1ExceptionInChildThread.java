package com.futao.basic.learn.thread.imooc._9异常捕获与处理;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * （synchronized关键字锁住的代码如果发生了异常，锁会被自动释放）
 * <p>
 * UncaughtException能检测出线程由于未捕获异常而终止的情况
 *
 * @author futao
 * Created on 2019/11/22.
 */
@Slf4j
public class _1ExceptionInChildThread {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());    //使用这种方式设置主线程的异常处理器。异常处理器的调用策略是：不断递归查找父线程的处理器，如果存在，则调用父线程的异常处理器，所以这里设置main线程的异常处理器，当子线程发生异常后，子线程不断递归查找父线程的异常处理器，那么就将找到main线程的默认异常处理器

//        Thread.currentThread().setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());   //X不可以用这种写法

        Runnable runnable = () -> {
            int i = 0 / 0;
            log.info("是否还可以继续往下执行呢{}", StringUtils.repeat("=", 50));        //不会继续往下执行
        };
        //这样无法捕获到子线程的异常
        try {
            new Thread(runnable).start();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

    }

}
