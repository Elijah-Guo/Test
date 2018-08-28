package com.itheima.test;

import com.itheima.dao.CustomerDao;
import com.itheima.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JPQLTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSelectAllCustomer() {
        customerDao.selectAllCustomer().forEach(c-> System.out.println(c));
    }

    @Test
    public void testgetCustomerById() {
        Customer customer = customerDao.getCustomerById(2l);
        System.out.println(customer);
    }

    @Test
    public void testgetCustomerByNameAddress() {
        customerDao.getCustomerByNameAddress("黑马程序员", "北京")
                .forEach(c-> System.out.println(c));

    }
    @Test
    public void testgetCustomerByNameAddressPage() {
        customerDao.getCustomerByNameAddressPage("黑马程序员", "北京", new PageRequest(0, 3))
                .forEach(c-> System.out.println(c));

    }

    @Test
    @Transactional
    @Commit
    public void updateAddressById() {
        customerDao.updateAddressById("上海" , 3l);
    }

    @Test
    public void testgetCustomerList() {
        customerDao.getCustomerList().forEach(c-> System.out.println(c));
    }

}
