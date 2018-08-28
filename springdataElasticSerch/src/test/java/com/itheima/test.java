package com.itheima;

import com.itheima.dao.ArticleDao;
import com.itheima.entity.Article;
import org.elasticsearch.search.suggest.SortBy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test {
    @Autowired
    private ArticleDao articleDao;

    //注入elasticSearchTemplate对象
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建索引并创建mapping
     */
    @Test
    public void creatIndex() {
        //创建索引
        elasticsearchTemplate.createIndex(Article.class);
        System.out.println("索引创建成功");
        //创建mapping
        elasticsearchTemplate.putMapping(Article.class);
        System.out.println("mapping创建成功");
    }

    /**
     * 创建文档
     * 修改文档--删除旧文档，增加新文档
     */
    @Test
    public void creatDocument() {

        for (int i = 0; i < 20; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle("斌哥"+i+"号");
            article.setContent("阿里"+i+"号");
            articleDao.save(article);
        }
    }

    /**
     * 修改文档
     */
    @Test
    public void updateDocument() {

        Article article = new Article();
        article.setId(2);
        article.setTitle("戊戌年");
        article.setContent("javaEE真香");

        articleDao.save(article);
    }

    /**
     * 删除文档
     */
    @Test
    public void delDocument() {
        articleDao.deleteById(2);
    }

    /**
     * 按ID查询
     *
     */
    @Test
    public void findById() {
        Optional<Article> result = articleDao.findById(3);
        Article article = result.get();
        System.out.println(article);
    }
    /**
     * 按关键字查询
     *
     * 可以根据JPA的命名规则自行创建方法
     */
    @Test
    public void findByTerm() {
        List<Article> list = articleDao.findByTitle("斌哥号");
        System.out.println(list.size());
        for (Article article : list) {
            System.out.println(article);
        }
    }

    /**
     * 分页查询并排序
     *
     * 排序暂时不可用，主键id字段添加fileddata字段设置为true,
     * 图形化界面查看并未添加此字段
     */
    @Test
    public void queryByPageAndSort()throws Exception {
        PageRequest page = PageRequest.of(1, 5/*,Sort.by(Sort.Order.asc("id"))*/);
        Iterable<Article> articles = articleDao.findAll(page);
        articles.forEach(c-> System.out.println(c));
    }
}
