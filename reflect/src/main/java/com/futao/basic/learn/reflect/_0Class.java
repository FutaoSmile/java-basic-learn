package com.futao.basic.learn.reflect;

import com.futao.basic.learn.reflect.other.Person;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class _0Class {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取描述类的类Class的信息
        log.info("{}", Person.class);
        log.info("{}", new Person().getClass());
        Class<?> aClass = Class.forName("com.futao.basic.learn.reflect.other.Person");
        log.info("{}", aClass);

        log.info("Person是否是原始类型{}", Person.class.isPrimitive());
        log.info("String是否是原始类型{}", String.class.isPrimitive());
        log.info("Integer是否是原始类型{}", Integer.class.isPrimitive());
        log.info("int是否是原始类型{}", int.class.isPrimitive());
        log.info("是否是数组{}", int[].class.isArray());

    }
}
