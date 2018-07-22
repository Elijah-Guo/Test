package cn.itcast.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class StringTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void t1() {
        redisTemplate.boundValueOps("key1").set("111");
    }

    @Test
    public void t2() {
        Object key1 = redisTemplate.boundValueOps("key1").get();
        System.out.println(key1);
    }

    @Test
    public void t3() {
        redisTemplate.delete("key1");
    }
}
