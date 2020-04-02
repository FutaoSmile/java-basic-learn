package com.futao.basic.learn.hutool;

import cn.hutool.core.convert.Convert;

/**
 * @author futao
 * @date 2020/3/26.
 */
public class Hutool {
    public static void main(String[] args) {
        Boolean aFalse = Convert.toBool("true");
        System.out.println(Convert.numberToChinese(123456789, false));
        System.out.println(Convert.digitToChinese(123456789.1234));
        System.out.println(aFalse);
    }

    protected void aaaaaa() {

    }
}
