package com.futao.basic.learn.thread._2JDK的线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creates a thread pool that creates new threads as needed, but
 * * will reuse previously constructed threads when they are
 * * available.  These pools will typically improve the performance
 * * of programs that execute many short-lived asynchronous tasks.
 * * Calls to {@code execute} will reuse previously constructed
 * * threads if available. If no existing thread is available, a new
 * * thread will be created and added to the pool. Threads that have
 * * not been used for sixty seconds are terminated and removed from
 * * the cache. Thus, a pool that remains idle for long enough will
 * * not consume any resources. Note that pools with similar
 * * properties but different details (for example, timeout parameters)
 * * may be created using {@link ThreadPoolExecutor} constructors.
 * <p>
 * 适合用来处理大大量的短任务，如果线程可用则直接使用线程池中的线程，如果没有可用线程则新建线程并加入到线程池缓存中，如果某个线程60秒之内没有任务则移除该线程
 *
 * @author futao
 * Created on 2019/11/13.
 */
public class _5Cache {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        _0TestClass.test(cachedThreadPool);
    }
}
