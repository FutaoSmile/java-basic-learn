package com.futao.basic.learn.reflect;

import java.lang.annotation.Target;

/**
 * @author futao
 * @date 2020/5/7
 */
@Ann
public class _5Anno {
    @Ann
    public static void a() {
        Class<_5Anno> annoClass = _5Anno.class;
        Ann annotation = annoClass.getAnnotation(Ann.class);
        Target target = annoClass.getAnnotation(Target.class);
        Target declaredAnnotation = annoClass.getDeclaredAnnotation(Target.class);


        System.out.println(annotation);
        System.out.println(target);
        System.out.println(declaredAnnotation);

        Target annotation1 = annotation.getClass().getDeclaredAnnotation(Target.class);
        System.out.println("--" + annotation1);

        Target annotation2 = annotation.annotationType().getDeclaredAnnotation(Target.class);
        System.out.println("--" + annotation2);
    }

    public static void main(String[] args) {
        a();
    }
}
