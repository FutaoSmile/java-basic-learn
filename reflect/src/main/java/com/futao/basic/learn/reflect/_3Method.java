package com.futao.basic.learn.reflect;

import com.futao.basic.learn.reflect.other.Person;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class _3Method {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Person> personClass = Person.class;

        Method[] methods = personClass.getMethods();        //返回当前类和父类的所有公有方法 only public
        for (Method method : methods) {
            log.info("{}", method.getName());
        }
        log.info("{}", "-----");
        Method[] declaredMethods = personClass.getDeclaredMethods();        //返回除了父类的所有方法，包括private,protected,public
        for (Method declaredMethod : declaredMethods) {
            log.info("{}", declaredMethod.getName());
        }


        Person person = new Person();
        person.setName("futao");
        person.setAge(18);
        log.info("反射调用方法{}", personClass.getDeclaredMethod("introduce").invoke(person));

        Method init = personClass.getDeclaredMethod("init");
        init.setAccessible(true);           //私有方法需要先设置可见性
        log.info("反射调用方法{}", init.invoke(person));

        //静态方法的反射调用
        Method m1 = personClass.getDeclaredMethod("m1");
        m1.setAccessible(true);
        log.info("反射调用静态方法{}", m1.invoke(null));        //反射调用静态方法，调用对象为null

        //通过反射调用main方法需要注意，main()方法虽然接收的是一个数组.class，但是JDK为了兼容以前的版本，会把数组中的每一项都当成一个参数，所以这个地方需要转成一个Object
        log.info("{}", _2Field.class.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{}));
    }
}
