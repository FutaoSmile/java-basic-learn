package com.futao.basic.learn.javassist.author;

import java.util.Random;

public class ApiTest {

    public String queryGirlfriendCount(String boyfriendName) {
        return boyfriendName + "的前女友数量：" + (new Random().nextInt(10) + 1L) + " 个";
    }

}
