package com.futao.spi;

import java.util.List;

/**
 * SPI机制
 *
 * @author futao
 * @since 2022/7/28
 */
public interface SpiInterface {
    String spiMethodOne();

    List<String> spiMethodTwo();
}
