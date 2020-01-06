package com.futao.basic.learn.spring.batch;

import com.alibaba.druid.pool.DruidDataSource;
import com.futao.basic.learn.spring.batch.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Closeable;
import java.io.IOException;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;

/**
 * @author futao Created on 2019/11/15.
 */
@Slf4j
@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchApplication implements Closeable {
    public static void main(String[] args) throws IOException {
        System.out.println(UserEntity.class.getPackage().getImplementationVersion());
        System.out.println(DruidDataSource.class.getPackage().getImplementationVersion());
        Thread thread = new Thread(() -> {
            System.out.println("做一些操作");
            log.info("------------------");
            // 需要配置META-INF/services/java.io.Closeable
            ServiceLoader<Closeable> closeables = ServiceLoader.load(Closeable.class);
            for (Closeable closeable : closeables) {
                log.info("{}", closeable);
            }
            log.info("------------------");
            // Reflections reflections = new Reflections();
            // Set<Class<? extends Closeable>> subTypesOf = reflections.getSubTypesOf(Closeable.class);
            // for (Class<? extends Closeable> aClass : subTypesOf) {
            //     log.info("{}", aClass);
            // }
            // Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Configuration.class);
            // log.info("------------------");
            // for (Class<?> aClass : typesAnnotatedWith) {
            //     log.info("{}", aClass);
            // }
        });
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(thread);
        runtime.exec("pwd");
        SpringApplication.run(SpringBatchApplication.class);
        new Thread(() -> {
            try {
                TimeUnit.DAYS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void close() throws IOException {
        log.info("system closing...");
        log.info("system closed");
    }
}
