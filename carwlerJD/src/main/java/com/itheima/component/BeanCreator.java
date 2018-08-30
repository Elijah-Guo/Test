package com.itheima.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Spider;

/**
 * 创建spider
 */
@Configuration
public class BeanCreator {

    String url="https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=" +
            "utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=78cd6232ce90431c902f58ef5125a3c3";

    @Bean
    public Spider spiderCreate(CrawlerJd crawlerJd, ItemPipeline itemPipeline, JdDownLoader downLoader){
        Spider spider = Spider.create(crawlerJd)
                .addUrl(url)
                .addPipeline(itemPipeline)
                .setDownloader(downLoader);
        return spider;
    }
}
