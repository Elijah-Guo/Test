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

public class JobProcessor2 implements PageProcessor {
    /**
     * 在此方法中实现解析页面的业务逻辑
     * @param page 就是抓取到的页面封装成的对象
     */
    @Override
    public void process(Page page) {

        Html html = page.getHtml();
        System.out.println(html.$("title","text"));
        //将结果设置到ResultItems对象中
        page.putField("html", html.toString());
        Selectable links = html.links().regex("https://www.jd.com/news.\\w+?.*");
        //把链接的列表添加到Scheduler对象中
        page.addTargetRequests(links.all());

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
        Spider.create(new JobProcessor2())
                //指定要爬取的url
                .addUrl("https://www.jd.com/moreSubject.aspx")
                .addPipeline(new FilePipeline("D:\\temp\\JavaEE318\\html"))
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000000)))
                .run();
    }
}
