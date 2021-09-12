package com.wyt.wytlab.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisPoolFactory {

    @Bean
    public RedisLock redisLock() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(5);
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxWaitMillis(10 * 1000);
        return new RedisLock(new JedisPool(poolConfig, "127.0.0.1", 6379,
                10 * 1000, "test123", 0));
    }
}
