package com.futao.basic.learn.lambda._1定义;

/**
 * @author futao
 * Created on 2019/11/22.
 */
public class UseLambda {
    public static void main(String[] args) {
        UserVerify userVerify = (userName, password) -> {
            return true;
        };
        System.out.println(userVerify.verify("123", "123"));
    }
}
