package com.futao.basic.learn.thread._1_2线程的终止;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 使用interrupt来建议线程终止
 *
 * @author futao
 * Created on 2019/11/20.
 */
@Slf4j
public class _1RightWay {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            //响应线程中断，判断如果当前线程被设置成了中断则结束while循环，即结束了run()方法，线程被终止(只能这样显示地处理线程中断，因为线程何时应该被终止，只有创建者才知道)
            while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE) {
                if (num % 10000 == 0) {
                    log.info("[{}]能被10000整除", num);
                }
                ++num;
            }
        });
        thread.start();

        //让主线程先阻塞500毫秒，即等待500毫秒再执行下面的终止线程方法
        TimeUnit.MILLISECONDS.sleep(500);
        //终止线程
        thread.interrupt();
    }
}
