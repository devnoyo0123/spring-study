package com.example.mildangbespringstudy.chap02.controller;


import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import lombok.RequiredArgsConstructor;
import org.ehcache.core.Ehcache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RestController
public class InMemoryController {

//    private final CacheManager cacheManager;

//    public InMemoryController(CacheManager cacheManager) {
//        this.cacheManager = cacheManager;
//    }

//    @GetMapping("/in-memory")
//    public Object findAll() {
//        List<Map<String, List<String>>> result = cacheManager.getCacheNames().stream()
//                .map((cacheName) -> {
//                    Cache cache = cacheManager.getCache(cacheName);
//                    ConcurrentMapCache nativeCache = (ConcurrentMapCache) cacheManager.getCache(cacheName);
//                    ConcurrentMap<Object, Object> cacheMap = nativeCache.getNativeCache();
//                    Map<String, List<String>> entry = new HashMap<>();
//
//                    cacheMap.keySet().forEach((key) -> {
//                        List<MemberV2> element = (List<MemberV2>) cacheMap.get(key);
//                        if(element != null) {
//                            String membersString = element.stream().map(MemberV2::toString).collect(Collectors.joining(","));
//                            entry.computeIfAbsent(cacheName, (k) -> new ArrayList<>()).add(membersString);
//                        }
//                    });
//
//                    return entry;
//                })
//                .collect(Collectors.toList());
//        return result;
//    }

//    @GetMapping("/ehcache")
//    public Object findAll() {
//        List<Map<String, List<String>>> result = cacheManager.getCacheNames().stream()
//                .map(cacheName -> {
//                    JCacheCache cache = (JCacheCache) cacheManager.getCache(cacheName);
//                    Map<String, List<String>> jCacheDto = new HashMap<>();
//                    cache.getNativeCache().iterator().forEachRemaining((entry) -> {
//                        System.out.println(entry.getKey() + " : " + entry.getValue());
//                        jCacheDto.put(entry.getKey().toString(), (List<String>) entry.getValue());
//                    });
//                    return jCacheDto;
//                })
//                .collect(Collectors.toList());
//
//        return result;
//    }
}
