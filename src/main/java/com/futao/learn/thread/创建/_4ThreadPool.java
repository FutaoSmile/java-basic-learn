package com.futao.learn.thread.创建;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池的方式
 *
 * @author futao
 * Created on 2019/11/12.
 */
@Slf4j
public class _4ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        //获取处理器数量
        int processors = Runtime.getRuntime().availableProcessors();
        //线程计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //实例化线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                processors,
                processors * 2,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1024),
                (r) -> new Thread(r, "tp-" + atomicInteger.getAndIncrement())
        );
        //预加载所有核心线程
        threadPool.prestartAllCoreThreads();
        //向线程池提交任务
        threadPool.execute(() -> {
            log.info("{}", "execute()方法执行任务start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{}", "execute()方法执行任务end");
        });


        Future<String> submitResult = threadPool.submit(() -> {
            log.info("{}", "submit()方法执行任务start");
            TimeUnit.SECONDS.sleep(2);
            log.info("{}", "submit()方法执行任务end");
            return "submit返回值";
        });
        log.info("{}", submitResult.get());
        threadPool.shutdown();

//        System.in.read();
    }
}
