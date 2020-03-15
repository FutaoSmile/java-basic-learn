package com.futao.basic.learn.design.pattern.proxy;

import com.futao.basic.learn.design.pattern.proxy.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author futao
 * @date 2020/3/15.
 */
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib before ");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("cglib after");
        return result;
    }


    public static void main(String[] args) {
        UserServiceImpl userService = (UserServiceImpl) Enhancer.create(UserServiceImpl.class, new CglibProxy());
        userService.doSomeThing();
        userService.hello();
    }
}
