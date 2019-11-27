package com.futao.basic.learn.lambda._1定义._2内建的函数式接口;

/**
 * @author futao
 * Created on 2019/11/26.
 */
public class _3原理_1表象 {

    public static void main(String[] args) {
        IMarkUp iMarkUp = (msg) -> {
            System.out.println(msg);
        };
        iMarkUp.markUp("123213");
    }

}

@FunctionalInterface
interface IMarkUp {
    void markUp(String msg);
}