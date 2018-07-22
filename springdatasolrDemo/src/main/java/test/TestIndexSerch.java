package test;

import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import poji.Item;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-solr.xml"})
public class TestIndexSerch {

    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void serch() {
        //创建简单查询对象,并设置查询方式
        SimpleQuery query = new SimpleQuery("*:*");
        //设置查询条件
        Criteria criteria = new Criteria( "item_title").contains("三星");
        //将查询条件添加到查询对象中
        query.addCriteria(criteria);

        //设置分页参数
        //起始页
        query.setOffset(10);
        //页容量
        query.setRows(20);

        //调用solrTemplate进行简单查询
        ScoredPage<Item> queryForPage = solrTemplate.queryForPage(query, Item.class);

        //结果集中获取数据
        //总页数
        long totalcount = queryForPage.getTotalElements();
        System.out.println(totalcount);
        List<Item> items = queryForPage.getContent();

        for (Item item : items) {
            System.out.println(item.getId());
            System.out.println(item.getTitle());
            System.out.println(item.getSeller());
            System.out.println("============================");
        }
    }
}
