package com.itheima.crawler.component;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtil {

    @Autowired
    private PoolingHttpClientConnectionManager connectionManager;

    public CloseableHttpClient getHttpClient() {

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .build();
        return httpClient;
    }
}
