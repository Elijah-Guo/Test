package com.itheima.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 取yml文件的值有两种方法:
 * 1.@Value("${person.age}") 2:@ConfigurationProperties注解
 *
 * ConfigurationProperties注解会将springboot的.yml配置文件中的信息
 * 自动映射为实体类的属性
 * 使用：prefix="配置文件中需要映射的key的前缀"
 *
 * 注意：需要添加get/set方法
 */
@RestController
@ConfigurationProperties(prefix="person")
public class ymlTestController {

    //@Value("${person.age}")
    private Integer age;
    //@Value("${person.address}")
    private String address;
    //@Value("${person.job}")
    private String job;

    @RequestMapping("/person")
    public Map test(){
        HashMap map = new HashMap();

        map.put("age",age);
        map.put("address",address);
        map.put("job",job);

        return map;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
