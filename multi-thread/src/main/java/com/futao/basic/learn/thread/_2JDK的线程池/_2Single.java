package com.futao.basic.learn.thread._2JDK的线程池;

import java.util.concurrent.Executors;

/**
 * 单个线程的线程池
 * 可以保证线程的执行顺序
 *
 * @author dark
 * Created on 2019/11/12.
 */
public class _2Single {
    public static void main(String[] args) {
        _0TestClass.test(Executors.newSingleThreadExecutor());
    }
}
