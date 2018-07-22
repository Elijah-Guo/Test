package cn.itcast.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class HashTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void t1() {
        redisTemplate.boundHashOps("key1").put("1号","熊大");
        redisTemplate.boundHashOps("key1").put("2号","熊二");
        redisTemplate.boundHashOps("key1").put("3号","光头强");
    }

    @Test
    public void t2() {
        Map<String,String> map = redisTemplate.boundHashOps("key1").entries();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Test
    public void t3() {
        redisTemplate.delete("key1");
    }
}
