package com.itheima.crawler.dao;

import com.itheima.crawler.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item, Long> {
}
