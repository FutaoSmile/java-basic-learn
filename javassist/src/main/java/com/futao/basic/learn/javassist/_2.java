package com.futao.basic.learn.javassist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * https://bugstack.cn/itstack-demo-agent/2020/04/20/字节码编程-Javassist篇二-定义属性以及创建方法时多种入参和出参类型的使用.html
 * <p>
 * 创建类，创建实例属性并赋初始值，创建方法，并包含入参和出参
 *
 * @author futao
 * @date 2020/4/21.
 */
public class _2 {
    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassPool pool = ClassPool.getDefault();

        CtClass ctClass = pool.makeClass("com.futao.basic.learn.H2C.javassist.generate.H2C");

        ctClass.addField(new CtField(CtClass.doubleType, "π", ctClass), "3.14D");

        CtMethod calculateCircularArea = new CtMethod(CtClass.doubleType, "calculateCircularArea", new CtClass[]{CtClass.intType}, ctClass);
        calculateCircularArea.setModifiers(Modifier.PUBLIC);
        //参数$0为this，$1为参数1，$2为参数2
        calculateCircularArea.setBody("{return π * $1 * $1;}");
        ctClass.addMethod(calculateCircularArea);

        CtMethod sumOfTwoNumbers = new CtMethod(CtClass.doubleType, "sumOfTwoNumbers", new CtClass[]{CtClass.doubleType, CtClass.doubleType}, ctClass);
        sumOfTwoNumbers.setModifiers(Modifier.PRIVATE);
        sumOfTwoNumbers.setBody("{return $1 + $2;}");
        ctClass.addMethod(sumOfTwoNumbers);

        ctClass.writeFile();

        Class aClass = ctClass.toClass();
        Object instance = aClass.newInstance();

        Method circularArea = aClass.getDeclaredMethod("calculateCircularArea", new Class[]{int.class});
        System.out.println(circularArea.invoke(instance, 10));

        Method twoNumbers = aClass.getDeclaredMethod("sumOfTwoNumbers", new Class[]{double.class, double.class});
        twoNumbers.setAccessible(true);
        System.out.println(twoNumbers.invoke(instance, 10D, 20D));


    }
}
