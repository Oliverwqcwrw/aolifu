package com.aolifu.caffeine;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    @Test
    public void manual() {
        Cache<String, String> cache = Caffeine.newBuilder()
                //5秒没有读写自动删除
                .expireAfterAccess(2, TimeUnit.SECONDS)
                //最大容量1024个，超过会自动清理空间
                .maximumSize(1024)
                .removalListener(((key, value, cause) -> {
                    //清理通知 key,value ==> 键值对   cause ==> 清理原因
                    System.out.println("清理旧数据: " + key);
                }))
                .build();
    
        //添加值
        cache.put("张三", "浙江");
        cache.put("李四","北京");
        //remove
        cache.invalidate("张三");
        System.out.println(cache.getIfPresent("李四"));
    }
    
    @Test
    public void population() {
        Cache<String, Integer> cache = Caffeine.newBuilder().build();
        // cache.put("张三",20);
    
        Integer age1 = cache.getIfPresent("张三");
        System.out.println(age1);
    
        //当key不存在时，会立即创建出对象来返回，age2不会为空
        Integer age2 = cache.get("张三", k -> {
            System.out.println("k:" + k);
            return 18;
        });
        System.out.println(age2);
    }
    
    @Test
    public void  autoLoading() {
        //此时的类型是 LoadingCache 不是 Cache
        LoadingCache<String, Integer> cache = Caffeine.newBuilder().build(key -> {
            System.out.println("自动填充:" + key);
            return 18;
        });
    
        Integer age1 = cache.getIfPresent("张三");
        System.out.println(age1);
    
        // key 不存在时 会根据给定的CacheLoader自动装载进去
        Integer age2 = cache.get("张三");
        System.out.println(age2);
    
    }
    
    @Test
    public void asyncManual() throws ExecutionException, InterruptedException {
        AsyncCache<String, Integer> cache = Caffeine.newBuilder().buildAsync();
    
        //会返回一个 future对象， 调用future对象的get方法会一直卡住直到得到返回，和多线程的submit一样
        CompletableFuture<Integer> ageFuture = cache.get("张三", name -> {
            System.out.println("name:" + name);
            return 18;
        });
    
        Integer age = ageFuture.get();
        System.out.println("age:" + age);
    }
    
    @Test
    public void asyncLoading() throws ExecutionException, InterruptedException {
        //和1.4基本差不多
        AsyncLoadingCache<String, Integer> cache = Caffeine.newBuilder().buildAsync(name -> {
            System.out.println("name:" + name);
            return 18;
        });
        CompletableFuture<Integer> ageFuture = cache.get("张三");
    
        Integer age = ageFuture.get();
        System.out.println("age:" + age);
    }
  }
