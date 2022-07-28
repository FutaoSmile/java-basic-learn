package com.futao.spi.consumer.impl;

import com.futao.spi.SpiInterface;

import java.util.Arrays;
import java.util.List;

/**
 * @author futao
 * @since 2022/7/28
 */
public class MySpiInterface implements SpiInterface {
    @Override
    public String spiMethodOne() {
        return "通过SPI机制替换:" + this.getClass().getName();
    }

    @Override
    public List<String> spiMethodTwo() {
        return Arrays.asList("通过SPI机制替换:" + this.getClass().getName());
    }
}
