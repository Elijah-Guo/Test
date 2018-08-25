package com.itheima.mapper;

import com.itheima.pojo.User;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;


public interface UserMapper {

    User findById(int id);

    List<User> findAll();
}
