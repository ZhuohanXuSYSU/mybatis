package com.zhuohxu.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * MyBatis 工具类
 * @author Zhuoh Xu
 * @version 1.0
 * @since 1.0
 */

public class SqlSessionUtils {

    // 每一个 environment 对应一个 sqlSessionFactory，所以没必要每一次创建
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SqlSessionUtils() {}

    // 获取会话对象
    public static SqlSession openSession() {
        if (sqlSessionFactory != null) {
            return sqlSessionFactory.openSession();
        } else {
            return null;
        }
    }

}
