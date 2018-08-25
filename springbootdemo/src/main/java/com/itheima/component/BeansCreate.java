package com.itheima.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.regexp.internal.RE;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
public class BeansCreate {

    /**
     * 将ObjectMapper作为一个bean交由spring管理
     * @return
     */
    @Bean
    public ObjectMapper createObjectMapper(){
        return new ObjectMapper();
    }

    /*@Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }*/

}
