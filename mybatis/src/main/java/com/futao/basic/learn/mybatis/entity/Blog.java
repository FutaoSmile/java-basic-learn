package com.futao.basic.learn.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author futao
 * @date 2020/7/23
 */
@ToString
@Getter
@Setter
public class Blog {
    private int id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createDateTime;
}
