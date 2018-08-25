package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.service.UserMapperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * springboot与junit整合
 * RunWith-->SpringRunner继承自SpringJUnit4ClassRunner,使用哪个引擎都可以
 * SpringBootTest--->相当于之前的@ContextConfiguration，这里指向启动类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class springbootTest {
    @Autowired
    private UserMapperService userMapperService;

    @Test
    public void springbootAndJunit() {
        userMapperService.findAll().forEach(c-> System.out.println(c));
    }
}
