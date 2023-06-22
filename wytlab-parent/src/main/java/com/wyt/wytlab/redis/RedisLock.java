//package com.wyt.wytlab.redis;
//
//import com.googlecode.aviator.runtime.function.system.DecimalFunction;
//import com.sun.org.apache.regexp.internal.RE;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.params.SetParams;
//
//import java.util.Collections;
//
//public class RedisLock {
//    private static final String SET_IF_NOT_EXIST = "NX";
//    private static final String SET_WITH_EXPIRE_TIME = "PX";
//    private static final String LOCK_PREFIX = "dlock_";
//    private static final String LOCK_MSG = "OK";
//    private static final Long UNLOCK_MSG = 1L;
//    private static final int DEFAULT_EXPIRE_TIME = 1000;
//    private static final long DEFAULT_SLEEP_TIME = 100;
//
//    private JedisPool jedisPool;
//
//    public RedisLock(JedisPool jedisPool) {
//        this.jedisPool = jedisPool;
//    }
//
//
//    private boolean setLockToRedis(String key, String value, Jedis jedis) throws InterruptedException {
//        SetParams setParams = new SetParams();
//        setParams.nx().px(DEFAULT_EXPIRE_TIME);
//        String result = jedis.set(LOCK_PREFIX + key, value,SetParams.setParams().nx().px(DEFAULT_EXPIRE_TIME));
//        if(LOCK_MSG.equals(result)) {
//            jedis.close();
//            System.out.println(Thread.currentThread().getName() + "分布式锁成功");
//            return true;
//        }
//        Thread.sleep(DEFAULT_EXPIRE_TIME);
//        return false;
//    }
//
//    public void lock(String key, String value) throws InterruptedException {
//        Jedis jedis = jedisPool.getResource();
//        while (true) {
//            if (setLockToRedis(key, value, jedis)) return;
//        }
//    }
//
//    public void lock(String key,String value, int timeout) throws InterruptedException {
//        Jedis jedis = jedisPool.getResource();
//        while (timeout >= 0){
//            if(setLockToRedis(key,value, jedis)) {
//                System.out.println(Thread.currentThread().getName() + "执行redis分布式锁成功");
//                return;
//            }
//            System.out.println(Thread.currentThread().getName() + "执行redis分布式锁失败，重试");
//            timeout -= DEFAULT_SLEEP_TIME;
//        }
//    }
//
//    public boolean tryLock(String key, String value) throws InterruptedException {
//        Jedis jedis = jedisPool.getResource();
//        return setLockToRedis(key, value, jedis);
//    }
//
//    public boolean unlock(String key, String value) {
//        Jedis jedis = jedisPool.getResource();
//
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        Object result = jedis.eval(script, Collections.singletonList(LOCK_PREFIX + key), Collections.singletonList(value));
//
//        jedis.close();
//        return UNLOCK_MSG.equals(result);
//    }
//
//    public boolean isLocked(String key) {
//        Jedis jedis = jedisPool.getResource();
//        String result = jedis.get(LOCK_PREFIX + key);
//        jedis.close();
//        return result != null;
//    }
//
//    public void flushAll() {
//        Jedis jedis = jedisPool.getResource();
//        jedis.flushAll();
//    }
//
//}
