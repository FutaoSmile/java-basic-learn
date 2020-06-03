package com.futao.basic.learn.loader;

/**
 * @author futao
 * @date 2020/5/12
 */
public class Loader extends ClassLoader {
    public static void main(String[] args) {
        ClassLoader classLoader = Loader.class.getClassLoader();
        System.out.println(classLoader.toString());
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());

        System.out.println(int.class.getClassLoader());
        System.out.println(String.class.getClassLoader());

        System.out.println(getSystemClassLoader());
    }
}
