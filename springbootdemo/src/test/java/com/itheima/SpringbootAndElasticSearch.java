package com.itheima;


import com.itheima.Entity.UserEntity;
import com.itheima.respostiry.UserRespitory;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringbootAndElasticSearch {

    @Autowired
    private UserRespitory UserRespitory;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 添加索引
     */
    @Test
    public void addIndex() {
        elasticsearchTemplate.createIndex(UserEntity.class);
        elasticsearchTemplate.putMapping(UserEntity.class);
    }

    /**
     * 添加文档
     */
    @Test
    public void addDocument() {
        for (int i = 0; i < 20; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(i);
            userEntity.setUsername("lisi" + i);
            userEntity.setPassword("123");
            userEntity.setName("李四" + i);
            UserRespitory.save(userEntity);

        }
    }
    /**
     * queryString查询
     */
    @Test
    public void queryString() {

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("李四").defaultField("name"))
                .withPageable(PageRequest.of(0,5))
                .build();

        List<UserEntity> userEntities = elasticsearchTemplate.queryForList(query, UserEntity.class);
        userEntities.forEach(c-> System.out.println(c));
    }

    /**
     * 高亮
     *
     * 代码思路
     * queryForPage() 参数一，查询对象（设置好高亮条件）,参数二：实体类类型,参数三：返回值
     */
    @Test
    public void findByHighLight() {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("李四").defaultField("name"))
                .withPageable(PageRequest.of(0,5))
                .withHighlightFields(new HighlightBuilder.Field("name")
                .preTags("<em>").postTags("</em>")
                ).build();
        elasticsearchTemplate.queryForPage(query, UserEntity.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                SearchHits hits = searchResponse.getHits();
                System.out.println("总记录数"+hits.getTotalHits());
                Iterator<SearchHit> iterator = hits.iterator();
                List<UserEntity> list = new ArrayList<>();
                while (iterator.hasNext()){
                    SearchHit searchHit = iterator.next();
                    String id = searchHit.getSource().get("id").toString();
                    String username = searchHit.getSource().get("username").toString();
                    String password = searchHit.getSource().get("password").toString();

                    //取高亮结果
                    String name = searchHit.getHighlightFields().get("name").getFragments()[0].toString();
                    /*String name = searchHit.getSource().get("name").toString();*/

                    //将高亮结果放入实体类中
                    UserEntity entity = new UserEntity();
                    entity.setId(Integer.parseInt(id));
                    entity.setName(name);
                    entity.setUsername(username);
                    entity.setPassword(password);
                    //将实体类放入集合中
                    list.add(entity);
                }
                AggregatedPage aggregatedPage=new AggregatedPageImpl(list,pageable,hits.getTotalHits());
                return aggregatedPage;
            }
        }).forEach(c-> System.out.println(c));
    }
}
