package com.zhuohxu.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisIntroductionTest {
    public static void main(String[] args) throws IOException {

        // 1 获取 SqlSessionFactoryBuilder 对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 2 获取 SqlSessionFactory 对象，
        // 首先创建 mybatis 核心配置文件的输入流对象，可以自己创建，也可以通过 mybatis 提供的工具创建
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml"); // 默认情况下从类的根目录下找
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        // 3 获取 SqlSession 对象， mybatis 床架的 SqlSession 对象默认情况下不支持自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int count = sqlSession.insert("insertCar"); // 根据 sql 语句的 id 执行 sql

        System.out.println("插入 " + count + " 条记录");

        sqlSession.commit(); // 手动提交事务
    }
}
