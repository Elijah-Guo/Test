package com.itheima.test;


import com.itheima.dao.CustomerDao3;
import com.itheima.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaSpecificationTest {

    @Autowired
    private CustomerDao3 customerDao3;

    @Test
    public void testGetCustomerById() {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //客户id等于2的客户
                //参数1：要查询的字段
                //参数2：等于的值
                Predicate predicate = criteriaBuilder.equal(root.get("custId").as(Long.class), 2l);

                return predicate;
            }
        };
        Customer customer = customerDao3.findOne(specification);
        System.out.println(customer);

    }

    @Test
    public void testSpecification2() {
        customerDao3.findAll((root,query,cb)->{
            Predicate p1 = cb.like(root.get("custAddress").as(String.class), "%传智%");
            Predicate p2 = cb.like(root.get("custName").as(String.class), "%传智%");
            Predicate predicate = cb.or(p1, p2);
            return predicate;
        }).forEach(c-> System.out.println(c));
    }

    @Test
    public void testSpecification3() {
        Pageable pageable = new PageRequest(0, 4);
        Page<Customer> page = customerDao3.findAll((root, query, cb) -> {

            Predicate predicate = cb.like(root.get("custName").as(String.class), "%传智%");

            return predicate;

        }, pageable);

        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        page.forEach(c-> System.out.println(c));


    }

    @Test
    public void testSpecificationSort() {
        customerDao3.findAll((root, query, cb)->{
            Predicate p1 = cb.like(root.get("custAddress").as(String.class), "%传智%");
            Predicate p2 = cb.like(root.get("custName").as(String.class), "%传智%");
            //设置排序方式
            CriteriaQuery<?> query1 = query.where(cb.or(p1, p2)).orderBy(cb.desc(root.get("custId").as(Long.class)));

            return query1.getRestriction();
        }).forEach(c-> System.out.println(c));
    }

}
