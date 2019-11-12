package com.futao.learn.thread._2JDK的线程池;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dark
 * Created on 2019/11/12.
 */
@Slf4j
public class _3Schedule {
    public static void main(String[] args) throws IOException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleWithFixedDelay(() -> log.error("==="), 0, 1L, TimeUnit.SECONDS);       //提交循环固定延迟的任务
        scheduledExecutorService.schedule(() -> log.warn("--"), 1L, TimeUnit.SECONDS);                                      //提交延迟任务
        _0TestClass.test(scheduledExecutorService);                                                                             //execute()提交普通任务

        System.in.read();
    }
}
