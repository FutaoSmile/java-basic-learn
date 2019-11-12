package com.futao.learn.thread.创建;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019/11/12.
 */
@Slf4j
public class _3ImplCallable implements Callable<String> {     //1.实现Callable<String>接口，指定泛型返回值类型
    @Override
    public String call() throws Exception {                 //2.重写call()方法
        log.info("线程启动...");
        TimeUnit.SECONDS.sleep(2L);
        return "ImplCallable方式创建的线程";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        _3ImplCallable implCallable = new _3ImplCallable();                     //3.创建多线程对象
        FutureTask<String> futureTask = new FutureTask<>(implCallable);     //4.创建FutureTask对象，将多线程对象作为target传入FutureTask
        Thread thread = new Thread(futureTask);                             //5.将FutureTask对象作为Target对象传入Thread（因为FutureTask实现了Runnable接口)
        thread.start();                                                     //6.启动线程
        //阻塞futureTask所在的线程，等待任务返回
        String result = futureTask.get();                                   //7.调用FutureTask对象的get()方法获取线程的返回值
        log.info("线程执行结果:{}", result);


        //lambda表达式的方式
        FutureTask<Integer> lambda表达式的方式 = new FutureTask<>(() -> {
            log.info("Lambda表达式的方式");
            return 678;
        });
        new Thread(lambda表达式的方式).start();
        log.info("lambda表达式的方式返回的值:{}", lambda表达式的方式.get());


        //匿名内部类的方式
        FutureTask<Long> 匿名内部类的方式 = new FutureTask<>(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                log.info("{}", "匿名内部类的方式");
                return 666L;
            }
        });
        new Thread(匿名内部类的方式).start();
        log.info("匿名内部类的方式返回的值:{}", 匿名内部类的方式.get());


    }
}
