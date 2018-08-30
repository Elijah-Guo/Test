package com.itheima.controller;

import com.itheima.component.CrawlerJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    private CrawlerJob crawlerJob;

    @RequestMapping("/test/start")
    public void test(){
        crawlerJob.goodJob();
    }
}
