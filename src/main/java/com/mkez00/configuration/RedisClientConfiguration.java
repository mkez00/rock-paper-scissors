package com.mkez00.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * Created by michaelkezele on 2017-10-30.
 */
@Configuration
public class RedisClientConfiguration {
    private static final Logger LOG = Logger.getLogger( RedisClientConfiguration.class.getName() );

    @Value("${redis.url}")
    String redisUrl;

    @Value("${redis.port}")
    String redisPort;

    JedisPool jedisPool = null;

    @PostConstruct
    public void init(){
        if (redisUrl==null || redisUrl.isEmpty() || redisUrl==null || redisUrl.isEmpty() || redisPort==null || redisPort.isEmpty()){
            return;
        }
        LOG.info("Connected to Redis server: " + redisUrl + ":" + redisPort);
        Integer redisPortInt = 0;
        try {
            redisPortInt = Integer.parseInt(redisPort);

            final JedisPoolConfig poolConfig = buildPoolConfig();
            jedisPool = new JedisPool(poolConfig, redisUrl, redisPortInt);

        } catch (NumberFormatException e){

        }
    }

    public JedisPool getRedisPool(){
        return jedisPool;
    }

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
