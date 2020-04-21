package com.futao.basic.learn;

import java.lang.annotation.*;

/**
 * 自动覆写JSON
 *
 * @author futao
 * @date 2020/4/16.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface JsonString {

    /**
     * 是否格式化输出
     *
     * @return
     */
    boolean pretty() default false;
}
