package com.itheima.jpa;

import com.itheima.dao.CustomerDao;
import com.itheima.dao.LinkManDao;
import com.itheima.dao.SysRoleDao;
import com.itheima.dao.SysUserDao;
import com.itheima.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class QueryTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;
    @Autowired
    private SysUserDao userDao;
    @Autowired
    private SysRoleDao roleDao;
    @Test
    public void oneToOneQuery() {
        Customer customer = customerDao.findOne(3l);
        CustomerExt customerExt = customer.getCustomerExt();
        System.out.println(customerExt);
        System.out.println(customerExt.getCustomer());


    }

    @Test
    @Transactional
    public void oneToManyQuery() {
        Customer customer = customerDao.findOne(3l);
        Set<LinkMan> linkManSet = customer.getLinkMans();
        linkManSet.stream().forEach(l-> System.out.println(l));
    }

    @Test
    @Transactional
    public void ManyToOneQuery() {
        LinkMan linkMan = linkManDao.findOne(7l);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);


    }

    @Test
    @Transactional
    public void ManyToManyQuery() {
        /*SysUser user = userDao.findOne(7l);
        user.getRoles().forEach(r-> System.out.println(r));*/
        SysRole role = roleDao.findOne(4l);
        role.getUsers().forEach(u-> System.out.println(u));
    }

    @Test
    public void testSpeicification() {
        customerDao.findAll((root, query, cb)->{
            //两个表关联
            Join<Object, Object> join = root.join("linkMans", JoinType.LEFT);
            //设置查询条件
            Predicate predicate = cb.equal(join.get("lkmName").as(String.class), "小米");
            return predicate;
        }).forEach(c-> System.out.println(c));
    }
}
