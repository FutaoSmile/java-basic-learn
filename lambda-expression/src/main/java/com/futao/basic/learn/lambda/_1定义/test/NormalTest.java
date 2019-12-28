package com.futao.basic.learn.lambda._1定义.test;

/**
 * @author futao
 * @date 2019/12/17.
 */
public class NormalTest {

    public static void main(String[] args) {
        NullPointerException nullPointerException = new NullPointerException();
        System.out.println(nullPointerException.getMessage());
        System.out.println(nullPointerException.getLocalizedMessage());
        System.out.println(nullPointerException.getClass().getName());

    }
}
