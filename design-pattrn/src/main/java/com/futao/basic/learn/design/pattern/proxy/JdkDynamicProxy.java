package com.futao.basic.learn.design.pattern.proxy;

import com.futao.basic.learn.design.pattern.proxy.service.UserService;
import com.futao.basic.learn.design.pattern.proxy.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理，需要代理对象实现接口
 *
 * @author futao
 * @date 2020/3/15.
 */
public class JdkDynamicProxy implements InvocationHandler {

    private UserServiceImpl userService;

    public JdkDynamicProxy(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("before");
            result = method.invoke(userService, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            System.out.println("after");
        }
        return result;
    }


    public static void main(String[] args) {
        //保留生成的代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //(通过打断点，可以发现，代理类是在运行时动态生成的代理对象).走完下面的方法才会生成代理类
        UserService userService = (UserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserService.class}, new JdkDynamicProxy(new UserServiceImpl()));
        userService.doSomeThing();
        userService.hello();
    }
}
