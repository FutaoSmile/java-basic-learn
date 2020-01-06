package com.futao.basic.learn.spring.batch.jobdemo;

import com.futao.basic.learn.spring.batch.entity.TradeEntity;
import com.futao.basic.learn.spring.batch.entity.UserEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import javax.sql.DataSource;
import java.util.HashMap;
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


    /**
     * todo 不知道为什么，在step中加入了这个方法，step就不执行了
     * 将数据输出到普通文件
     *
     * @return
     */
    @Bean
    public FlatFileItemWriter<UserEntity> fileItemWriter() throws Exception {
        FlatFileItemWriter<UserEntity> fileItemWriter = new FlatFileItemWriter<>();
        fileItemWriter.setResource(new ClassPathResource("processorFileOutPut2.txt"));

        //Obj.toString()
//        PassThroughLineAggregator<UserEntity> lineAggregator = new PassThroughLineAggregator<>();

        //自定义
//        LineAggregator<UserEntity> lineAggregator = new LineAggregator<UserEntity>() {
//            @Override
//            public String aggregate(UserEntity item) {
//                return JSON.toJSONString(item);
//            }
//        };


        //分隔符文件(Delimited File)写入示例
        DelimitedLineAggregator<UserEntity> lineAggregator = new DelimitedLineAggregator<>();
        //分隔符
        lineAggregator.setDelimiter(",");
        BeanWrapperFieldExtractor<UserEntity> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "userName", "password", "age"});
        lineAggregator.setFieldExtractor(fieldExtractor);


        //固定宽度的(Fixed Width)文件写入示例
//        FormatterLineAggregator<UserEntity> lineAggregator = new FormatterLineAggregator<>();
//        BeanWrapperFieldExtractor<UserEntity> fieldExtractor = new BeanWrapperFieldExtractor<>();
//        fieldExtractor.setNames(new String[]{"id", "userName", "password", "age"});
//        //Formatter
//        lineAggregator.setFormat("s%s%s%s%");
//        lineAggregator.setFieldExtractor(fieldExtractor);


        fileItemWriter.setLineAggregator(lineAggregator);
        fileItemWriter.afterPropertiesSet();
        return fileItemWriter;
    }

//    @Bean
    public StaxEventItemWriter<TradeEntity> xmlItemWriter() {
        StaxEventItemWriter<TradeEntity> userEntityStaxEventItemWriter = new StaxEventItemWriter<>();
        userEntityStaxEventItemWriter.setResource(new ClassPathResource("xmlOutput.xml"));
        userEntityStaxEventItemWriter.setRootTagName("rootTagName");
        userEntityStaxEventItemWriter.setVersion("v1.0");

        XStreamMarshaller marshaller = new XStreamMarshaller();
        HashMap<String, Class<?>> map = new HashMap<>(1);
        map.put("trade", TradeEntity.class);
        marshaller.setAliases(map);
        userEntityStaxEventItemWriter.setMarshaller(marshaller);

        return userEntityStaxEventItemWriter;
    }




}
