package com.futao.basic.learn.lambda._1定义._2内建的函数式接口;

import java.util.UUID;
import java.util.function.*;

/**
 * @author futao
 * Created on 2019/11/22.
 */
public class _1使用与定义 {
    public static void main(String[] args) {
        //
//        Predicate<String> filterNull = Objects::isNull;
//        Predicate<String> filterMinLength = (String t) -> t.length() >= 3;

//        filterNull.and(filterMinLength).and(filterMinLength).or(filterMinLength).test(null);

        Consumer<String> consumer = (msg) -> System.out.println("接收到" + msg);
        consumer.accept("123");
        consumer.andThen((msg) -> System.out.println("---")).accept("12312");

        Function<String, Integer> function = (String t) -> {
            return 1;
        };
        function.apply("1231");
        function.apply("456");

        ToIntFunction<String> toIntFunction = (String t) -> {
            return 123;
        };
        toIntFunction.applyAsInt("2131");

        System.out.println(_1使用与定义.uuid.get());

        UnaryOperator<String> unaryOperator = (String t) -> {
            return t + "----";
        };
        System.out.println(unaryOperator.apply("12312"));

        IL1 il1 = (p1, p2) -> {
            String s = p1 + "";
            String s1 = p2 + "";
        };
        il1.m1("1231", 1231);

    }

    private static Supplier<String> uuid = () -> {
        return UUID.randomUUID().toString();
    };

    interface IL1 {
        void m1(String p1, Integer p2);
    }


}
