package com.itheima.respostiry;

import com.itheima.Entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRespitory extends ElasticsearchRepository<UserEntity,Integer> {
}
