package com.itheima.jpa;

import com.itheima.dao.CustomerDao;
import com.itheima.dao.CustomerExtDao;
import com.itheima.entity.Customer;
import com.itheima.entity.CustomerExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OneToOneTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerExtDao customerExtDao;

    @Test
    @Transactional
    @Commit
    public void addData() {
        Customer customer = new Customer();
        customer.setCustName("李四");
        customer.setCustLevel("vip");
        customer.setCustSource("电话营销");
        customer.setCustAddress("北京");
        customer.setCurstIndustry("教育");

        CustomerExt customerExt = new CustomerExt();
        customerExt.setExt("扩展信息22222222");

        //设置对象的对应关系
        //customer.setCustomerExt(customerExt);
        customerExt.setCustomer(customer);

        customerDao.save(customer);
        customerExtDao.save(customerExt);

    }

}
