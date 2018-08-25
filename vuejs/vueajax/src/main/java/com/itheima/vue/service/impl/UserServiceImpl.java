package com.itheima.vue.service.impl;

import com.itheima.vue.dao.UserDao;
import com.itheima.vue.pojo.User;
import com.itheima.vue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int id) {
        User user = userDao.getUserById(id);
        return user;
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}
