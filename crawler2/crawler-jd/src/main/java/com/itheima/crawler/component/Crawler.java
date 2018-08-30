package com.itheima.crawler.component;

import com.itheima.crawler.dao.ItemDao;
import com.itheima.crawler.entity.Item;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

@Component
public class Crawler {

    @Autowired
    private HttpClientUtil httpClientUtil;
    @Autowired
    private ItemDao itemDao;

    private Thread tCrawler;
    private String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA" +
            "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655" +
            "&s=59&click=0&page=";

    public void process() {
        //开启新的线程执行爬取操作。
        tCrawler = new Thread(new CrawlerJob());
        tCrawler.start();
    }

    public void stop() {
        tCrawler.interrupt();
    }

    class CrawlerJob implements Runnable {

        @Override
        public void run() {
            //实现爬取的业务逻辑
            //创建一个HttpClient对象
            CloseableHttpClient httpClient = httpClientUtil.getHttpClient();
            //循环爬取商品列表页面
            for (int i = 0; i < 10; i++) {
                HttpGet get  = new HttpGet(url + (i * 2 + 1));
                try {
                    CloseableHttpResponse response = httpClient.execute(get);
                    //解析页面中的商品数据
                    if (response.getStatusLine().getStatusCode() == 200) {
                        //取响应的html页面
                        HttpEntity entity = response.getEntity();
                        String itemListHtml = EntityUtils.toString(entity, "utf-8");
                        //解析页面
                        Document document = Jsoup.parse(itemListHtml);
                        document.select("li.gl-item").forEach(li->{
                            try{
                                String sku = li.attr("data-sku");
                                String spu = li.attr("data-spu");
                                String title = li.select("div.p-name em").text();
                                String price = li.select("div.p-price i").text();
                                String url = li.select("div.p-img > a").attr("href");
                                String imgURL = li.select("div.p-img img").attr("source-data-lazy-img");
                                //下载图片
                                String image = downLoadImage(imgURL);
                                //封装到Item对象中
                                Item item = new Item();
                                item.setSku(Long.parseLong(sku));
                                item.setSpu(Long.parseLong(spu));
                                item.setTitle(title);
                                if (StringUtils.isNotBlank(price)) {
                                    item.setPrice(Float.parseFloat(price));
                                } else {
                                    item.setPrice(0f);
                                }
                                item.setUrl(url);
                                item.setPic(image);
                                item.setCreated(new Date());
                                item.setUpdated(new Date());

                                //写入数据库
                                itemDao.save(item);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //把商品封装成Item对象
            //把商品对象写入索引库
            //下载商品图片
        }
        private String downLoadImage(String url) throws Exception {
            //生成文件名
            String fileName = UUID.randomUUID() + url.substring(url.lastIndexOf("."));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\temp\\JavaEE318\\images\\" + fileName));
            //下载图片
            CloseableHttpClient httpClient = httpClientUtil.getHttpClient();
            HttpGet get = new HttpGet("http:" + url);
            CloseableHttpResponse response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                entity.writeTo(fileOutputStream);
                fileOutputStream.close();
            }
            return fileName;
        }
    }
}
