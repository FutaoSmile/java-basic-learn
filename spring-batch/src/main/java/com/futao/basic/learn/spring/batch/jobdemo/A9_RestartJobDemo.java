package com.futao.basic.learn.spring.batch.jobdemo;

import com.futao.basic.learn.spring.batch.entity.UserEntity;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 任务的失败重试
 * 流程为:
 * Step执行前。先执行open()方法，再处理完一个批次chunk条数据之后，如果没发生异常则update()，将处理结果持久化到DB，最后Step执行完毕之后调用close()方法
 * 失败重启：读取DB中上次读取的位置(chunk x 读取批次)，再开始读取下一个批次（不是在失败的那一行重新开始，而是在是失败的那个批次重新开始)
 *
 * @author futao
 * @date 2019/12/30.
 */
@Component
public class A9_RestartJobDemo {


    @Autowired
    private FlatFileItemReader<UserEntity> userFileReader;

    public ItemStreamReader<UserEntity> restartReader() {
        return new ItemStreamReader<UserEntity>() {

            /**
             * Step执行之前执行
             *
             * @param executionContext
             * @throws ItemStreamException
             */
            @Override
            public void open(ExecutionContext executionContext) throws ItemStreamException {

            }


            /**
             * 一个chunk批次执行完毕之后，如果执行成功，才执行update，将执行结果存到数据库
             *
             * @param executionContext
             * @throws ItemStreamException
             */
            @Override
            public void update(ExecutionContext executionContext) throws ItemStreamException {

            }

            /**
             * 整个Step执行完毕才执行close
             *
             * @throws ItemStreamException
             */
            @Override
            public void close() throws ItemStreamException {

            }

            @Override
            public UserEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                //可以从JdbcItemReader/FileItemReader/XmlItemRead/MultiItemReader中读取数据
                UserEntity entity = userFileReader.read();
                return null;
            }
        };
    }
}
