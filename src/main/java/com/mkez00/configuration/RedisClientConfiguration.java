package com.mkez00.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
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

    Jedis jedis = null;

    @PostConstruct
    public void init(){
        if (redisUrl==null || redisUrl.isEmpty() || redisUrl==null || redisUrl.isEmpty() || redisPort==null || redisPort.isEmpty()){
            return;
        }
        LOG.info("Connected to Redis server: " + redisUrl + ":" + redisPort);
        Integer redisPortInt = 0;
        try {
            redisPortInt = Integer.parseInt(redisPort);
            jedis = new Jedis(redisUrl, redisPortInt);
        } catch (NumberFormatException e){

        }
    }

    public Jedis getRedisClient(){
        this.jedis.connect();
        return this.jedis;
    }
}
