package com.futao.basic.learn.spring.batch.jobdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 监听器
 *
 * @author futao
 * @date 2019/12/29.
 */
@Slf4j
@Configuration
public class A6_Listener {


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
     * 任务监听器
     *
     * @return
     */
    public JobExecutionListener jobListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("beforeJob......");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                log.info("afterJob.......");
            }
        };
    }

    /**
     * Step监听器
     *
     * @return
     */
    public StepExecutionListener stepListener() {
        return new StepExecutionListener() {
            @Override
            public void beforeStep(StepExecution stepExecution) {
                log.info("beforeStep......");
            }

            @Override
            public ExitStatus afterStep(StepExecution stepExecution) {
                log.info("afterStep......");
                return ExitStatus.COMPLETED;
            }
        };
    }


    @Bean
    public Job demoJob1() {
        return jobBuilderFactory
                .get("demoJob-1")
                .start(
                        stepBuilderFactory.get("step-01")
                                .listener(stepListener())
                                .tasklet(new Tasklet() {
                                    @Override
                                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                        log.info("-----task01-01....");
                                        return RepeatStatus.FINISHED;
                                    }
                                })
                                .build()
                ).next(
                        stepBuilderFactory.get("step-01")
                                .listener(stepListener())
                                .tasklet(new Tasklet() {
                                    @Override
                                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                        log.info("-----task01-02....");
                                        return RepeatStatus.FINISHED;
                                    }
                                })
                                .build()
                )
                .listener(jobListener())
                .build();
    }
}
