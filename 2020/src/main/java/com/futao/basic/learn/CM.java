package com.futao.basic.learn;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author futao
 * @date 2020/4/14.
 */
public class CM {
    public static void main(String[] args) {
        ArrayList<KV> kvs = new ArrayList<>();
        kvs.add(new KV("a", "b1"));
        kvs.add(new KV("b", "b2"));
        kvs.add(new KV("c", "b3"));
        kvs.add(new KV("a", "b3"));

        Map<String, String> collect = kvs.stream().collect(Collectors.toMap(KV::getK, KV::getV, (x, y) -> y));
        System.out.println(collect);
    }


    @Getter
    @AllArgsConstructor
    public static class KV {
        String k, v;
    }
}
