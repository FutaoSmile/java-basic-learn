package com.futao.basic.learn;

/**
 * 打印ASCII码
 *
 * @author ft
 * @date 2021/1/18
 */
public class PrintAsc {
    public static void main(String[] args) {
        char aChar = 'a';
        int aCharInt = (int) aChar;
        System.out.println(aCharInt);

        String asz = "Asz";
        char[] chars = asz.toCharArray();
        for (char c : chars) {
            System.out.println((int) c);
        }
    }
}
