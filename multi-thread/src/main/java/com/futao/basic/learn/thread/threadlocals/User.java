package com.futao.basic.learn.thread.threadlocals;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author futao
 * @date 2020/4/10.
 */
@Getter
@Setter
@Builder
public class User {
    private String id;
    private String name;
    private String address;
    private int age;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
