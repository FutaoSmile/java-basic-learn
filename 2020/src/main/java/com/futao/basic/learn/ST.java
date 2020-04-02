package com.futao.basic.learn;

/**
 * @author futao
 * @date 2020/3/31.
 */
public class ST {


    private ST() {

    }

    public static ST getInstance() {
        return HolderClass.INSTANCE;
    }

    private static class HolderClass {
        private static final ST INSTANCE = new ST();
    }

}
