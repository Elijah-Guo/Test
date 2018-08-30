package com.itheima.component;

import com.itheima.entity.JobInfo;
import com.itheima.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class JobPipeline implements Pipeline {

    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //取工作信息对象
        JobInfo jobInfo = resultItems.get("jobInfo");
        jobInfoService.save(jobInfo);
    }
}
