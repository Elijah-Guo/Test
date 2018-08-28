package com.itheima.jpa;


import com.itheima.dao.SysRoleDao;
import com.itheima.dao.SysUserDao;
import com.itheima.entity.SysRole;
import com.itheima.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private SysUserDao userDao;
    @Autowired
    private SysRoleDao roleDao;

    @Test
    @Transactional
    @Commit
    public void testAddData() {
        //创建
        SysUser user = new SysUser();
        user.setUserName("zhangsan");
        user.setUserPassword("123");
        //创建一个角色
        SysRole role = new SysRole();
        role.setrName("学生");
        //配置关联关系
        user.getRoles().add(role);
        role.getUsers().add(user);
        //添加数据
        userDao.save(user);
        //roleDao.save(role);

    }

    @Test
    @Transactional
    @Commit
    public void deleteUser() {
        userDao.delete(6l);
    }
}
