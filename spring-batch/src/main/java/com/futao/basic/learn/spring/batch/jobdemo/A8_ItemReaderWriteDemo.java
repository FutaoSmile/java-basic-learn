package com.futao.basic.learn.spring.batch.jobdemo;

import com.futao.basic.learn.spring.batch.entity.TradeEntity;
import com.futao.basic.learn.spring.batch.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.validation.BindException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 从<1.数据库中><2.File文件只中><3.XML文件><4.读取多个文件>读取数据并处理
 * 写入数据
 *
 * @author futao
 * @date 2019/12/29.
 */
@Slf4j
@Configuration
public class A8_ItemReaderWriteDemo {

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

    @Qualifier("simpleItemWriter")
    @Autowired
    private ItemWriter<UserEntity> simpleItemWriter;

    @Qualifier("jdbcItemWriter")
    @Autowired
    private ItemWriter<UserEntity> jdbcItemWriter;

    @Qualifier("fileItemWriter")
    @Autowired
    private FlatFileItemWriter<UserEntity> fileItemWriter;


    @Qualifier("xmlItemWriter")
    @Autowired
    private StaxEventItemWriter<TradeEntity> xmlItemWriter;

    @Bean
    public Job ItemReaderDBJobDemo() {
        return jobBuilderFactory
                .get("A8_ItemReaderDBDemo.jobDemo.02-39")
                // .start(jdbcStep())
                // .next(fileStep())
                // .next(xmlStep())
                .start(multiFileStep())
                .build();
    }

    @Bean
    public Step jdbcStep() {
        return stepBuilderFactory
                .get("A8_ItemReaderDBDemo.jdbcStep.01")
                .<UserEntity, UserEntity>chunk(2)
                .reader(userJdbcReader())
                .writer(dataList -> {
                    dataList.forEach(data -> {
                        log.info("输出DB处理数据,{}", data);
                    });
                    log.info("一个BD批次完成.....");
                })
                .writer(simpleItemWriter)
                .build();
    }

    @Bean
    public Step fileStep() {
        return stepBuilderFactory
                .get("A8_ItemReaderDBDemo.fileStep.01")
                .<UserEntity, UserEntity>chunk(2)
                .reader(userFileReader())
                .writer(dataList -> {
                    dataList.forEach(data -> {
                        log.info("输出File处理数据,{}", data);
                    });
                    log.info("一个File批次完成.....");
                })
                .build();
    }

    @Bean
    public Step xmlStep() {
        return stepBuilderFactory
                .get("A8_ItemReaderDBDemo.xmlStep.01")
                .<TradeEntity, TradeEntity>chunk(2)
                .reader(userXmlReader())
//                .writer(dataList -> {
//                    dataList.forEach(data -> {
//                        log.info("输出XML处理数据{}", data);
//                    });
//                    log.info("一个XML批次完成.....");
//                })
                .writer(xmlItemWriter)
                .build();
    }


    @Bean
    public Step multiFileStep() {
        return stepBuilderFactory
                .get("A8_ItemReaderDBDemo.multiFileStep.01")
                .<UserEntity, UserEntity>chunk(2)
                .reader(multiFileItemReader())
                .writer(dataList -> {
                    dataList.forEach(data -> {
                        log.info("输出多File文件处理数据{}", data);
                    });
                    log.info("一个多文件File批次完成.....");
                })
//                .writer(jdbcItemWriter)
//                .writer(fileItemWriter)
                .build();
    }


    /**
     * 读取数据库的数据
     * 必须设置成Bean，StepScope，否则会报错Name....template为null
     *
     * @return
     */
    @Bean
    public ItemReader<UserEntity> userJdbcReader() {
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

    /**
     * 读取文件的数据
     *
     * @return
     */
    @Bean
    public FlatFileItemReader<UserEntity> userFileReader() {
        FlatFileItemReader<UserEntity> fileItemReader = new FlatFileItemReader<>();
        //设置资源路径
        fileItemReader.setResource(new ClassPathResource("customer.txt"));
        //跳过第一行
        fileItemReader.setLinesToSkip(1);

        //解析数据
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        //设置分隔符
        delimitedLineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
        delimitedLineTokenizer.setNames("id", "userName", "password", "age");

        //把解析出的一行映射成一个对象
        DefaultLineMapper<UserEntity> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(delimitedLineTokenizer);
        //字段映射
        mapper.setFieldSetMapper(new FieldSetMapper<UserEntity>() {
            @Override
            public UserEntity mapFieldSet(FieldSet fieldSet) throws BindException {
                return new UserEntity(
                        fieldSet.readString("id"),
                        fieldSet.readString("userName"),
                        fieldSet.readString("password"),
                        fieldSet.readInt("age")
                );
            }
        });
        mapper.afterPropertiesSet();
        fileItemReader.setLineMapper(mapper);
        return fileItemReader;
    }


    /**
     * 从XML中读取文件
     *
     * @return
     */
    @Bean
    public StaxEventItemReader<TradeEntity> userXmlReader() {
        StaxEventItemReader<TradeEntity> eventItemReader = new StaxEventItemReader<>();
        //要读取的资源路径--代表了需要读取的文件
        eventItemReader.setResource(new ClassPathResource("trade.xml"));
        //资源片段元素名称---片段根元素的名称就是要映射的对象。上面的示例代表的是 trade 的值
        eventItemReader.setFragmentRootElementName("trade");

        //Spring OXM提供的Unmarshalling 用于将 XML片段映射为对象
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        HashMap<String, Class<?>> map = new HashMap<>(1);
        map.put("trade", TradeEntity.class);
        unmarshaller.setAliases(map);
        eventItemReader.setUnmarshaller(unmarshaller);
        return eventItemReader;
    }


    /**
     * 从多个文件中读取数据
     *
     * @return
     */
    @Bean
    public MultiResourceItemReader<UserEntity> multiFileItemReader() {
        MultiResourceItemReader<UserEntity> multiResourceItemReader = new MultiResourceItemReader<>();
        //资源路径
        multiResourceItemReader.setResources(new Resource[]{new ClassPathResource("customer_multi_1.txt"), new ClassPathResource("customer_multi_2.txt"), new ClassPathResource("customer_multi_3.txt")});
        //设置解析代理(该代理中的资源路径会被自动设置)
        multiResourceItemReader.setDelegate(userFileReader());
        return multiResourceItemReader;
    }

}
