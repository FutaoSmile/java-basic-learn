package com.futao.basic.learn.thread.Java多线程编程核心技术;


import com.alibaba.fastjson.JSONObject;

/**
 * @author futao
 * @date 2020/4/7.
 */
public class SOUT {

//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            for (int i = 0; i < 9999 + 100; i++) {
//                System.out.println(i);
//                if (i == 9999) {
//                    try {
//                        Thread.currentThread().suspend();
//                        Thread.sleep(2000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        thread.start();
//        Thread.sleep(1000L);
//        System.out.println("man end....");
////        thread.suspend();
////        thread.resume();
//    }

    public static void main(String[] args) {
        Object o="{\"k\":\"v\"}";
        String o1 = (String) o;
        System.out.println(o1.toString());
    }
}
