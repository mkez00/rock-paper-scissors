package com.mkez00.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Created by michaelkezele on 2017-10-30.
 */
@Configuration
public class RedisClientConfiguration {

    @Value("${redis.url}")
    String redisUrl;

    @Value("${redis.port}")
    Integer redisPort;

    Jedis jedis = null;

    public RedisClientConfiguration(){
        if (redisUrl==null || redisUrl.isEmpty() || redisPort==null){
            return;
        }
        jedis = new Jedis(redisUrl, redisPort);
    }

    public Jedis getRedisClient(){
        return this.jedis;
    }
}
