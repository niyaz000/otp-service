package io.channelapi.sms_service.client;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPool;

@Component
@RequiredArgsConstructor
public class RedisClient {

    private final JedisPool jedisPool;

    public void setWithExpiry(String key, String value, long expiryInSeconds) {
        try (var jedis = jedisPool.getResource()) {
            jedis.setex(key, expiryInSeconds, value);
        }
    }

    public boolean setIfNotExists(String key, String value, long expiryInSeconds) {
        try (var jedis = jedisPool.getResource()) {
            return "OK".equals(jedis.set(key, value, new redis.clients.jedis.params.SetParams().nx().ex(expiryInSeconds)));
        }
    }

    public boolean exists(String key) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    public long ttl(String key) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.ttl(key);
        }
    }

    public String get(String key) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    public void delete(String key) {
        try (var jedis = jedisPool.getResource()) {
            jedis.del(key);
        }
    }
}
