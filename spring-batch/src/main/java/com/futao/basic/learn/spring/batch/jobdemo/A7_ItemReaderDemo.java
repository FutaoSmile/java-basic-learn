package com.futao.basic.learn.spring.batch.jobdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * ItemReader简单Demo
 *
 * @author futao
 * @date 2019/12/29.
 */
@Slf4j
@Configuration
public class A7_ItemReaderDemo {


    /**
     * 创建Job所需
     */
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /**
     * 创建Step所需
     */
    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job jobDemo() {
        return jobBuilderFactory
                .get("A7_ItemReaderDemo.jobDemo-1")
                .start(step1())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory
                .get("A7_ItemReaderDemo.step1.01-1")
                .<String, String>chunk(2)   //一次读取的数量
                .reader(itemReader1())
                .writer(itemWrite())
                .build();
    }

    /**
     * 数据读取操作
     *
     * @return
     */
    private ItemReader<String> itemReader1() {
        List<String> strings = Arrays.asList("A", "B", "C", "D", "E");
        Iterator<String> iterator = strings.iterator();

        return new ItemReader<String>() {
            @Override
            public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                if (iterator.hasNext()) {
                    return iterator.next();
                }
                return null;
            }
        };
    }

    /**
     * 数据写操作
     *
     * @return
     */
    private ItemWriter<String> itemWrite() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                list.forEach(item -> {
                    log.info("itemWrite....{}", item);
                });
                log.info("-------------一次写入操作完成");
            }
        };
    }
}
