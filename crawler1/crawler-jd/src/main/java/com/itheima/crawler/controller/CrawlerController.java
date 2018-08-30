package com.itheima.crawler.controller;

import com.itheima.crawler.component.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CrawlerController {

    @Autowired
    private Crawler crawler;

    @RequestMapping("/crawler")
    public Map doCrawler(String action) {
        Map result = new HashMap();
        if ("start".equals(action)) {
            crawler.process();
            result.put("status", "start");
        } else if ("stop".equals(action)) {
            crawler.stop();
            result.put("status", "stop");
        }
        return result;
    }
}
