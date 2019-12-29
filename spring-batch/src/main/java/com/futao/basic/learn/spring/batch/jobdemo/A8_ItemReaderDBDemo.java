package com.futao.basic.learn.spring.batch.jobdemo;

import com.futao.basic.learn.spring.batch.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 从数据库中读取数据
 *
 * @author futao
 * @date 2019/12/29.
 */
@Slf4j
@Configuration
public class A8_ItemReaderDBDemo {

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

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job ItemReaderDBJobDemo() {
        return jobBuilderFactory
                .get("A8_ItemReaderDBDemo.jobDemo.01-01")
                .start(step1())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory
                .get("A8_ItemReaderDBDemo.step1.01")
                .<UserEntity, UserEntity>chunk(2)
                .reader(userJdbcReader())
                .writer(dataList -> {
                    dataList.forEach(data -> {
                        log.info("输出处理数据,{}", data);
                    });
                    log.info("一个批次完成.....");
                })
                .build();
    }

    /**
     * 必须设置成Bean，StepScope，否则会报错Name....template为null
     *
     * @return
     */
    @Bean
    @StepScope
    public ItemReader<? extends UserEntity> userJdbcReader() {
        JdbcPagingItemReader<UserEntity> jdbcPagingItemReader = new JdbcPagingItemReader<>();
        jdbcPagingItemReader.setDataSource(dataSource);
        //一次取两条
        jdbcPagingItemReader.setFetchSize(2);
        //把读取到的数据转换成对象
        jdbcPagingItemReader.setRowMapper(new RowMapper<UserEntity>() {
            @Override
            public UserEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                return new UserEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                );
            }
        });
        //编写sql语句
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        //查询的字段
        queryProvider.setSelectClause("id,user_name,password,age");
        //查询的表
        queryProvider.setFromClause("from user");
        //查询的条件
        queryProvider.setWhereClause("1=1");
        //排序
        HashMap<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("age", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);

        //设置查询语句
        jdbcPagingItemReader.setQueryProvider(queryProvider);
        return jdbcPagingItemReader;
    }
}
