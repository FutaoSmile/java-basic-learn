package com.futao.basic.learn.mybatis.dao;

import com.futao.basic.learn.mybatis.entity.Blog;

/**
 * @author futao
 * @date 2020/7/23
 */
public interface BlogMapper {
    Blog byId();

    int insert();
}
