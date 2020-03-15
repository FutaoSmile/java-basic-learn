package com.futao.basic.learn.design.pattern.proxy.service.impl;

import com.futao.basic.learn.design.pattern.proxy.service.UserService;

/**
 * @author futao
 * @date 2020/3/15.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void doSomeThing() {
        System.out.println("UserServiceImpl.doSomeThing...");
    }

    @Override
    public void hello() {
        System.out.println("hello");
    }
}