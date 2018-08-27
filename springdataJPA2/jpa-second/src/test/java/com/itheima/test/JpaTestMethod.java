package com.itheima.test;

import com.itheima.dao.CustomerDao2;
import com.itheima.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaTestMethod {

    @Autowired
    private CustomerDao2 customerDao2;
    @Test
    public void testGetById() {
        Customer customer = customerDao2.getCustomerByCustId(2l);
        System.out.println(customer);
    }

    @Test
    public void testgetAllByCustAddressAndCustName() {
        customerDao2.getAllByCustAddressAndCustName("北京", "黑马程序员")
                .forEach(c-> System.out.println(c));
    }

    @Test
    public void testfindAllByCustIdBetween() {
        customerDao2.findAllByCustIdBetween(1l, 10l).forEach(c-> System.out.println(c));
    }

    @Test
    public void testfindByCustIdIn() {
        long ids[] = {1,3,5,7,9};
        customerDao2.findByCustIdIn(ids)
                .forEach(c-> System.out.println(c));

    }

    @Test
    public void testfindByCustNameLike() {
        customerDao2.findByCustNameLike("%传智%", new PageRequest(1, 4))
                .forEach(c-> System.out.println(c));
    }
}
