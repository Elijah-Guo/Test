package com.itheima.httpclient;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class JsoupTest {
    @Test
    public void testJsoupFirst() throws Exception {
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 2000);
        Elements elements = document.getElementsByTag("title");
        elements.forEach(e-> System.out.println(e.text()));
    }

    @Test
    public void testJsoupLoadLocalFile() throws Exception {
        Document document = Jsoup.parse(new File("d:/temp/test.html"), "utf-8");
        document.getElementsByTag("title")
                .forEach(e-> System.out.println(e.text()));
    }

    @Test
    public void testJsoupLoadString() throws Exception {
        String fileContent = FileUtils.readFileToString(new File("d:/temp/test.html"), "utf-8");
        Document document = Jsoup.parse(fileContent);
        document.getElementsByTag("title")
                .forEach(e-> System.out.println(e.text()));
    }

    @Test
    public void testJsoupParse() throws Exception {
        String fileContent = FileUtils.readFileToString(new File("d:/temp/test.html"), "utf-8");
        Document document = Jsoup.parse(fileContent);
        //根据tag解析
        //document.getElementsByTag("li").forEach(e-> System.out.println(e));
        //根据id解析
        //Element element = document.getElementById("test");
        //System.out.println(element.toString());
        //根据class解析
        //document.getElementsByClass("city_in").forEach(e-> System.out.println(e.toString()));
        //根据属性解析
        //document.getElementsByAttribute("abc").forEach(e-> System.out.println(e));
        document.getElementsByAttributeValue("class", "s_name").forEach(e-> System.out.println(e));

    }

    @Test
    public void testJsoupSelect() throws Exception {
        String fileContent = FileUtils.readFileToString(new File("d:/temp/test.html"), "utf-8");
        Document document = Jsoup.parse(fileContent);
        //根据tag解析
        //document.select("title").forEach(e-> System.out.println(e));
        //根据class解析
        //document.select(".s_name").forEach(e-> System.out.println(e));
        //根据属性解析
        //document.select("[abc]").forEach(e-> System.out.println(e));
        //document.select("[class=s_name]").forEach(e-> System.out.println(e));
        //根据id解析
        //document.select("#city_bj").forEach(e-> System.out.println(e));
        //组合条件解析
        document.select("div.city_con > ul > li span").forEach(e-> System.out.println(e.text()));
    }
}
