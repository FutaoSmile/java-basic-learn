package com.futao.basic.learn.spring.batch.fail;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.jsr.RetryListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 失败重试
 *
 * @author futao
 * @date 2020/1/6.
 */
@Configuration
public class FailRetryDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job failRetryJobDemo() {
        return jobBuilderFactory
                .get("FailRetryDemo.failJobDemo.002")
                .start(failRetryStep())
                .next(failSkipStep())
                .build();
    }

    /**
     * 失败重试
     *
     * @return
     */
    private Step failRetryStep() {
        return stepBuilderFactory
                .get("FailRetryDemo.step1")
                .<String, String>chunk(10)
                .reader(new ListItemReader<>(Arrays.asList("12", "2", "3", "33", "56", "88", "123")))
                //容错
                .faultTolerant()
                //指定的异常发生时
                .retry(Exception.class)
                //重试次数
                .retryLimit(3)
                .writer(list -> list.forEach(item -> {
                    System.out.println("--:" + item);
                }))
                .build();
    }


    /**
     * 失败跳过与监听
     *
     * @return
     */
    private Step failSkipStep() {
        return stepBuilderFactory
                .get("FailRetryDemo.step1")
                .<String, String>chunk(10)
                .reader(new ListItemReader<>(Arrays.asList("12", "2", "3", "33", "56", "88", "123")))
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String item) throws Exception {
                        return item.toUpperCase();
                    }
                })
                //容错
                .faultTolerant()
                //指定的异常发生时
                .skip(Exception.class)
                //跳过次数
                .skipLimit(3)
                .listener(new SkipListener<String, String>() {
                    @Override
                    public void onSkipInRead(Throwable t) {
                        System.out.println("读取的时候..");
                    }

                    @Override
                    public void onSkipInWrite(String item, Throwable t) {
                        System.out.println("写的时候..");
                    }

                    @Override
                    public void onSkipInProcess(String item, Throwable t) {
                        System.out.println("process的时候..");
                    }
                })
                .writer(list -> list.forEach(item -> {
                    System.out.println("--:" + item);
                }))
                .build();
    }

}
