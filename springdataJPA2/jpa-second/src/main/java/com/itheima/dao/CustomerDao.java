package com.itheima.dao;

import com.itheima.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long> {

    @Query("from Customer")
    List<Customer> selectAllCustomer();
    @Query("from Customer where custId=?")
    Customer getCustomerById(long id);
    @Query("from Customer where custAddress like ?2 and custName like ?1")
    List<Customer> getCustomerByNameAddress(String name, String address);
    @Query("from Customer where custAddress like ?2 and custName like ?1")
    List<Customer> getCustomerByNameAddressPage(String name, String address, Pageable pageable);

    @Query("update Customer set custAddress=?1 where custId=?2")
    @Modifying
    //@Transactional
    void updateAddressById(String address, long id);

    @Query(value = "select * from cst_customer", nativeQuery = true)
    List<Customer> getCustomerList();
}
