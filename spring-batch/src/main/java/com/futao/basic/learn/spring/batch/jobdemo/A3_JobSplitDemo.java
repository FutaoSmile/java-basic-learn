package com.futao.basic.learn.spring.batch.jobdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * 并发执行任务
 *
 * @author futao
 * @date 2019/12/28.
 */
@EnableBatchProcessing
@Slf4j
@Configuration
public class A3_JobSplitDemo {
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
     * 定义Step-1
     *
     * @return
     */
    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get("[作业步骤名称]-步骤-1-1")
                .tasklet((contribution, chunkContext) -> {
                    log.debug("[作业步骤名称]-步骤-1-task-1");
                    Thread.sleep(3000L);
                    log.debug("step1 end");
                    return RepeatStatus.FINISHED;
                }).build();

    }

    /**
     * 定义Step-2
     *
     * @return
     */
    @Bean
    public Step step2() {
        return stepBuilderFactory
                .get("[作业步骤名称]-步骤-2-1")
                .tasklet((contribution, chunkContext) -> {
                    log.debug("[作业步骤名称]-步骤-2-task-1");
                    Thread.sleep(2000L);
                    log.debug("step2 end");
                    return RepeatStatus.FINISHED;
                }).build();

    }

    /**
     * 定义Step-3
     *
     * @return
     */
    @Bean
    public Step step3() {
        return stepBuilderFactory
                .get("[作业步骤名称]-步骤-3-1")
                .tasklet((contribution, chunkContext) -> {
                    log.debug("[作业步骤名称]-步骤-3-task-1");
                    Thread.sleep(1000L);
                    log.debug("step3 end");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Flow workFlow1() {
        return new FlowBuilder<Flow>("工作流1")
                .start(step1())
                .next(step2())
                .build();

    }

    @Bean
    public Flow workFlow2() {
        return new FlowBuilder<Flow>("工作流2")
                .start(step2())
                .next(step3())
                .build();

    }

    /**
     * 多线程处理flow，每个flow都是一个线程
     *
     * @return
     */
    @Bean
    public Job jobSplitDemoJob() {
        return jobBuilderFactory
                .get("[并发处理任务]")
                .start(workFlow1())
                .split(new SimpleAsyncTaskExecutor())
                .add(workFlow2())
                .end()
                .build();

    }


}
