package com.futao.basic.learn.spring.batch.entity;

import lombok.*;

/**
 * @author futao
 * @date 2019/12/29.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private String id;
    private String userName;
    private String password;
    private int age;
}
