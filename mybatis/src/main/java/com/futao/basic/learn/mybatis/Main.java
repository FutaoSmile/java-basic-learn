package com.futao.basic.learn.mybatis;

import com.futao.basic.learn.mybatis.entity.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

/**
 * @author futao
 * @date 2020/7/23
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        // 加载配置文件并创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 创建SqlSession对象
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            Blog parameter = new Blog();
            parameter.setTitle("标题");
            parameter.setDescription("描述");
            parameter.setContent("内容");
            parameter.setCreateDateTime(LocalDateTime.now(ZoneOffset.ofHours(8)));
            int insert = sqlSession.insert("com.futao.basic.learn.mybatis.dao.BlogMapper.insert", parameter);
            System.out.println("insert result:" + insert);

            sqlSession.commit();

            HashMap<String, Object> param = new HashMap<>();
            param.put("id", 10);
            Blog blog = sqlSession.<Blog>selectOne("com.futao.basic.learn.mybatis.dao.BlogMapper.byId", param);
            System.out.println(blog);
        }
    }
}
