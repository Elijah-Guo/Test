package com.itheima.entity;

import javax.persistence.*;

@Entity
@Table(name = "job_info")
public class JobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String company_name;
    @Column
    private String company_addr;
    @Column
    private String company_info;
    @Column
    private String job_name;
    @Column
    private String job_addr;
    @Column
    private String job_info;
    @Column
    private int salary_min;
    @Column
    private int salary_max;
    @Column
    private String url;
    @Column
    private String time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_addr() {
        return company_addr;
    }

    public void setCompany_addr(String company_addr) {
        this.company_addr = company_addr;
    }

    public String getCompany_info() {
        return company_info;
    }

    public void setCompany_info(String company_info) {
        this.company_info = company_info;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_addr() {
        return job_addr;
    }

    public void setJob_addr(String job_addr) {
        this.job_addr = job_addr;
    }

    public String getJob_info() {
        return job_info;
    }

    public void setJob_info(String job_info) {
        this.job_info = job_info;
    }

    public int getSalary_min() {
        return salary_min;
    }

    public void setSalary_min(int salary_min) {
        this.salary_min = salary_min;
    }

    public int getSalary_max() {
        return salary_max;
    }

    public void setSalary_max(int salary_max) {
        this.salary_max = salary_max;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
