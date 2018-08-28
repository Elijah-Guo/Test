package com.itheima.dao;

import com.itheima.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDao2 extends JpaRepository<Customer, Long> {

    Customer getCustomerByCustId(long id);
    List<Customer> getAllByCustAddressAndCustName(String address, String name);
    List<Customer> findAllByCustIdBetween(long min, long max);
    List<Customer> findByCustIdIn(long[] ids);
    List<Customer> findByCustNameLike(String name, Pageable pageable);
}
