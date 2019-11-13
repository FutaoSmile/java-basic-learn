package com.futao.basic.learn.reflect;

import com.futao.basic.learn.reflect.other.Person;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 构造方法
 *
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class _1Constructor {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Person> personConstructor = Person.class.getConstructor(String.class, int.class);   //得到指定的构造方法
//        log.info("{}", personConstructor.newInstance());     //会报错哟，需要与获取的构造方法的参数列表一致
        Person person = personConstructor.newInstance("123", 18);       //通过构造方法来实例化对象
        log.info("{}", person);
        log.info("{}", personConstructor.getDeclaringClass());      //获取定义该构造方法的类

    }
}
