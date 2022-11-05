package com.zhuohxu.mybatis.test;

import com.zhuohxu.mybatis.pojo.Car;
import com.zhuohxu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMapperTest {

    @Test
    public void testInsertCar() {

        // CarMapper 中的 sql 语句
        // insert into t_car(id, car_num, brand, guide_price, produce_time, car_type) values(null, #{carNum}, #{brand}, #{guidePrice}, #{produceTime}, #{carType})

        // 使用 Map 可以给 sql 语句的占位符传值
        // map 的 key 与 sql 语句的 #{} 对应，名字要求见名知意
        Map car = new HashMap();
        car.put("carNum", "1111");
        car.put("brand", "比亚迪唐");
        car.put("guidePrice", 10.1);
        car.put("produceTime", "2020-11-09");
        car.put("carType", "新能源");

        SqlSession sqlSession = SqlSessionUtils.openSession();
        int count = sqlSession.insert("insertCar", car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByPOJO() {

        Car car = new Car(null, "3333", "比亚迪秦", 55.5, "2024-07-20", "新能源");

        SqlSession sqlSession = SqlSessionUtils.openSession();

        /*
        insert into t_car(id, car_num, brand, guide_price, produce_time, car_type)
            values(#{id}, #{carNum}, #{brand}, #{guidePrice}, #{produceTime}, #{carType})
        sql 语句中的 #{} 写 pojo 类中的对应 get 方法去掉 get 并将剩下的字符串首字母小写
         */
        sqlSession.insert("insertCar", car);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = SqlSessionUtils.openSession();
        /**
         * delete from t_car where id = #{id} 由于只有一个参数传入，这种情况下{}内填什么都行，但最好见名知意
         */
        sqlSession.delete("deleteById", 15); // delete接受的是Object对象，故 15 会被自动装箱为包装类
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = SqlSessionUtils.openSession();

        Car car = new Car(9L, "9999", "凯美瑞", 30.3, "1999-11-12", "燃油车");

        sqlSession.update("updateById", car);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectById() {

        SqlSession sqlSession = SqlSessionUtils.openSession();

        Object car = sqlSession.selectOne("selectById", 1);
        System.out.println(car);

        sqlSession.close();

    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtils.openSession();

        List<Car> list = sqlSession.selectList("jkjkjk.selectAll");

        list.forEach(car -> System.out.println(car));

        sqlSession.close();
    }



}
