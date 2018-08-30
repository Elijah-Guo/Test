package com.itheima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CrawlerController {
    @Autowired
    private Spider spider;

    @RequestMapping("/crawler")
    public Map crawler(String action) {
        Map map = new HashMap();
        if ("start".equals(action)) {
            spider.start();
            map.put("status","start");
        } else if ("stop".equals(action)) {
            spider.stop();
            map.put("status","stop");
        }
        return map;
    }

}
