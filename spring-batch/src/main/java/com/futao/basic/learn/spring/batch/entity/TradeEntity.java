package com.futao.basic.learn.spring.batch.entity;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author futao
 * @date 2019/12/30.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeEntity {
    private String isin;
    private String quantity;
    private BigDecimal price;
    private String customer;
}
