package com.itheima.test.jpa;

import com.itheima.jap.entity.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaCRUD {

    @Test
    public void addCustomer() throws Exception {
        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa318");
        EntityManager entityManager = entityManagerFactory.createEntityManager();*/
        EntityManager entityManager = EntityUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //创建一个Customer对象
        Customer customer = new Customer();
        customer.setCustName("小米科技");
        customer.setCustAddress("北京昌平");
        customer.setCustLevel("普通");
        //写入数据库
        entityManager.persist(customer);
        //提交事务
        transaction.commit();
        //关闭资源
        entityManager.close();
    }

    @Test
    public void deleteCustomer() throws Exception {
        //获得EntityManager对象
        EntityManager entityManager = EntityUtil.getEntityManager();
        //开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询对象
        Customer customer = entityManager.find(Customer.class, 1l);
        //删除此对象
        entityManager.remove(customer);
        //提交
        transaction.commit();
        //关闭资源
        entityManager.close();
    }

    @Test
    public void updateCustomer() throws Exception {
        //得到EntityManager对象
        EntityManager entityManager = EntityUtil.getEntityManager();
        //开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询Customer对象
        Customer customer = entityManager.find(Customer.class, 3l);
        //更新Customer对象中的属性。
        customer.setCustName("黑米科技");
        customer.setCustPhone("12345678");
        //使用EntityManager把数据更新到数据库中。
        entityManager.merge(customer);
        //提交事务
        transaction.commit();
        //关闭EntityManager对象
        entityManager.close();
    }

    @Test
    public void findCustomerById() throws Exception {
        //获得一个EntityManager对象
        EntityManager entityManager = EntityUtil.getEntityManager();
        //使用find方法根据id查询
        //第一个参数：返回结果的数据类型，第二个参数：主键
        Customer customer = entityManager.find(Customer.class, 2l);
        //打印结果
        System.out.println(customer);
    }
    @Test
    public void findCustomerById2() throws Exception {
        //获得EntityManager对象
        EntityManager entityManager = EntityUtil.getEntityManager();
        //执行查
        Customer customer = entityManager.getReference(Customer.class, 2l);
        //打印结果
        System.out.println(customer);
        entityManager.close();
    }
}
