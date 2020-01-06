package com.futao.basic.learn.spring.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 手动启动任务
 *
 * @author futao
 * @date 2020/1/6.
 */
@Slf4j
@Component
public class JobLauncherDemo implements ApplicationRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Timer("SpringBatch任务调度")
                .scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            jobLauncher.run(
                                    jobBuilderFactory
                                            .get("JobLauncherDemo.001")
                                            .start(
                                                    stepBuilderFactory
                                                            .get("JobLauncherDemo.jobLauncherDemo.001" + System.currentTimeMillis())
                                                            .tasklet(new Tasklet() {
                                                                @Override
                                                                public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                                                    log.info("执行成功.....");
                                                                    return RepeatStatus.FINISHED;
                                                                }
                                                            }).build()
                                            ).build(), new JobParameters()
                            );
                        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                            e.printStackTrace();
                        }
                    }
                }, 0L, 500L);
    }

}
