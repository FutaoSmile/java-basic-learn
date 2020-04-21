package com.futao.basic.learn.javassist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * https://bugstack.cn/itstack-demo-agent/2020/04/19/字节码编程-Javassist篇一-基于javassist的第一个案例helloworld.html
 * 创建类，给类添加方法，并调用这个方法。
 *
 * @author futao
 * @date 2020/4/21.
 */
public class _1 {
    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ClassPool classPool = ClassPool.getDefault();

        //class
        CtClass helloJavassistClass = classPool.makeClass("com.futao.basic.learn.javassist.generate.HelloJavassist");

        //method
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "main", new CtClass[]{classPool.get(String[].class.getName())}, helloJavassistClass);
        ctMethod.setModifiers(Modifier.PUBLIC + Modifier.STATIC);
        ctMethod.setBody("{System.out.println(\"你好...Javassist.\");}");


        //add method to class
        helloJavassistClass.addMethod(ctMethod);

        //输出类内容
        helloJavassistClass.writeFile();

        Class aClass = helloJavassistClass.toClass();
        Method mainMethod = aClass.getDeclaredMethod("main", new Class[]{String[].class});
        mainMethod.invoke(aClass.newInstance(), (Object) new String[]{});


    }
}
