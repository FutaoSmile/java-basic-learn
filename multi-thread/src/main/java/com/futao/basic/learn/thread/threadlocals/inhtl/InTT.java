package com.futao.basic.learn.thread.threadlocals.inhtl;

import lombok.extern.slf4j.Slf4j;

/**
 * InheritableThreadLocal子线程可以继承父线程的值，但是测试下来没有实现效果...
 *
 * @author futao
 * @date 2020/4/10.
 */
@Slf4j
public class InTT {
    public static void main(String[] args) throws InterruptedException {
        boolean run = false;

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (InTT.class) {
                    log.info("{}", InhThreadLocals.integerInheritableThreadLocal.get());
                    InTT.class.notify();
                    try {
                        InTT.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            synchronized (InTT.class) {
                if (!run) {
                    thread.start();
                    run = true;
                }
                InhThreadLocals.integerInheritableThreadLocal.set(i + 10000);
                log.info("{}", InhThreadLocals.integerInheritableThreadLocal.get());
                InTT.class.notify();
                InTT.class.wait();
            }
        }


    }
}
