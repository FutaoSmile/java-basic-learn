package com.futao.basic.learn.spring.batch.jobdemo;

import com.futao.basic.learn.spring.batch.entity.UserEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.List;

/**
 * 将数据通过jdbc写入数据库
 *
 * @author futao
 * @date 2019/12/30.
 */
@Configuration
public class B1_ItemWriteDemo {

    @Autowired
    private DataSource dataSource;

    /**
     * 简单的数据输出-打印到控制台
     *
     * @return
     */
    @Bean
    public ItemWriter<UserEntity> simpleItemWriter() {
        return new ItemWriter<UserEntity>() {
            @Override
            public void write(List<? extends UserEntity> items) throws Exception {
                for (UserEntity item : items) {
                    System.out.println("输出:" + item);
                }
                System.out.println("----------------");
            }
        };
    }


    /**
     * 将数据通过JdbcTemplate写入到数据库中
     *
     * @return
     */
    @Bean
    public JdbcBatchItemWriter<UserEntity> jdbcItemWriter() {
        JdbcBatchItemWriter<UserEntity> userEntityJdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        userEntityJdbcBatchItemWriter.setDataSource(dataSource);
        userEntityJdbcBatchItemWriter.setSql("INSERT INTO user(id,user_name,password,age) VALUES(:id,:userName,:password,:age)");
        userEntityJdbcBatchItemWriter.setItemSqlParameterSourceProvider(new ItemSqlParameterSourceProvider<UserEntity>() {
            @Override
            public SqlParameterSource createSqlParameterSource(UserEntity item) {
                MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
                mapSqlParameterSource.addValue("id", item.getId());
                mapSqlParameterSource.addValue("userName", item.getUserName());
                mapSqlParameterSource.addValue("password", item.getPassword());
                mapSqlParameterSource.addValue("age", item.getAge());
                return mapSqlParameterSource;
            }
        });
        return userEntityJdbcBatchItemWriter;
    }


}
