package com.itheima;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Iterator;

public class QueryTest {

    private TransportClient client;

    @Before
    public void init() throws Exception {
        //创建一个Settings对象
        Settings settings = Settings.builder()
                .put("cluster.name", "my-elasticsearch")
                .build();
        //创建一个连接，连接到es的服务，TransportClient,需要一个Settings配置
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9301))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9302))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9303));
    }

    /**
     * 关键字查询
     */
    @Test
    public void queryByTerm() throws Exception{
        /**
         * 设置查询范围
         */
        SearchResponse response = client.prepareSearch("tony1").setTypes("pp")
                //指定要查询的域和关键字，如果不指定域，则默认查询整个域
                .setQuery(QueryBuilders.termQuery("name", "大佬"))
                .get();
        /**
         * 处理查询结果
         */
        System.out.println("总记录数："+response.getHits().getTotalHits());

        //getHits和internalHits法结果相同

        /*SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.internalHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getSource().get("id"));
            System.out.println(searchHit.getSource().get("name"));
            System.out.println(searchHit.getSource().get("content"));
        }*/
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSource().get("id"));
            System.out.println(hit.getSource().get("name"));
            System.out.println(hit.getSource().get("content"));
        }
        client.close();
    }

    /**
     * 根据id查询
     */
    @Test
    public void queryById() {
        SearchResponse response = client.prepareSearch("tony1").setTypes("pp")
                .setQuery(QueryBuilders.idsQuery().addIds("文档1号"))
                .get();
        //处理查询结果

        SearchHits hits = response.getHits();

        //获取总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        //循环结果集
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()){
            SearchHit searchHit = iterator.next();
            System.out.println(searchHit.getSourceAsString());
        }
        client.close();
    }
    /**
     * 根据queryString方式查询
     * 先分析关键字---再执行查询
     */
    @Test
    public void queryByQueryString() {
        SearchResponse response = client.prepareSearch("tony1").setTypes("pp")
                .setQuery(QueryBuilders.queryStringQuery("成功当上董事长").defaultField("content"))
                .get();

        SearchHits hits = response.getHits();
        System.out.println("总条数:"+hits.getTotalHits());

        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()){
            SearchHit searchHit = iterator.next();
            System.out.println(searchHit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void queryBySortAndHighLightAndPage()throws Exception{
        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                //高亮前缀
                .preTags("<em>")
                //高亮后缀
                .postTags("</em>")
                .field("content");

        SearchResponse response = client.prepareSearch("tony1").setTypes("pp")
                .setQuery(QueryBuilders.queryStringQuery("重要啊不重要").defaultField("content"))
                //高亮
                .highlighter(highlightBuilder)
                //排序
                .addSort("id",SortOrder.ASC)
                //分页,起始行号和每页数据
                /*.setFrom(10)
                .setSize(5)*/
                .get();
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        Iterator<SearchHit> result = hits.iterator();
        while (result.hasNext()){
            //处理查询结果
            SearchHit hit = result.next();
            System.out.println(hit.getSourceAsString());
            //处理高亮结果
            System.out.println("总的高亮的结果:"+hit.getHighlightFields());

            //获取高亮结果的内容，是一个数组，一般只有一个元素
            Text[] names = hit.getHighlightFields().get("content").getFragments();
            if (names!=null){
                for (Text name : names) {
                    System.out.println("每一个高亮结果:"+name);
                }
            }
        }
    }
}
