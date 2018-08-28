package com.itheima.test;

import com.itheima.dao.CustomerDao;
import com.itheima.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaFirst {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void findCustomer() {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        CustomerDao customerDao = applicationContext.getBean(CustomerDao.class);
        Customer customer = customerDao.findOne(2l);
        System.out.println(customer);
    }

    @Test
    public void addCustomer() {
        Customer customer = new Customer();
        customer.setCustAddress("北京");
        customer.setCustLevel("vip");
        customer.setCustName("传智播客3");
        customer.setCurstIndustry("培训");
        customer.setCustPhone("12345678");
        customer.setCustSource("互联网");
        customerDao.save(customer);
    }

    @Test
    public void deleteCustomer() {
        customerDao.delete(1l);
    }

    @Test
    public void updateCustomer() {
        //查询数据
        Customer customer = customerDao.findOne(2l);
        //修改数据
        customer.setCustName("黑马程序员");
        //更新到数据库
        customerDao.save(customer);
    }

    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(2l);
        System.out.println(customer);
    }

    @Test
    @Transactional
    public void testGetOne() {
        Customer customer = customerDao.getOne(2l);
        System.out.println(customer);
    }

    @Test
    public void testFindAll() {
        customerDao.findAll()
                .forEach(c-> System.out.println(c));
    }

    @Test
    public void testFindAllPageable() {
        //参数1：页码从0开始
        //参数2：每页的行数
        Pageable pageable = new PageRequest(1, 5);
        customerDao.findAll(pageable).forEach(c-> System.out.println(c));
    }

    @Test
    public void testCount() {
        long count = customerDao.count();
        System.out.println(count);
    }

    @Test
    public void testExists() {
        boolean exists = customerDao.exists(1l);
        System.out.println(exists);
    }

}
