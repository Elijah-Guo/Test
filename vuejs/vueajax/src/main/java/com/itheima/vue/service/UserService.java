package com.itheima.vue.service;

import com.itheima.vue.pojo.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    List<User> getUserList();
    void updateUser(User user);
}
