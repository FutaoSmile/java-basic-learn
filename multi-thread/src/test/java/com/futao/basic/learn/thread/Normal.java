package com.futao.basic.learn.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019/11/20.
 */
@Slf4j
public class Normal {

    @Test
    public void test3() throws InterruptedException {
        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getName() + "--" + i);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getName() + "--" + i);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
        thread1.join();
        thread2.join();
    }

    @Test
    public void test1() {
        int num = 0;
        while (num < 10000) {
            if (num % 100 == 0) {
                log.info("{}", num);
            }
            num++;
            if (num == 1001) {
                try {
                    int i = 0 / 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() {
        int num = 0;
        try {
            while (num < 10000) {
                if (num % 100 == 0) {
                    log.info("{}", num);
                }
                num++;
                if (num == 1001) {
                    int i = 0 / 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
