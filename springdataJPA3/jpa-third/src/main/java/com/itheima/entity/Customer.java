package com.itheima.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cst_customer")
//@SecondaryTable(name = "cst_customer_ext", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ext_id", referencedColumnName = "cust_id"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private long custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_source")
    private String custSource;
    @Column(name = "cust_industry")
    private String curstIndustry;
    @Column(name = "cust_level")
    private String custLevel;
    @Column(name = "cust_address")
    private String custAddress;
    @Column(name = "cust_phone")
    private String custPhone;

    @OneToOne(fetch = FetchType.LAZY)/*(targetEntity = CustomerExt.class)*/
    //@JoinColumn(name = "ext_id"/*, referencedColumnName = "ext_id"*/)
    @PrimaryKeyJoinColumn
    private CustomerExt customerExt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    //@JoinColumn(name = "lkm_cust_id")
    private Set<LinkMan> linkMans = new HashSet<>();

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    public CustomerExt getCustomerExt() {
        return customerExt;
    }

    public void setCustomerExt(CustomerExt customerExt) {
        this.customerExt = customerExt;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", curstIndustry='" + curstIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCurstIndustry() {
        return curstIndustry;
    }

    public void setCurstIndustry(String curstIndustry) {
        this.curstIndustry = curstIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
}
