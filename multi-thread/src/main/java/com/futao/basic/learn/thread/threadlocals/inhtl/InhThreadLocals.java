package com.futao.basic.learn.thread.threadlocals.inhtl;

/**
 * @author futao
 * @date 2020/4/10.
 */
public class InhThreadLocals {
    public static InheritableThreadLocal<Integer> integerInheritableThreadLocal = new InheritableThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return super.initialValue();
        }
    };
}
