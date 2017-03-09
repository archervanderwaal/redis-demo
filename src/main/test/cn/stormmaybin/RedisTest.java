package cn.stormmaybin;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>Created by mayongbin01 on 2017/3/9.</p>
 */
public class RedisTest extends ConfigTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 简单测试插入和获取数据
     */
    @Test
    public void getAndPutTest() {
        redisTemplate.opsForHash().put("user", "age", "20");
        Object object = redisTemplate.opsForHash().get("user", "age");
        System.out.println(object);
    }

    /**
     * 测试缓存
     */
    @Test
    public void testCache() throws InterruptedException {
        redisTemplate.opsForHash().put("user", "name", "StormMa");
        // 设置失效时间为3秒
        redisTemplate.expire("user", 3, TimeUnit.SECONDS);
        Thread.sleep(1000);

        //1s后的值
        Object object = redisTemplate.opsForHash().get("user", "name");
        System.out.println("1秒后：" + object);
        Thread.sleep(2000);
        // 2秒后获取
        object = redisTemplate.opsForHash().get("user", "name");
        System.out.println("2秒后：" + object);
    }
}
