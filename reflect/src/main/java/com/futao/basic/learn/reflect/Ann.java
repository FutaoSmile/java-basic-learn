package com.futao.basic.learn.reflect;

import java.lang.annotation.*;

/**
 * @author futao
 * @date 2020/5/7
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Ann {
}
