package com.futao.basic.learn.reflect;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @author futao
 * Created on 2019/11/13.
 */
@Slf4j
public class _4Array {
    public static void main(String[] args) {
        log.info("{}", int[].class);
        log.info("{}", int[].class);
        log.info("{}", int[][].class);
        log.info("{}", String[].class);
        log.info("{}", String[][][][].class);
    }
}
