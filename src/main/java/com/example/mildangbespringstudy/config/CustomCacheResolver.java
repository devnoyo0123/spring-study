package com.example.mildangbespringstudy.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.Collection;
import java.util.Collections;

public class CustomCacheResolver implements CacheResolver {

    private final CacheManager ehCacheManager;
    private final CacheManager redissonCacheManager;

    public CustomCacheResolver(CacheManager ehCacheManager,
                               CacheManager redissonCacheManager) {
        this.ehCacheManager = ehCacheManager;
        this.redissonCacheManager = redissonCacheManager;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        // 캐시 이름을 기준으로 캐시 매니저를 선택
        String cacheName = context.getOperation().getCacheNames().iterator().next();
        if ("localCache".equals(cacheName)) {
            return Collections.singleton(ehCacheManager.getCache(cacheName));
        } else if ("globalCache".equals(cacheName)) {
            return Collections.singleton(redissonCacheManager.getCache(cacheName));
        }
        return Collections.emptyList();
    }
}