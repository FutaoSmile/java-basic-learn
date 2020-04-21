package com.futao.basic.learn.eventbus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author futao
 * @date 2020/4/16.
 */
@Getter
@Setter
@AllArgsConstructor
public class EventMessage {
    private String msg;
    private String data;
}
