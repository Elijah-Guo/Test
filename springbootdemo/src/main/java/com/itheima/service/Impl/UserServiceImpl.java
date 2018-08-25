package com.itheima.service.Impl;

import com.itheima.dao.UserDao;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据id查询
     * Optional为1.8新增 可以实现返回值的非空判断或者指定如果为null显示其他
     * 常用方法为:orElse() 其他对象
     *           orElseget()  其他实现
     *           orElsethrow() 其他异常
     *           .get()有值正常执行
     * @param id
     * @return
     */
    @Override
    public User findById(int id) {
        Optional<User> optional = userDao.findById(id);
        return optional.get();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
