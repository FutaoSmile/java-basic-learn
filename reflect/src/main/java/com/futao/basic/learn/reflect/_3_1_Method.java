package com.futao.basic.learn.reflect;

/**
 * @author futao
 * @date 2020/6/3
 */
public class _3_1_Method {
    public static void main(String[] args) {
        m1(123, "adasdsa按时的", 'a');
    }

    public static <T> void m1(T... p) {
        for (T t : p) {
            if (t instanceof String) {
                System.out.println("string:" + t);
            }
            if (t instanceof Integer) {
                System.out.println("int:" + t);
            }
            if (t instanceof Character) {
                System.out.println("char:" + t);
            }
        }
    }
}
