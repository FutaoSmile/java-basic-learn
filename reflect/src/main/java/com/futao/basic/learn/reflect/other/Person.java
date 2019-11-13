package com.futao.basic.learn.reflect.other;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author futao
 * Created on 2019/11/13.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private int age;

    public String address;
    protected char a;

    public String introduce() {
        String x = "name = " + name + ",age=" + age;
        System.out.println(x);
        return x;
    }

    private void init() {
        System.out.println("private init....");
    }

    private static final String m1() {
        return "方法被调用";
    }
}
