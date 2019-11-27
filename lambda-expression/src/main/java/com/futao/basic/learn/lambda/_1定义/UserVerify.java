package com.futao.basic.learn.lambda._1定义;

/**
 * 函数式接口
 *
 * @author futao
 * Created on 2019/11/22.
 */
@FunctionalInterface
public interface UserVerify {
    /**
     * 验证用户名密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 是否通过
     */
    boolean verify(String userName, String password);


    //------------------------------------------------------

    /**
     * 函数式接口中可以包含default方法
     */
    default void test() {

    }

    /**
     * 函数式接口中也可以包含静态方法
     */
    static void test2() {

    }
    //------------------------------------------------------

}
