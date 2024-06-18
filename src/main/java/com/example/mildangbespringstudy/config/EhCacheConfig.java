package com.example.mildangbespringstudy.config;

import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

@Configuration
public class EhCacheConfig {

    @Bean
    public org.springframework.cache.CacheManager ehCacheManager() {
        // EhcacheCachingProvider를 사용하여 CachingProvider를 가져옵니다.
        CachingProvider cachingProvider = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
        javax.cache.CacheManager jCacheManager = cachingProvider.getCacheManager();
        javax.cache.configuration.Configuration<Object, Object> configuration = new MutableConfiguration<>()
                .setTypes(Object.class, Object.class)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE))
                .setStoreByValue(false)
                .setStatisticsEnabled(true);
        jCacheManager.createCache("localCache", configuration);

        // Spring의 CacheManager로 래핑합니다.
        return new JCacheCacheManager(jCacheManager);
    }
}
