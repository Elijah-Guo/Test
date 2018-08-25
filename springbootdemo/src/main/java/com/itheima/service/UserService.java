package com.itheima.service;

import com.itheima.pojo.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    List<User> findAll();
}
