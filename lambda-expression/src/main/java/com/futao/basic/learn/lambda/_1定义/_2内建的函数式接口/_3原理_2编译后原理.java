package com.futao.basic.learn.lambda._1定义._2内建的函数式接口;

import java.util.stream.IntStream;

/**
 * @author futao
 * Created on 2019/11/26.
 */
public class _3原理_2编译后原理 {
    public static void main(String[] args) {

        //4.执行
        _3原理_2编译后原理$$lambda$1 原理_2编译后原理$$lambda$1 = new _3原理_2编译后原理$$lambda$1();
        原理_2编译后原理$$lambda$1.markUp("12312");

    }

    //1.编译后生成了一个静态方法
    private static void lambda$main$0(java.lang.String message) {
        System.out.println(message);
    }

    //2.编译后生成了一个内部类 并且实现了接口
    static final class _3原理_2编译后原理$$lambda$1 implements IMarkUp {
        @Override
        public void markUp(String msg) {
            //3.调用第一步新建的静态方法
            _3原理_2编译后原理.lambda$main$0(msg);
        }
    }
}
