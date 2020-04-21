package com.futao.basic.learn.design.pattern.proxy;

/**
 * @author futao
 * @date 2020/4/17.
 */
public class B extends A {

    @Override
    public void a() {
        new B().a();
    }
}
