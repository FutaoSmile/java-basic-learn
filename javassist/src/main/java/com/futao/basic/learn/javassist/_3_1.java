package com.futao.basic.learn.javassist;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author futao
 * @date 2020/4/22
 */
@Slf4j
public class _3_1 {

    public int printContent() {
        Random random = new Random();
        return random.nextInt(10);
    }

    public void threadPrint() {
        Random random = new Random();
//        new Thread(() -> log.info("{}", random.nextInt(10))).start();
        System.out.println("---");
    }

    public static void main(String[] args) {
        String a = "a";
        final String c = "a";
        String b = a + "b";
        String d = c + "b";
        String e = getA() + "b";
        String compare = "ab";
        System.out.println(b == compare);
        System.out.println(d == compare);
        System.out.println(e == compare);
    }

    private static String getA() {
        return "a";
    }


}
