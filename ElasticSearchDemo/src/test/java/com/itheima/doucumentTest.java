package com.itheima;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.Binge;
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

public class doucumentTest {
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
     * 添加文档/修改文档即是将原文档对象删除重新添加新的文档，用到了luncene的底层原理
     *
     * 采用XContentFactory拼接字符串
     */
    @Test
    public void add()throws Exception {
        //循环创建一些文档
        for (int i = 0; i < 20; i++) {

            //1、创建一个Settings对象
            //2、创建一个连接，连接到es的服务，TransportClient,需要一个Settings配置
            //3、创建一个文档，json数据格式。同样可以使用XContentBuilder创建。
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject()
                    .field("id", i+"")
                    .field("name","成功入职")
                    .field("content", "复制之所以重要，有两个主要原因： 在分片/节点失败的情况下，提供了高可用性。因为这个原因，注意到复制分片从不与原/主要（original/primary）分片置于同一节点上是非常重要的。")
                    .endObject();
            //4、使用client把文档添加到索引库
            //如果不存在对应的index或者type会自动创建
            //.prepareIndex 里面封装了很多常用方法 建议使用
            client.prepareIndex("tony1", "pp").setId(i+"号文档对象")
                    .setSource(builder).get();
        }
        //5、关闭client
        client.close();
    }

    @Test
    public void del() {
        client.prepareDelete("tony1","pp","1号文档对象")
                //执行操作.get()---并不是发送方式哦
        .get();
        client.close();
    }

    /**
     * 使用java对象作为文档对象，转为json字符串形式直接添加文档
     */
    @Test
    public void addByJava()throws Exception {
        Binge binge = new Binge();
        binge.setId("斌哥1号");
        binge.setName("大佬");
        binge.setContent("成功入职阿里");

        //将java对象装换为json对象
        String json = new ObjectMapper().writeValueAsString(binge);

        client.prepareIndex("tony1","pp").setId("文档1号")
                .setSource(json, XContentType.JSON)
                .get();
        client.close();
    }
}
