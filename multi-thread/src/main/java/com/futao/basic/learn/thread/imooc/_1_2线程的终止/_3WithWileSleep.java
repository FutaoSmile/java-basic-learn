package com.futao.basic.learn.thread.imooc._1_2线程的终止;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * while循环过程中中断线程
 *
 * @author futao
 * Created on 2019/11/20.
 */
@Slf4j
public class _3WithWileSleep {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    log.info("{}", num);
                }
                ++num;
                try {
                    //重点：中断标记会被sleep()重置--->下一次进入while循环的时候判断isInterrupted()还是为false(interrupt()会被sleep()响应后吃掉)
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }

    /**
     * 这个场景下线程会结束，因为sleep()会响应interrupt()方法，并抛出异常，而抛出异常后代码就跳出了while循环，进入了catch代码块，当catch
     * 代码块执行完毕之后，整个线程体就执行完毕了
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        log.info("{}", num);
                    }
                    ++num;
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
