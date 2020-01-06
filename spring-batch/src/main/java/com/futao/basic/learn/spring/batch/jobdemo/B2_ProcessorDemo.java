package com.futao.basic.learn.spring.batch.jobdemo;

import com.futao.basic.learn.spring.batch.entity.UserEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author futao
 * @date 2020/01/06
 */
@Configuration
public class B2_ProcessorDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Qualifier("userJdbcReader")
    @Autowired
    private ItemReader<UserEntity> userJdbcReader;

    @Qualifier("fileItemWriter")
    @Autowired
    private FlatFileItemWriter<UserEntity> fileItemWriter;

    @Bean
    public Job processJobDemo() {
        return jobBuilderFactory
                .get("B2_ProcessorDemo.processJobDemo.003")
                .start(step1())
                .build();

    }

    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get(this.getClass().getSimpleName() + "step1")
                .<UserEntity, UserEntity>chunk(3)
                .reader(userJdbcReader)
                .processor(toUpperCaseItemProcessor())
//                .processor(ageItemProcessor())
                .writer(fileItemWriter)
                .build();
    }

    /**
     * 将名字转为大写的处理器
     *
     * @return processor
     */
    public ItemProcessor<? super UserEntity, ? extends UserEntity> toUpperCaseItemProcessor() {
        return new ItemProcessor<UserEntity, UserEntity>() {

            @Override
            public UserEntity process(UserEntity item) throws Exception {
                String userName = item.getUserName();
                userName.toUpperCase();
                item.setUserName(userName);
                return item;
            }

        };
    }

    /**
     * 只保留年龄为奇数的数据processor
     *
     * @return
     */
    public ItemProcessor<UserEntity, UserEntity> ageItemProcessor() {
        return new ItemProcessor<UserEntity, UserEntity>() {

            @Override
            public UserEntity process(UserEntity item) throws Exception {
                int age = item.getAge();
                return age % 2 == 0 ? null : item;
            }
        };
    }

}