package com.itheima.service;

import com.itheima.pojo.User;

import java.util.List;

public interface UserMapperService {

    User findById(int id);

    List<User> findAll();
}
