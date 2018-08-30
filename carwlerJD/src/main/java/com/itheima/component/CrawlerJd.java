package com.itheima.component;

import com.itheima.entity.Item;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * 主要业务
 * 判断是否获取到资源，是否利用消息队列不断获取，是否保存到数据库
 */
@Component
public class CrawlerJd implements PageProcessor{

    private Site site=Site.me();

    @Override
    public void process(Page page) {
        //获取商品列表的所有商品url
        List<String> linkList = page.getHtml().$("div#J_goodsList li.gl-item div.p-img").links().all();
        //判断url是否为空
        if (linkList.isEmpty()){
            //如果是空说明是商品详情页面,直接调用方法解析页面,封装内容
            parseItem(page);
            //返回
            return;
        }
        //如果是空,是商品列表页面,将url添加入队列中
        page.addTargetRequests(linkList);

        //获取当前页面的url
        String url = page.getUrl().get();

        //翻页,调用方法，传参数url进去
        Request request = new Request("http://nextpage.do");
        request.putExtra("nextpage", url);
        page.addTargetRequest(request);
    }


    @Override
    public Site getSite() {
        //设置一个抓取间隔时间为1秒
        site.setSleepTime(1000);
        return site;
    }

    /**
     * 分析html,封装内容到实体类
     * @param page
     */
    private void parseItem(Page page) {
        Html html = page.getHtml();
        String sku = html.$("div.left-btns > a.follow.J-follow", "data-id").get();
        String title = html.$("div.sku-name", "text").get().trim();
        String price = html.$("span.p-price > span.price", "text").get();
        String picUrl = "https:" + html.$("img#spec-img", "data-origin").get();

        Item item = new Item();
        item.setPic(picUrl);
        item.setUrl(page.getUrl().toString());
        item.setPrice(Float.parseFloat(price));
        item.setTitle(title);
        item.setSku(Long.parseLong(sku));

        //将封装好的实体类放入Pipeline持久化到数据库
        page.putField("item",item);
    }
}
