package com.itheima.entity;

import javax.persistence.*;

@Entity
@Table(name = "cst_customer_ext")
public class CustomerExt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ext_id")
    private Long extId;

    @Column(name = "ext")
    private String ext;

    @OneToOne(mappedBy = "customerExt", fetch = FetchType.LAZY)
    //@JoinColumn(name = "cust_id")
    @PrimaryKeyJoinColumn
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getExtId() {
        return extId;
    }

    public void setExtId(Long extId) {
        this.extId = extId;
    }

    public String getExt() {
        return ext;
    }

    @Override
    public String toString() {
        return "CustomerExt{" +
                "extId=" + extId +
                ", ext='" + ext + '\'' +
                ", customer=" + customer +
                '}';
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
