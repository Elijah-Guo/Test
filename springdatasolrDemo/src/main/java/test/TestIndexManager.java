package test;

import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import poji.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-solr.xml"})
public class TestIndexManager {

    //注入springdatasolr的solrTemplate
    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 增加/修改  同一方法
     */
    @Test
    public void test(){

        List<Item> items = new ArrayList<>();
        for (long i = 0; i < 100; i++) {
            Item item = new Item();
            item.setId(i);
            item.setBrand("三星"+i);
            item.setCategory("大家电"+i);
            item.setTitle("三星电冰箱"+i);
            item.setImage("xxxxx.jpg");
            item.setGoodsId(i);
            item.setSeller("张三");
            item.setPrice(new BigDecimal("9999"));
            items.add(item);
        }


        solrTemplate.saveBeans(items);

        solrTemplate.commit();
    }

    /**
     * 删除
     */
    @Test
    public void test2(){

        SimpleQuery query = new SimpleQuery("*:*");
        //solrTemplate.deleteById("1");
        solrTemplate.delete(query);

        solrTemplate.commit();
    }



}
