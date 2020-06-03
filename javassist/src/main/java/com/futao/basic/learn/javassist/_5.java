package com.futao.basic.learn.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * @author futao
 * @date 2020/5/6
 */
@_5_1(name = "王老五", age = 55)
public class _5 {
    public static void main(String[] args) throws NotFoundException, ClassNotFoundException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.getCtClass(_5.class.getName());
        _5_1 annotation = (_5_1) ctClass.getAnnotation(_5_1.class);
        System.out.println(annotation.name());
        System.out.println(annotation.age());
    }


    @_5_2(name = "麦子1", age = 11)
    public void m1() {

    }
}
