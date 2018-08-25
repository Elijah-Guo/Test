package com.itheima.vue.dao;

import com.itheima.vue.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {

    @Select("select * from user where id=#{id}")
    User getUserById(int id);

    @Select("select * from user")
    List<User> getUserList();

    @Update("UPDATE USER\n" +
            "SET age = #{age},\n" +
            "username = #{username},\n" +
            "password = #{password},\n" +
            "email = #{email},\n" +
            "sex = #{sex}\n" +
            "WHERE\n" +
            "	id = #{id}")
    void updateUser(User user);
}
