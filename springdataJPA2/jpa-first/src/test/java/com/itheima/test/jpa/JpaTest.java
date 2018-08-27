package com.itheima.test.jpa;

import com.itheima.jap.entity.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    @Test
    public void addCustomer() throws Exception {
        //1、创建一个EntityManagerFactory对象，作用就是创建EntityManager 对象。单例。
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("jpa318");
        //2、创建EntityManager对象，一个数据库连接。多例，使用完毕之后关闭。
        EntityManager entityManager = managerFactory.createEntityManager();
        //3、创建一个事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //4、开启事务
        transaction.begin();
        //5、创建一个Customer对象，并设置属性
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        customer.setCurstIndustry("教育培训");
        customer.setCustAddress("北京昌平");
        customer.setCustLevel("vip");
        customer.setCustPhone("88888888");
        //6、使用EntityManager将Customer对象添加到数据库中。
        entityManager.persist(customer);
        //7、提交事务
        transaction.commit();
        //8、关闭资源
        entityManager.close();
        managerFactory.close();
    }
}
