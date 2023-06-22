package com.wyt.wytlab;

import com.wyt.wytlab.redis.RedisLimitUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class RedisLimitTest {

    @Autowired
    private RedisLimitUtil redisLimitUtil;

    @Test
    public void limitTest() {
        for (int i = 0; i < 20; i++) {
            boolean c = redisLimitUtil.slidingWindowsTryAcquire("aaa");
            System.out.printf("start: %s, rest:%s%n", i, c);
        }
    }
}
