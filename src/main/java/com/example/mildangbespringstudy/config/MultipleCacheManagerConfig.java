package com.example.mildangbespringstudy.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableCaching
public class MultipleCacheManagerConfig implements CachingConfigurer {

    private final CacheManager ehCacheManager;
    private final CacheManager redissonCacheManager;

    public MultipleCacheManagerConfig(@Qualifier("ehCacheManager") @Lazy CacheManager ehCacheManager,
                                      @Qualifier("redissonCacheManager") @Lazy CacheManager redissonCacheManager) {
        this.ehCacheManager = ehCacheManager;
        this.redissonCacheManager = redissonCacheManager;
    }

    @Lazy
    @Bean
    public CacheResolver cacheResolver() {
        return new CustomCacheResolver(ehCacheManager, redissonCacheManager);
    }
}
