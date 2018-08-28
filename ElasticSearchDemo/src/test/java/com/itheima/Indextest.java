package com.itheima;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

public class Indextest {

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
     * 创建索引
     */
    @Test
    public void add() {

        client.admin().indices().prepareCreate("tony1").get();

        client.close();
    }

    /**
     * 创建有mapping的索引
     */
    @Test
    public void addByMapping() throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                    .startObject("dd")
                        .startObject("properties")
                            .startObject("id")
                            .field("type", "text")
                            .field("store", true)
                         //域上加入fielddata设置为true即可实现排序
                            .field("fielddata",true)
                            .endObject()
                .startObject("name")
                            .field("type", "text")
                            .field("store", true)
                            .field("analyzer", "ik_max_word")
                .endObject()
                .startObject("content")
                            .field("type", "text")
                            .field("store", true)
                            .field("analyzer", "ik_max_word")
                .endObject()
                    .endObject()
                        .endObject()
                            .endObject();

        client.admin().indices().preparePutMapping("tony1")
                .setType("dd").setSource(builder.string(), XContentType.JSON)
                 .get();
        client.close();
    }

    /**
     * 删除索引
     */
    @Test
    public void del() {
        client.admin().indices().prepareDelete("tony1").get();
        client.close();
    }
}
