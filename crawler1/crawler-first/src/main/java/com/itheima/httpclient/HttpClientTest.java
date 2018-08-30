package com.itheima.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {

    @Test
    public void testGet() throws Exception {
        //1）创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2）创建一个HttpGet对象，需要指定请求的url
        HttpGet get = new HttpGet("http://www.itcast.cn");
        //3）使用HttpClient执行get请求，得到服务端响应的数据，封装成一个Response对象。
        CloseableHttpResponse response = httpClient.execute(get);
        //4）从Response对象中取服务端响应的内容。
        int statusCode = response.getStatusLine().getStatusCode();
        //判断是否成功
        if (statusCode == 200) {
            //取服务端响应的内容
            HttpEntity entity = response.getEntity();
            //取响应的文本内容
            //参数1：实体对象 参数2：编码格式
            String content = EntityUtils.toString(entity, "utf-8");
            //5）打印服务端响应的内容。
            System.out.println(content);
        }
        //6）关闭连接
        response.close();
        httpClient.close();
    }

    @Test
    public void testGetWithParam() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://bbs.itheima.com/search.php?mod=forum&searchid=17&orderby=lastpost&ascdesc=desc&searchsubmit=yes&kw=java");
        CloseableHttpResponse response = httpClient.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
        response.close();
        httpClient.close();
    }

    @Test
    public void testPost() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post对象
        HttpPost post = new HttpPost("http://bbs.itheima.com/search.php");
        //执行post
        CloseableHttpResponse response = httpClient.execute(post);
        //取响应的结果
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        }
        response.close();
        httpClient.close();
    }

    @Test
    public void testPostWitParam() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个Post对象
        HttpPost post = new HttpPost("http://bbs.itheima.com/search.php");
        //创建一个Entity对象
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("mod", "forum"));
        form.add(new BasicNameValuePair("searchid", "17"));
        form.add(new BasicNameValuePair("orderby", "lastpost"));
        form.add(new BasicNameValuePair("ascdesc", "desc"));
        form.add(new BasicNameValuePair("searchsubmit", "yes"));
        form.add(new BasicNameValuePair("kw", "java"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        //把表单添加到post对象中
        post.setEntity(entity);
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        }
        response.close();
        httpClient.close();
    }

    @Test
    public void testConnectonPool() throws Exception {
        //创建一个连接池对象
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        //使用HttpClients工具类创建连接
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
        CloseableHttpClient httpClient2 = HttpClients.custom().setConnectionManager(connectionManager).build();
        //执行get请求
        HttpGet get1 = new HttpGet("http://www.itcast.cn");
        HttpGet get2 = new HttpGet("http://www.itheima.com");
        CloseableHttpResponse response1 = httpClient.execute(get1);
        CloseableHttpResponse response2 = httpClient2.execute(get2);
        printResponse(response1);
        printResponse(response2);
        //httpClient不需要关闭
        //httpClient.close();
        //httpClient2.close();
    }
    private void printResponse(CloseableHttpResponse response) throws Exception {
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        response.close();
    }

    @Test
    public void setGetOption() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个Get对象
        HttpGet get = new HttpGet("http://www.itheima.com");
        //设置请求的配置信息
        RequestConfig config = RequestConfig.custom()
                //创建连接的超时时间
                .setConnectTimeout(1000)
                //发起请求的超时时间
                .setConnectionRequestTimeout(500)
                //设置传输过程中的超时时间
                .setSocketTimeout(1000*10)
                .build();
        get.setConfig(config);
        CloseableHttpResponse response = httpClient.execute(get);
        printResponse(response);
        httpClient.close();
    }
}
