package com.itheima.Entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;

@Document(indexName = "springboot",type = "haha")
public class UserEntity {
    @Id
    @Field(type = FieldType.Integer,store = true)
    private Integer id;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_max_word")
    private String username;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_max_word")
    private String password;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_max_word")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
