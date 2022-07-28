package com.futao.spi.impl;

import com.futao.spi.SpiInterface;

import java.util.Arrays;
import java.util.List;

/**
 * @author futao
 * @since 2022/7/28
 */
public class SpiInterfaceImpl implements SpiInterface {

    @Override
    public String spiMethodOne() {
        return "默认实现" + this.getClass().getName();
    }

    @Override
    public List<String> spiMethodTwo() {
        return Arrays.asList("默认实现", this.getClass().getName());
    }
}
