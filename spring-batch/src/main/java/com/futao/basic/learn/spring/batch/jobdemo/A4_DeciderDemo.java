package com.futao.basic.learn.spring.batch.jobdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 决策器的使用
 *
 * @author futao
 * @date 2019/12/28.
 */
@Slf4j
@Configuration
public class A4_DeciderDemo {

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


    /**
     * 定义决策器
     *
     * @return
     */
    @Bean
    public JobExecutionDecider myJobExecutionDecider() {
        return new JobExecutionDecider() {
            int i = 0;

            @Override
            public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
                ++i;
                if (i % 2 == 0) {
                    return new FlowExecutionStatus("偶数");
                } else {
                    return new FlowExecutionStatus("奇数");
                }
            }
        };
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get("步骤1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("步骤1正在执行...我只打印奇数");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory
                .get("步骤2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("步骤2正在执行...我只打印偶数");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Flow flow1() {
        return new FlowBuilder<Flow>("flow1")
                .start(step1())
                .build();
    }

    @Bean
    public Flow flow2() {
        return new FlowBuilder<Flow>("flow2")
                .start(step2())
                .build();
    }


    @Bean
    public Job deciderDemoJob() {
        return jobBuilderFactory
                .get("决策器任务1")
                .start(flow1())
                .from(myJobExecutionDecider()).on("奇数").to(flow1())
                .from(myJobExecutionDecider()).on("偶数").to(flow2())
                .from(flow1()).on("*").to(myJobExecutionDecider())
                .end()
                .build();
    }

}
