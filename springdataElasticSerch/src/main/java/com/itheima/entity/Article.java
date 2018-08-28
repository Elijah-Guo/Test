package com.itheima.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "baby",type = "please")
public class Article {

    @Id
    @Field(type = FieldType.Integer,index = false,store = true,fielddata = true)
    private Integer id;
    @Field(type = FieldType.text,store = true,analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.text,store = true,analyzer = "ik_max_word")
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
