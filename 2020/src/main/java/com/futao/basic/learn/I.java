package com.futao.basic.learn;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author futao
 * @date 2020/3/31.
 */
@Slf4j
public class I {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    log.info("{}", ST.getInstance());
                }
            });
        }
        executorService.shutdown();

        Class<ST> stClass = ST.class;
        Constructor<ST> constructor = stClass.getDeclaredConstructor();
        Constructor<?>[] constructors = stClass.getConstructors();


        constructor.setAccessible(true);
        ST st = constructor.newInstance();
        log.info("------{}", st);
    }

}
