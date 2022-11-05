package com.zhuohxu.mybatis.test;

import com.zhuohxu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CarMapperTest {

    @Test
    public void testInsertCar() {
        SqlSession sqlSession = null;

        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            sqlSession = sqlSessionFactory.openSession();

            int count = sqlSession.insert("insertCar");
            System.out.println(count + " 行受到影响");

            sqlSession.commit();

        } catch (IOException e) {
            // 出现异常，回滚事务
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsertCarByUtil() {
        SqlSession sqlSession = SqlSessionUtils.openSession();
        if (sqlSession != null) {
            sqlSession.insert("insertCar");
            sqlSession.commit();
            sqlSession.close();
        }
    }

}
