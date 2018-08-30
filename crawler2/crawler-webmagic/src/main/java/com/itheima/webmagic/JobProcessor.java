package com.itheima.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class JobProcessor implements PageProcessor {
    /**
     * 在此方法中实现解析页面的业务逻辑
     * @param page 就是抓取到的页面封装成的对象
     */
    @Override
    public void process(Page page) {
        //从page中取抓取的页面
        Html html = page.getHtml();
        //System.out.println(html);
        //使用Jsoup的方式解析
        //String title = html.getDocument().getElementsByTag("title").text();
        //System.out.println(title);
        //使用xpath方式解析
        //Selectable meta = html.xpath("/html/head/meta[@name='keywords']/@content");
        //meta.all().forEach(e-> System.out.println(e));
        /*List<String> all = meta.all();
        System.out.println(all);
        System.out.println(meta.get());
        System.out.println(meta.toString());*/
        /*Selectable xpath = html.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/h3/text()");
        System.out.println(xpath);*/
        //使用css选择器
        //Selectable css = html.css("meta");
        //Selectable css = html.css("meta[name='keywords']");
        //html.css("#id");
        //Selectable css = html.css("div.inner div.box_hd");
        //Selectable css = html.css("meta[name='keywords']", "content");
        /*Selectable css = html.$("title", "text");
        css.all().forEach(s-> System.out.println(s));*/
        //使用正则表达式
        Selectable links = html.links();
        links.all().forEach(l-> System.out.println(l));
        System.out.println("----------------------");
        links.regex("https://www.jd.com/news.\\w+?.*")
                .all().forEach(s-> System.out.println(s));



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
        Spider.create(new JobProcessor())
                //指定要爬取的url
                //.addUrl("http://www.itcast.cn")
                .addUrl("https://www.jd.com/moreSubject.aspx")
                .run();
    }
}
