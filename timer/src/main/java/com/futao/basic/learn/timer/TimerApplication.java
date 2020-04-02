package com.futao.basic.learn.timer;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有且仅有一个线程在后台对多个线程进行定时定频率的调度
 *
 * @author futao
 * @date 2020/3/15.
 */
@Slf4j
public class TimerApplication {
    public static void main(String[] args) {
        Timer timer = new Timer();
        MyTimerTask timerTask = new MyTimerTask("任务1");
        timer.schedule(timerTask, 2000L, 1000L);

        AtomicInteger atomicInteger = new AtomicInteger(1);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(100, (x) -> {
            Thread thread = new Thread(x);
            thread.setName(atomicInteger.getAndIncrement() + "-t");
            return thread;
        });
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> log.info("---"), 200L, 500L, TimeUnit.MILLISECONDS);
    }

}
