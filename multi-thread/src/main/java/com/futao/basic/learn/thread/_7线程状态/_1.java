package com.futao.basic.learn.thread._7线程状态;

/**
 * @author futao
 * Created on 2019/11/21.
 */
public class _1 {
    public static void main(String[] args) {
        for (Thread.State value : Thread.State.values()) {
            System.out.println(value.name() + ":" + value.toString() + ":" + value.ordinal());
        }
    }
}
