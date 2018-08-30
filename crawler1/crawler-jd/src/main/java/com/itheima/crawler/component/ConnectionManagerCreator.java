package com.itheima.crawler.component;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionManagerCreator {

    @Bean
    public PoolingHttpClientConnectionManager createPoolingConnectionManager() {
        return new PoolingHttpClientConnectionManager();
    }
}
