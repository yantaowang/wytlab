package com.wyt.wytlab.java;

import com.github.benmanes.caffeine.cache.*;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CaffeineTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        demo3();
//        quzhu();
//        weight();
        demo7();
    }

    public static void demo7(){
        LoadingCache<Integer,String> cache = Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .recordStats()
                .build(key -> {
                    if(key % 6 == 0 ){
                        return null;
                    }
                    return  UUID.randomUUID().toString();
                });

        for (int i = 0; i < 20; i++) {
            cache.get(i);
            printStats(cache.stats());
        }
        for (int i = 0; i < 10; i++) {
            cache.get(i);
            printStats(cache.stats());
        }
    }

    private static void printStats(CacheStats stats){
        System.out.println("---------------------");
        System.out.println("stats.hitCount():"+stats.hitCount());//命中次数
        System.out.println("stats.hitRate():"+stats.hitRate());//缓存命中率
        System.out.println("stats.missCount():"+stats.missCount());//未命中次数
        System.out.println("stats.missRate():"+stats.missRate());//未命中率
        System.out.println("stats.loadSuccessCount():"+stats.loadSuccessCount());//加载成功的次数
        System.out.println("stats.loadFailureCount():"+stats.loadFailureCount());//加载失败的次数,返回null
        System.out.println("stats.loadFailureRate():"+stats.loadFailureRate());//加载失败的百分比
        System.out.println("stats.totalLoadTime():"+stats.totalLoadTime());//总加载时间,单位ns
        System.out.println("stats.evictionCount():"+stats.evictionCount());//驱逐次数
        System.out.println("stats.evictionWeight():"+stats.evictionWeight());//驱逐的weight值总和
        System.out.println("stats.requestCount():"+stats.requestCount());//请求次数
        System.out.println("stats.averageLoadPenalty():"+stats.averageLoadPenalty());//单次load平均耗时
    }


    public static void demo6(){
        LoadingCache<String,String> cache = Caffeine.newBuilder()
                .maximumSize(5)
                .recordStats()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .removalListener((String key, String value, RemovalCause cause) -> {
                    System.out.printf("Key %s was removed (%s)%n", key, cause);
                })
                .build(key -> UUID.randomUUID().toString());
        for (int i = 0; i < 15; i++) {
            cache.get(i+"");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }

        //因为evict是异步线程去执行，为了看到效果稍微停顿一下
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public static void demo5(){
        /**
         * 使用软引用存储value,GC内存不够时会回收
         */
        LoadingCache<String,String> cache = Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .softValues()//注意没有softKeys方法
                .build(k -> UUID.randomUUID().toString());
    }


    public static void weight() {
        LoadingCache<Integer,String> cache = Caffeine.newBuilder()
                .maximumWeight(300)
                .recordStats()
                .weigher((Weigher<Integer, String>) (key, value) -> {
                    if(key % 2 == 0){
                        return 2;
                    }
                    return 1;
                })
                .build( k -> UUID.randomUUID().toString());

        for (int i = 0; i < 300; i++) {
            cache.get(i);
            if(i> 200){
                System.out.println(cache.stats().toString());
            }
        }
    }

    public static void quzhu() {
        LoadingCache<String,String> cache = Caffeine.newBuilder()
                .maximumSize(500)
                .recordStats()
                .build( k -> UUID.randomUUID().toString());

        for (int i = 0; i < 600; i++) {
            cache.get(String.valueOf(i));
            if(i> 500){
                CacheStats stats = cache.stats();
                System.out.println("evictionCount:"+stats.evictionCount());
                System.out.println("stats:"+stats.toString());
            }
        }

    }

    public static void demo3() throws ExecutionException, InterruptedException {
        AsyncLoadingCache<String,String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(500)
                .buildAsync(k -> createExpensiveGraph(k));
        CompletableFuture<String> future = cache.get("hello");
        System.out.println(future.get());
    }


    public static void demo2() throws ExecutionException, InterruptedException {
        AsyncCache<String,String> cache = Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .buildAsync();

        // Lookup and asynchronously compute an entry if absent
        CompletableFuture<String> future = cache.get("hello", k -> createExpensiveGraph(k));
        System.out.println(future.get());
    }


    private static void demo1() {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .maximumSize(500)
                .build(new CacheLoader<String, String>() {

                    @Override
                    public String load(String key) throws Exception {
                        return createExpensiveGraph(key);
                    }

                    @Override
                    public Map<String, String>  loadAll(Iterable<? extends String> keys) {
                        System.out.println("build keys");
                        Map<String,String> map = new HashMap<>();
                        for(String k : keys){
                            map.put(k,k+"-val");
                        }
                        return map;
                    }
                });

        String val1 = cache.get("hello");
        Map<String,String> values = cache.getAll(Lists.newArrayList("key1", "key2"));

    }


    public static void demo(){
        Cache<String,String> cache = Caffeine.newBuilder()
                .expireAfterWrite(20, TimeUnit.SECONDS)
                .maximumSize(5000)
                .build();

        // 1.Insert or update an entry
        cache.put("hello","world");

        // 2. Lookup an entry, or null if not found
        String val1 = cache.getIfPresent("hello");

        // 3. Lookup and compute an entry if absent, or null if not computable
        cache.get("msg", CaffeineTest::createExpensiveGraph);

        String v = cache.getIfPresent("msg");

        // 4. Remove an entry
        cache.invalidate("hello");
    }

    private static String createExpensiveGraph(String key){
        System.out.println("begin to query db..."+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("success to query db...");
        return UUID.randomUUID().toString();
    }

}
