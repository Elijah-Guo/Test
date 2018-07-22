package cn.itcast.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class ListTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void t1() {
        redisTemplate.boundListOps("testList").leftPush("001");
        redisTemplate.boundListOps("testList").leftPush("002");
        redisTemplate.boundListOps("testList").leftPush("003");
        redisTemplate.boundListOps("testList").leftPush("004");
        redisTemplate.boundListOps("testList").leftPush("005");
    }

    @Test
    public void t4() {
        redisTemplate.boundListOps("testList").rightPush("001");
        redisTemplate.boundListOps("testList").rightPush("002");
        redisTemplate.boundListOps("testList").rightPush("003");
        redisTemplate.boundListOps("testList").rightPush("004");
        redisTemplate.boundListOps("testList").rightPush("005");
    }

    @Test
    public void t2() {
        List testList = redisTemplate.boundListOps("testList").range(0, 10);
        System.out.println(testList);
    }

    @Test
    public void t3() {
        redisTemplate.delete("testList");
    }
}
