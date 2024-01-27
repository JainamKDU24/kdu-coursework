package com.caching.config;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.CacheManager;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

/**
 * Configuration class for setting up caching with Caffeine.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Configures the Caffeine cache with maximum size and expiration settings.
     *
     * @return The configured Caffeine instance.
     */
    @Bean
    public @NonNull Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .maximumSize(5)  // after it reaches the maxsize it will evict in LRU fashion
                .expireAfterAccess(60, TimeUnit.MINUTES); // time based eviction
    }

    /**
     * Creates a CaffeineCacheManager bean to manage caches using Caffeine.
     *
     * @return The configured CacheManager.
     */
    @Bean("caffeineCacheManager")
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeineConfig());
        caffeineCacheManager.setCacheNames(asList("geocoding", "reverse-geocoding"));
        return caffeineCacheManager;
    }
}
