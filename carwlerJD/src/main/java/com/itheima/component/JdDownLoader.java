package com.itheima.component;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * 数据下载方式,利用chrome内核能够获取完整的京东商品页数据
 * 并模拟滑动和点击下一页不断爬取数据
 */
@Component
public class JdDownLoader implements Downloader{

    private RemoteWebDriver remoteWebDriver;

    public JdDownLoader() {

        /*DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\temp\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        remoteWebDriver = new PhantomJSDriver(capabilities);
        remoteWebDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);*/

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();

//        设置为 headless 模式 （必须）
        chromeOptions.addArguments("--headless");
//        设置浏览器窗口打开大小  （非必须）
        chromeOptions.addArguments("--window-size=1920,1080");
        remoteWebDriver = new ChromeDriver(chromeOptions);
    }

    /**
     * 利用chrome内核，模拟滑动和点击下一页获取数据
     * @param request
     * @param task
     * @return
     */
    @Override
    public Page download(Request request, Task task) {
        try {
            String url = request.getUrl();
            System.out.println("begin:" + url);
            if (StringUtils.isBlank(url)) {
                return Page.fail();
            }
            //翻页处理
            if ("http://nextpage.do".equals(url)) {
                String preUrl = (String) request.getExtra("nextpage");
                remoteWebDriver.get(preUrl);
                remoteWebDriver.findElementByCssSelector("div#J_topPage a.fp-next").click();
                Thread.sleep(2000);
                //加载后部数据
                remoteWebDriver.executeScript("window.scrollTo(0, document.body.scrollHeight - 300)");
                Thread.sleep(2000);
                //取页面内容
                String html = remoteWebDriver.getPageSource();
                //取当前url
                String currentUrl = remoteWebDriver.getCurrentUrl();
                return createPage(currentUrl, html);
            }
            remoteWebDriver.get(url);
            //加载后部数据
            remoteWebDriver.executeScript("window.scrollTo(0, document.body.scrollHeight - 300)");
            Thread.sleep(2000);
            //取页面内容
            String html = remoteWebDriver.getPageSource();
            return createPage(url, html);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Page.fail();
    }

    /**
     * 根据url获取当前url下的html内容-->传给page
     * @param url
     * @param content
     * @return
     */
    private Page createPage(String url, String content) {
        Page page = new Page();
        page.setRawText(content);
        page.setUrl(new PlainText(url));
        page.setRequest(new Request(url));
        page.setDownloadSuccess(true);

        return page;
    }

    @Override
    public void setThread(int i) {

    }
}
