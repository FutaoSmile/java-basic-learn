package com.futao.learn.thread._2JDK的线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 测试工具类
 *
 * @author dark
 * Created on 2019/11/12.
 */
@Slf4j
public class _0TestClass {

    public static ExecutorService test(ExecutorService executor) {
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                for (int j = 1; j <= 10; j++) {
                    log.info("当前线程是:{},当前I:J为:{}:{}", Thread.currentThread().getName(), finalI, j);
                }
            });
        }
        return executor;
    }
}
