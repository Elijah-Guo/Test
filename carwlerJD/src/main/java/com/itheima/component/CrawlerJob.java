package com.itheima.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class CrawlerJob {

    @Autowired
    private Spider spider;

    /**
     * 任务开启,定时一小时执行一次
     */
    @Scheduled(fixedRate = 1000*3600)
    public void goodJob(){
        spider.run();
    }
}
