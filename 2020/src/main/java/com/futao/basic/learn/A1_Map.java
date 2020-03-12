package com.futao.basic.learn;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author futao
 * @date 2020/1/6.
 */
public class A1_Map {
    public static void main(String[] args) {

        //同步方法
        //线程安全，但是实现线程安全的方式非常的简单粗暴，是在方法上加上synchronized关键字，锁住的是当前对象(即都在竞争同一把锁)，所以一个方法在执行时，其他对该对象的操作将全部阻塞。
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("1", 1);
        hashtable.get("1");

        //在1.7中ConcurrentHashMap使用了分段锁，1.8中则使用了CAS算法。
        //分段锁---容器里有多把锁，每一把锁用于锁容器其中一部分数据，那么当多线程访问容器里不同数据段的数据时，线程间就不会存在锁竞争
        //CAS Compare and Swap：比较并交换算法  (CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。 )
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", 1);


        //(数组 + 单向链表)最外层是数组，根据key的hash值决定将数据放到哪个索引位置下，如果发生了hash冲突，则存入链表    |---
        //                                                                                    |------
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("1", 1);

        //同步代码块
        Map<Object, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        //继承自HashMap，内部维护了一个双向链表，在put的时候记录。遍历的时候调用自己的迭代器
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1", 1);

        //红黑树
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("1", 1);
    }
}
