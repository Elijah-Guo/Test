package com.itheima.test.jpa;

import com.itheima.jap.entity.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPQLTest {
    @Test
    public void findAll() throws Exception {
        //获得EntityManager对象
        EntityManager entityManager = EntityUtil.getEntityManager();
        //使用EntityManager创建一个Query对象，基于jpql创建
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        //使用query对象执行查询
        List<Customer> list = query.getResultList();
        //打印结果
        for (Customer c: list
             ) {
            System.out.println(c);
        }
    }


    @Test
    public void findAllWithParameter() throws Exception {
        EntityManager entityManager = EntityUtil.getEntityManager();
        //创建一个jpql语句
        String jpql = "from Customer where custName like ?";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        //设置参数
        query.setParameter(1, "%科技%");
        //执行查询
        query.getResultList().stream().forEach(c-> System.out.println(c));
    }

    @Test
    public void findAllWithPage() throws Exception {
        EntityManager entityManager = EntityUtil.getEntityManager();
        TypedQuery<Customer> query = entityManager.createQuery("from Customer ", Customer.class);
        //设置分页信息
        //起始行号，从0开始
        query.setFirstResult(1);
        //每页的行数
        query.setMaxResults(2);
        //执行查询
        query.getResultList()
                .stream()
                .forEach(c-> System.out.println(c));

    }

    @Test
    public void findAllWithSort() throws Exception {
        EntityManager entityManager = EntityUtil.getEntityManager();
        TypedQuery<Customer> query = entityManager.createQuery("from Customer order by custId desc ", Customer.class);
        //执行查询
        query.getResultList()
                .stream()
                .forEach(c-> System.out.println(c));
    }

    @Test
    public void findAllCount() throws Exception {
        EntityManager entityManager = EntityUtil.getEntityManager();
        Query query = entityManager.createQuery("select count(*) from Customer ");
        Long count = (Long) query.getSingleResult();
        System.out.println(count);

    }
}
