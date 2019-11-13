package com.futao.basic.learn.reflect.other;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.util.stream.Stream;

/**
 * 对日志输出的内容进行JSON格式化
 *
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class LogBackToStringLayout extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        try {
            return MessageFormatter.arrayFormat(event.getMessage(), Stream.of(event.getArgumentArray()).map(JSON::toJSONString).toArray()).getMessage();
        } catch (Exception var3) {
            log.error("toString时发生异常", var3);
            return var3.getMessage();
        }
    }
}
