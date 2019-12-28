package com.futao.basic.learn.spring.batch.flow;

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

/**
 * FlowDemo
 * 一个Flow是由多个step构成
 *
 * @author futao
 * @date 2019/12/28.
 */
@Slf4j
@EnableBatchProcessing
@Configuration
public class FlowDemo {

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
                    log.info("[作业步骤名称]-步骤-1-task-1");
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
                    log.info("[作业步骤名称]-步骤-2-task-1");
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
                    log.info("[作业步骤名称]-步骤-3-task-1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    /**
     * 创建一个flow对象
     * 指明这个flow包含哪些flow
     *
     * @return
     */
    @Bean
    public Flow flowDemoFlow1() {
        return new FlowBuilder<Flow>("flowDemoFlow-1-1")
                //定义这个flow包含哪些step
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    /**
     * 创建一个flow对象
     * 指明这个flow包含哪些flow
     *
     * @return
     */
    @Bean
    public Flow flowDemoFlow2() {
        return new FlowBuilder<Flow>("flowDemoFlow-2-1")
                //定义这个flow包含哪些step
                .start(step2())
                .next(step3())
                .build();
    }


    @Bean
    public Job flowDemoJob() {
        return jobBuilderFactory
                .get("[作业名称]-flowDemoJob-2-1")
                .start(flowDemoFlow1())
                .on("COMPLETED")
                .to(flowDemoFlow2())
                .end()
                .build();
    }
}
