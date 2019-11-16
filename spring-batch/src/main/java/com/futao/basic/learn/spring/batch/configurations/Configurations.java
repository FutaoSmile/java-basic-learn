package com.futao.basic.learn.spring.batch.configurations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author futao
 * Created on 2019/11/16.
 */
@Slf4j
@AllArgsConstructor
@Configuration
public class Configurations {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory
                .get("helloWorld")
                .start(helloWorldStepOne())
//                .next(helloWorldStepTwo())
                .build();
    }

    private Step helloWorldStepTwo() {
        return stepBuilderFactory.get("helloWorld").tasklet((stepContribution, chunkContext) -> {
            log.info("running...");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step helloWorldStepOne() {
        return stepBuilderFactory
                .get("helloWorld")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("running...");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
