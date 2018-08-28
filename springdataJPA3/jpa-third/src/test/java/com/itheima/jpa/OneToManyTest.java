package com.itheima.jpa;


import com.itheima.dao.CustomerDao;
import com.itheima.dao.CustomerExtDao;
import com.itheima.dao.LinkManDao;
import com.itheima.entity.Customer;
import com.itheima.entity.CustomerExt;
import com.itheima.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerExtDao customerExtDao;
    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Commit
    public void testAddData() {
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        customer.setCustLevel("vip");
        customer.setCustSource("电话营销");
        customer.setCustAddress("北京");
        customer.setCurstIndustry("教育");

        CustomerExt customerExt = new CustomerExt();
        customerExt.setExt("扩展信息3333333333333");

        //联系人信息
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小米");
        linkMan.setLkmMobile("13788889999");
        linkMan.setLkmEmail("123@itcast.cn");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("小米2");
        linkMan2.setLkmMobile("13788889999");
        linkMan2.setLkmEmail("123@itcast.cn");
        //设置关联关系
        customer.getLinkMans().add(linkMan);
        customer.getLinkMans().add(linkMan2);
        linkMan.setCustomer(customer);
        linkMan2.setCustomer(customer);

        //设置对象的对应关系
        //customer.setCustomerExt(customerExt);
        customerExt.setCustomer(customer);

        //linkManDao.save(linkMan);
        customerDao.save(customer);
        customerExtDao.save(customerExt);
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteCustomer() {
        customerDao.delete(2l);
    }
}
