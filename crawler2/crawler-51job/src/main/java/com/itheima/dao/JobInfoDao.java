package com.itheima.dao;

import com.itheima.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInfoDao extends JpaRepository<JobInfo, Long> {
}
