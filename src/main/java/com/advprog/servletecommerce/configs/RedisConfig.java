package com.advprog.servletecommerce.configs;


import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.RedisClient;

public class RedisConfig {
    private static RedisClient redisClient;

    public static RedisClient getRedisClient() {
        if (redisClient == null) {
            ConnectionPoolConfig poolConfig = new ConnectionPoolConfig();
            poolConfig.setMaxTotal(10);

            redisClient = RedisClient.builder()
                    .hostAndPort("localhost", 6379)
                    .poolConfig(poolConfig)
                    .build();
        }
        return redisClient;
    }

    public static void close() {
        if (redisClient != null) {
            redisClient.close();
        }
    }
}
