package com.futao.basic.learn.javassist;

import java.lang.annotation.*;

/**
 * @author futao
 * @date 2020/5/6
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface _5_1 {

    String name();

    int age();
}
