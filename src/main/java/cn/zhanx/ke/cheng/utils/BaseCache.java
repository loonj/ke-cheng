package cn.zhanx.ke.cheng.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Data
public class BaseCache {

    private Cache<String,Object> tenMinuteCache= CacheBuilder.newBuilder()
            //设置缓存初始大小，应该合理设置，后续会扩容
            .initialCapacity(10)
            .maximumSize(100)
            //同时5个线程可以写
            .concurrencyLevel(5)
            //过期时间，10分钟后过期
            .expireAfterWrite(600, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .build();

    private Cache<String,Object> oneHourCache= CacheBuilder.newBuilder()
            //设置缓存初始大小，应该合理设置，后续会扩容
            .initialCapacity(10)
            .maximumSize(100)
            //同时5个线程可以写
            .concurrencyLevel(5)
            //过期时间，10分钟后过期 ,视频想起一般很少改动
            .expireAfterWrite(3600, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .build();
}
