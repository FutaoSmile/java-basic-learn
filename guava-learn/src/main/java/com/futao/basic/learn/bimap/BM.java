package com.futao.basic.learn.bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author futao
 * @date 2020/4/16.
 */
public class BM {
    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        HashBiMap<String, Integer> hashBiMap = HashBiMap.create();
        hashBiMap.put("k1", 1);
        hashBiMap.put("k2", 2);
        hashBiMap.put("k3", 3);
        hashBiMap.put("k4", 4);
        hashBiMap.put("k4", 5);
        hashBiMap.put("k5", 5);
        System.out.println(hashBiMap.get("k4"));
        BiMap<Integer, String> inverse = hashBiMap.inverse();
        System.out.println(inverse.get(5));
    }
}
