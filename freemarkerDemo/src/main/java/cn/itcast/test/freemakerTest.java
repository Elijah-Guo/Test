package cn.itcast.test;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class freemakerTest {


    public static void main(String[] args) throws  Exception{

        //初始化模版
        Configuration configuration = new Configuration();

        //设置模版路径   (绝对路径 ,指定到包名)
        configuration.setDirectoryForTemplateLoading(new File("D:\\IdeaProjects\\pinyougou\\freemarkerDemo\\src\\main\\resources\\ftl"));

        //获取模版对象
        Template template = configuration.getTemplate("test.ftl.html");

        //map类型 静态页面可以根据String类型的key拿取数据库的Object类型数据
        Map<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("message","我是你斌哥");

        Writer out = new FileWriter(new File("666.html"));

        //两个参数：1：数据源信息(object类型的数据源信息) 2：输出文件位置及文件名
        template.process(map,out);
    }
}
