package com.futao.basic.learn.design.pattern.proxy;

import com.futao.basic.learn.design.pattern.proxy.service.UserService;
import com.futao.basic.learn.design.pattern.proxy.service.impl.UserServiceImpl;

/**
 * 静态代理，需要编写大量的代理对象
 *
 * @author futao
 * @date 2020/3/15.
 */
public class StaticProxy implements UserService {

    private UserService userService;

    public StaticProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doSomeThing() {
        System.out.println("before");
        userService.doSomeThing();
        System.out.println("after");
    }

    @Override
    public void hello() {

    }


    public static void main(String[] args) {
        new StaticProxy(new UserServiceImpl()).doSomeThing();
    }
}
