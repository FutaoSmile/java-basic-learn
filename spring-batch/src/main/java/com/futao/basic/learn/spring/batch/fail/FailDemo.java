package com.futao.basic.learn.spring.batch.fail;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * SpringBatch对于错误场景的处理：一个job中的两个Step，第一个step第一次执行时发生异常，结束，第二次执行成功，则Step1结束。Step2同上。
 *
 * @author futao
 * @date 2020/1/6.
 */
@Configuration
public class FailDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job failJobDemo() {
        return jobBuilderFactory
                .get("FailDemo.failJobDemo.001")
                .start(step1())
                .next(step2())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory
                .get("FailDemo.step1")
                .tasklet(taskLet())
                .build();
    }

    private Step step2() {
        return stepBuilderFactory
                .get("FailDemo.step2")
                .tasklet(taskLet())
                .build();
    }


    public Tasklet taskLet() {
        return (contribution, chunkContext) -> {
            Map<String, Object> stepExecutionContext = chunkContext.getStepContext().getStepExecutionContext();
            if (!stepExecutionContext.containsKey("contextParam")) {
                chunkContext.getStepContext().getStepExecution().getExecutionContext().put("contextParam", 123);
                throw new RuntimeException("第一次运行失败...");
            } else {
                System.out.println("第二次运行成功");
                return RepeatStatus.FINISHED;
            }
        };
    }

}
