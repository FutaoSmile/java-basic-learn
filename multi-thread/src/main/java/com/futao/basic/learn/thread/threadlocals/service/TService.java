package com.futao.basic.learn.thread.threadlocals.service;

import com.futao.basic.learn.thread.threadlocals.ThreadLocals;
import com.futao.basic.learn.thread.threadlocals.User;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author futao
 * @date 2020/4/10.
 */
@Slf4j
public class TService {

    public void s() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                ThreadLocals.threadLocalUser.set(User.builder().name(Thread.currentThread().getName()).id("123").build());
                User user = ThreadLocals.threadLocalUser.get();
                log.info(user.toString());
            });
        }
    }

    public static void main(String[] args) {
        new TService().s();
    }
}
