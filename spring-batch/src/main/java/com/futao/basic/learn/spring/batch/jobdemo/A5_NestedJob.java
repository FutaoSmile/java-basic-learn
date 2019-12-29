package com.futao.basic.learn.spring.batch.jobdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 任务的嵌套-由父任务来创建子任务
 *
 * @author futao
 * @date 2019/12/28.
 */
@Slf4j
@Configuration
public class A5_NestedJob {

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
     * 父任务
     *
     * @return
     */
    @Bean
    public Job parentJob(JobLauncher jobLauncher, JobRepository repository, PlatformTransactionManager transactionManager) {
        return jobBuilderFactory
                .get("父任务1")
                .start(task1(jobLauncher, repository, transactionManager))
                .next(task2(jobLauncher, repository, transactionManager))
                .build();
    }

//    @Bean
    public Step task1(JobLauncher jobLauncher, JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("jobStep-1"))
                .job(
                        jobBuilderFactory
                                .get("子任务1")
                                .start(stepBuilderFactory
                                        .get("步骤1-1")
                                        .tasklet(new Tasklet() {
                                            @Override
                                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                                log.info("step1-1执行...");
                                                return RepeatStatus.FINISHED;
                                            }
                                        }).build()
                                ).build()
                ).launcher(jobLauncher)
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }

//    @Bean
    public Step task2(JobLauncher jobLauncher, JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("jobStep-2"))
                .job(
                        jobBuilderFactory
                                .get("子任务2")
                                .start(stepBuilderFactory
                                        .get("步骤2-1")
                                        .tasklet(new Tasklet() {
                                            @Override
                                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                                log.info("step2-1执行...");
                                                return RepeatStatus.FINISHED;
                                            }
                                        }).build()
                                )
                                .next(stepBuilderFactory
                                        .get("步骤2-2")
                                        .tasklet(new Tasklet() {
                                            @Override
                                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                                log.info("step2-2执行...");
                                                return RepeatStatus.FINISHED;
                                            }
                                        }).build())
                                .build()
                ).launcher(jobLauncher)
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }
}
