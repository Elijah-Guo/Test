package com.itheima.service.impl;

import com.itheima.dao.JobInfoDao;
import com.itheima.entity.JobInfo;
import com.itheima.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    public void save(JobInfo jobInfo) {
        jobInfoDao.save(jobInfo);
    }
}
