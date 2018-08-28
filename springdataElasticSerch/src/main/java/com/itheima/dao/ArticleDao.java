package com.itheima.dao;

import com.itheima.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleDao extends ElasticsearchRepository<Article,Integer> {

    /**
     * 根据JPA的规则自定义方法
     * @param title
     * @return
     */
    List<Article> findByTitle(String title);

    List<Article> findByTitleAndContent(String title,String content);
}
