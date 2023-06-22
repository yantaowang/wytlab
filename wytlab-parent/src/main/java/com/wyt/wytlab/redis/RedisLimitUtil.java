package com.wyt.wytlab.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLimitUtil {
    private static final String SEND_NUM_KEY_PATTERN = "putin:abc";
    private static final int WINDOW_DURATION = 10;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 滑动窗口 实现限流
     * @param accessToken 钉钉token
     * @return 统计信息
     */
    public boolean slidingWindowsTryAcquire(String accessToken) {
        String redisKey = String.format(SEND_NUM_KEY_PATTERN, accessToken);
        // 获取当前时间
        long ts = System.currentTimeMillis();
        // 窗口数据写入
        redisTemplate.opsForZSet().add(redisKey, String.valueOf(ts), (double)ts);
        // 移除滑动窗口之外的数据
        redisTemplate.opsForZSet().removeRangeByScore(redisKey, 0, ts - WINDOW_DURATION * 1000);
        // 窗口内数据统计
        int count = redisTemplate.opsForZSet().zCard(redisKey).intValue();
        // 设置窗口key的过期时间，如果数据为冷数据，redis将会删除该key节省内存空间
        redisTemplate.expire(redisKey, WINDOW_DURATION, TimeUnit.SECONDS);
        return count <= 10;
    }
}
