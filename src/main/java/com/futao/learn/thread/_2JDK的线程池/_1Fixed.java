package com.futao.learn.thread._2JDK的线程池;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dark
 * Created on 2019/11/12.
 */
@Slf4j
public class _1Fixed {
    private static final int THREAD_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(THREAD_COUNT);
//        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(
                    () -> {
                        for (int i = 0; i < 10; i++) {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            log.info("当前线程为:{},当前I为:{}", Thread.currentThread().getName(), i);
                        }
//                        countDownLatch.countDown();
                    }
            );
        }
//        countDownLatch.await();


        _0TestClass.test(fixedThreadPool);

        fixedThreadPool.shutdown();



        log.info("主线程");
    }
}
