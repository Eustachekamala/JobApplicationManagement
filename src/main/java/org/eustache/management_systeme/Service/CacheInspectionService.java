package org.eustache.management_systeme.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheInspectionService {
    @Autowired
    private CacheManager cacheManager;

    public void printCacheContents(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            var nativeCache = cache.getNativeCache();
            System.out.println("Cache contents for '" + cacheName + "': " + nativeCache);
        } else {
            System.out.println("Cache '" + cacheName + "' not found.");
        }
    }
}
