package com.itheima.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.stream.Stream;

public class JobProcessorJob implements PageProcessor {
    /**
     * 在此方法中实现解析页面的业务逻辑
     * @param page 就是抓取到的页面封装成的对象
     */
    @Override
    public void process(Page page) {

       page.getHtml().$("p.msg.ltype").all()
               .forEach(e-> System.out.println(e));
        String s = page.getHtml().$("p.msg.ltype", "text").get();
        System.out.println(s);
        String[] strings = s.split("\\|");
        Stream.of(strings).forEach(a->
                System.out.println(a.replace(" ","")
                        .replace(" ","")));

    }

    /**
     * 返回一个Site对象，Site对当前爬虫的一个配置信息
     * @return
     */
    @Override
    public Site getSite() {
        return Site.me();
    }

    public static void main(String[] args) {
        Spider.create(new JobProcessorJob())
                //指定要爬取的url
                .addUrl("https://jobs.51job.com/beijing-mtgq/106111898.html?s=01&t=0")
                //.addPipeline(new FilePipeline("D:\\temp\\JavaEE318\\html"))
                //.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000000)))
                .run();
    }
}
