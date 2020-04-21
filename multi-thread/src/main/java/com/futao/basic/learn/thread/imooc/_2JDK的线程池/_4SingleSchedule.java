package com.futao.basic.learn.thread.imooc._2JDK的线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 单个线程的定时任务线程池
 * ****
 * Tasks are guaranteed to execute sequentially, and no more than one task will be active at any given time
 * 任务保证顺序执行，并且在给定的时间仅会有一个任务处在运行状态
 * ****
 *
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class _4SingleSchedule {

    public static void main(String[] args) {
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        //直接通过execute()提交的任务按照普通任务（非定时任务）执行
        _0TestClass.test(singleThreadScheduledExecutor);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() -> {
            log.info("{}", "---");
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }, 0, 1L, TimeUnit.SECONDS);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() -> log.info("{}", "==="), 0, 1L, TimeUnit.SECONDS);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() -> log.info("{}", "+++"), 0, 1L, TimeUnit.SECONDS);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() -> log.info("{}", "~~~"), 0, 1L, TimeUnit.SECONDS);
    }

}
