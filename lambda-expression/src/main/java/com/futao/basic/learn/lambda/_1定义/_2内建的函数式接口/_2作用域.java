package com.futao.basic.learn.lambda._1定义._2内建的函数式接口;

/**
 * @author futao
 * Created on 2019/11/26.
 */
public class _2作用域 {

    private String s1 = "全局变量";

    public void testInnerClass() {
        String s2 = "局部变量";
        new Thread(new Runnable() {
            String s3 = "内部类变量";

            @Override
            public void run() {
                String s4 = "内部类局部变量";
//                System.out.println(this.s1);  //无法访问，当前this是匿名内部类
                System.out.println(s1);
                s1 = s1 + "--";                 //可以修改
                System.out.println(s1);

//                System.out.println(this.s2);    //无法访问，this是当前匿名内部类
//                s2 = s2 + "";       //无法修改，默认推导变量的修饰符：final
                System.out.println(s2);
                System.out.println(this.s3);
                System.out.println(s3);
//                System.out.println(this.s4);    //无法通过对象访问方法内的变量
                System.out.println(s4);
            }
        }).start();
    }

    public void testLambda() {
        String s2 = "局部变量";
        new Thread(() -> {
            System.out.println(this.s1);            //可以直接访问，lambda表达式中没有内部类作用域
        }).start();
    }


    public static void main(String[] args) {
        _2作用域 z = new _2作用域();
//        z.testInnerClass();
        System.out.println("----------------------");
        z.testLambda();
    }
}
