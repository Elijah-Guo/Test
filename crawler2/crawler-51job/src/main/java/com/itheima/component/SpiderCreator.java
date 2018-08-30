package com.itheima.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Spider;

@Configuration
public class SpiderCreator {

    private String url = "https://search.51job.com/" +
            "list/010000,000000,0000,32,9,99,%2B,2,2.html?lang=c&stype=1" +
            "&postchannel=0000&workyear=99&cotype=99&degreefrom=99" +
            "&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9" +
            "&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Bean
    public Spider createSpider(JobProcessor jobProcessor, JobPipeline pipeline) {
        Spider spider = Spider.create(jobProcessor)
                .addUrl(url)
                .addPipeline(pipeline)
                .thread(5);
        return spider;
    }
}
