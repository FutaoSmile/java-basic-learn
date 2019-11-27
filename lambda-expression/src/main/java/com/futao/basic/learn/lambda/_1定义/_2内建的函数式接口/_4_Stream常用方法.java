package com.futao.basic.learn.lambda._1定义._2内建的函数式接口;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author futao
 * Created on 2019/11/26.
 */
public class _4_Stream常用方法 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("tom");
        list.add("jerry");
        list.add("duck");
        list.add("luck");
        list.add("nancy");

        list.stream()
                .peek(x -> System.out.println("p1----" + x))        //中间操作，注意运行结果，不是单独执行的
                .peek(x -> System.out.println("p2----" + x))
                .collect(Collectors.toList());


        IntStream.range(1, 10).forEach(System.out::println);

        //累加操作
        System.out.println(list.stream().reduce((sum, current) -> sum + current).get());
    }
}
