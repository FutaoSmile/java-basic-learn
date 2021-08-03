package com.futao.basic.learn.kaiser;


/**
 * 凯撒Kaiser加密
 * -往左往右位移
 *
 * @author ft
 * @date 2021/1/18
 */
public class KaiserDemo {
    public static void main(String[] args) {
        String s = "Kaiser jiami{}`";
        System.out.println("原文:" + s);
        int key = 3;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            chars[i] = (char) ((int) aChar + key);
        }
        System.out.println("密文:" + new String(chars));

        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((int) chars[i] - key);
        }
        System.out.println("解密之后:" + new String(chars));
    }
}
