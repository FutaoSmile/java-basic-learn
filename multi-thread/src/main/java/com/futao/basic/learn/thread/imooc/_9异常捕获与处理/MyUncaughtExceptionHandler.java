package com.futao.basic.learn.thread.imooc._9异常捕获与处理;

import lombok.extern.slf4j.Slf4j;

/**
 * @author futao
 * Created on 2019/11/22.
 */
@Slf4j
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("【未处理的异常捕获】ID为[{}]，NAME为[{}]的线程发生了异常，异常信息为", t.getId(), t.getName(), e);
    }
}
