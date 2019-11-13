package com.futao.basic.learn.reflect;

import com.futao.basic.learn.reflect.other.Person;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class _2Field {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person("futao", 18, "上海", 'L');
        //所有定义的字段
        Field[] declaredFields = Person.class.getDeclaredFields();
        //所有可见的字段(public)
        Field[] fields = Person.class.getFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);  //暴力反射，设置可见性为为可见
            log.info("{}：{}", field.getName(), field.get(person));     //获取字段名称 //取出这个字段在某个对象上的值
        }

        //通过反射修改对象字段的值
        log.info("改变String类型字段之前：{}", person);
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                field.set(person, "我是帅哥");
            }
        }
        log.info("通过反射改变String类型字段之后：{}", person);
    }

}
