package com.futao.basic.learn.thread.threadlocals;

/**
 * @author futao
 * @date 2020/4/10.
 */
public enum ThreadLocals {


    HOLDER;

    public static ThreadLocal<User> threadLocalUser = new ThreadLocal<User>(){
        @Override
        protected User initialValue() {
            return super.initialValue();
        }
    };


}
