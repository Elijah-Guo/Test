package com.itheima.component;

import com.itheima.entity.JobInfo;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 爬虫的核心业务逻辑
 */
@Component
public class JobProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //1）创建一个PageProcess接口的实现类
        //2）得到抓取后的页面
        Html html = page.getHtml();
        //3）分析页面中的工作详细信息的url地址列表
        List<String> linkList = html.$("div.el > p.t1").links().all();
        if (!linkList.isEmpty()) {
            //4）把工作信息url添加到Scheduler对象中。
            page.addTargetRequests(linkList);
            //6）解析下一页的url添加到Scheduler对象中。
            String nextPageLink = html.$("div.p_in li.bk:last-child").links().get();
            page.addTargetRequest(nextPageLink);
            return;
        }
        //5）如果是工作详情页面解析其中的工作信息，封装到JobInfo对象中，并保存JobInfo对象
        parseDetail(page);
    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    /**
     * 解析工作详情页面
     */
    private void parseDetail(Page page) {
        Html html = page.getHtml();
        //公司名称
        String companyName = html.$("p.cname > a", "text").toString().trim();
        String s = page.getHtml().$("p.msg.ltype", "text").get();
        String[] strings = s.split("\\|");
        //公司地址
        String companyAddress = strings[0].replace(" ", "")
                .replace(" ", "");
        //发布日期
        List<String> timeList = Stream.of(strings)
                .filter(str -> str.contains("发布"))
                .collect(Collectors.toList());
        String time = "";
        if (!timeList.isEmpty()) {
            time = timeList.get(0);
        }
        //公司信息
        String compoanyInfo = html.$("div.tmsg.inbox", "text").get();
        //职位名称
        String title = html.$("div.cn > h1", "title").get();
        //取上班地址
        String address = html.$("div.bmsg.inbox > p.fp", "text").get();
        //职位信息
        String job_info = html.$("div.bmsg.job_msg.inbox").get();
        job_info = Jsoup.parse(job_info).text();
        //取薪资范围
        String salary = html.$("div.cn > strong", "text").get();
        Integer[] salayrs = MathSalary.getSalary(salary);
        int sMax = salayrs[1];
        int sMin = salayrs[0];
        //取url
        String url = page.getUrl().get();
        //封装到对象中
        JobInfo jobInfo = new JobInfo();
        jobInfo.setCompany_addr(companyAddress);
        jobInfo.setCompany_info(compoanyInfo);
        jobInfo.setCompany_name(companyName);
        jobInfo.setJob_addr(address);
        jobInfo.setJob_info(job_info);
        jobInfo.setJob_name(title);
        jobInfo.setSalary_max(sMax);
        jobInfo.setSalary_min(sMin);
        jobInfo.setTime(time);
        jobInfo.setUrl(url);
        //添加到pipeline对象中
        page.putField("jobInfo", jobInfo);

    }
}
