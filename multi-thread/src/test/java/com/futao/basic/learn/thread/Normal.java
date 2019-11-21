package com.futao.basic.learn.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author futao
 * Created on 2019/11/20.
 */
@Slf4j
public class Normal {

    @Test
    public void test1() {
        int num = 0;
        while (num < 10000) {
            if (num % 100 == 0) {
                log.info("{}", num);
            }
            num++;
            if (num == 1001) {
                try {
                    int i = 0 / 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() {
        int num = 0;
        try {
            while (num < 10000) {
                if (num % 100 == 0) {
                    log.info("{}", num);
                }
                num++;
                if (num == 1001) {
                    int i = 0 / 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
