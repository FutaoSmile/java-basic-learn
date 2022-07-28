package com.futao.spi.consumer;

import com.futao.spi.SpiInterface;
import com.futao.spi.SpiService;

import java.util.ArrayList;

/**
 * @author futao
 * @since 2022/7/28
 */
public class Consumer {
    public static void main(String[] args) {
        SpiService service = new SpiService();
        SpiInterface spiInterface = service.getService();
        System.out.println(spiInterface.spiMethodOne());
        System.out.println(spiInterface.spiMethodTwo());
    }
}
