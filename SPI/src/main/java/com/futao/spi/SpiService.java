package com.futao.spi;

import com.futao.spi.impl.SpiInterfaceImpl;

import java.util.*;

/**
 * @author futao
 * @since 2022/7/28
 */
public class SpiService {
    private static final List<SpiInterface> SPI_INTERFACES = new ArrayList<>();
    private final SpiInterface spiInterface;


    public SpiService() {
        ServiceLoader<SpiInterface> spiInterfaces = ServiceLoader.load(SpiInterface.class);
        for (SpiInterface spiInterface : spiInterfaces) {
            System.out.println("detect impl:" + spiInterface.getClass().getName());
            SPI_INTERFACES.add(spiInterface);
        }
        spiInterface = SPI_INTERFACES.isEmpty() ? new SpiInterfaceImpl() : SPI_INTERFACES.get(0);
    }

    public SpiInterface getService() {
        return spiInterface;
    }
}
