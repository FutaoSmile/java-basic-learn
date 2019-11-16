package com.futao.basic.learn.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author futao
 * Created on 2019/11/15.
 */
@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class);
    }
}
