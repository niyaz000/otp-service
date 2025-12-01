package io.channelapi.sms_service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisPool jedisPool(
            @Value("${spring.redis.host}") String redisHost,
            @Value("${spring.redis.port}") int redisPort,
            @Value("${spring.redis.password:}") String redisPassword,
            @Value("${spring.redis.timeout:5000}") int timeout,
            @Value("${spring.redis.pool.max-active:30}") int maxActive,
            @Value("${spring.redis.pool.max-idle:20}") int maxIdle,
            @Value("${spring.redis.pool.min-idle:10}") int minIdle,
            @Value("${spring.redis.pool.max-wait:1000}") long maxWait) {
        
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWait(Duration.ofMillis(maxWait));
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleDuration(Duration.ofSeconds(60));
        poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(30));
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        
        // Disable JMX to avoid MBean registration issues
        poolConfig.setJmxEnabled(false);
        
        // Create JedisPool with or without password
        if (redisPassword != null && !redisPassword.isEmpty()) {
            return new JedisPool(poolConfig, redisHost, redisPort, timeout, redisPassword);
        } else {
            return new JedisPool(poolConfig, redisHost, redisPort, timeout);
        }
    }
}
