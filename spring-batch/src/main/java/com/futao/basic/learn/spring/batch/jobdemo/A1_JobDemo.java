//package com.futao.basic.learn.spring.batch.job;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 一个Job由多个Step组成
// *
// * @author futao
// * @date 2019/12/28.
// */
//@Slf4j
//@EnableBatchProcessing
//@Configuration
//public class A1_JobDemo {
//
//    /**
//     * 创建Job所需
//     */
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    /**
//     * 创建Step所需
//     */
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    /**
//     * 创建Job
//     *
//     * @return
//     */
//    @Bean
//    public Job jobDemoJob() {
//        return jobBuilderFactory.get("[作业名称]-jobDemoJob")
////                .start(step1())     //需要执行的step
////                .next(step2())      //第二个step
////                .next(step3())      //第三个step
////                .build();
//
//
//                .start(step1())     //需要执行的step
//                .on("COMPLETED")   //条件
//                .to(step2())            //条件满足才执行step2
//                .from(step2()).on("COMPLETED").to(step3())
//                .from(step3()).end().build();
//
//    }
//
//    /**
//     * 定义Step-1
//     *
//     * @return
//     */
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory
//                .get("[作业步骤名称]-步骤-1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        log.info("[作业步骤名称]-步骤-1-task");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//
//    }
//
//    /**
//     * 定义Step-2
//     *
//     * @return
//     */
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory
//                .get("[作业步骤名称]-步骤-2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        log.info("[作业步骤名称]-步骤-2-task");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//
//    }
//
//    /**
//     * 定义Step-3
//     *
//     * @return
//     */
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory
//                .get("[作业步骤名称]-步骤-3")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        log.info("[作业步骤名称]-步骤-3-task");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//}
