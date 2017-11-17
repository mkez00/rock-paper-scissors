package com.mkez00.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkez00.configuration.RedisClientConfiguration;
import com.mkez00.helper.GeneralHelper;
import com.mkez00.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by michaelkezele on 2017-11-10.
 */
@Service("ActionRepositoryRedis")
public class ActionRepositoryRedis implements ActionRepository {
    private static final Logger LOG = Logger.getLogger( RedisClientConfiguration.class.getName() );

    @Autowired
    RedisClientConfiguration redisClientConfiguration;

    @Override
    public Action findByKey(String id) {
        LOG.info("findByKey");
        try (Jedis jedis = redisClientConfiguration.getRedisPool().getResource()) {
            return GeneralHelper.deserialize(jedis.get(id));
        }
    }

    @Override
    public void put(Action action) {
        LOG.info("put");
        try (Jedis jedis = redisClientConfiguration.getRedisPool().getResource()) {
            jedis.set(action.getId(), GeneralHelper.serialize(action));
        }
    }

    @Override
    public void delete(String id) {
        LOG.info("delete");
        try (Jedis jedis = redisClientConfiguration.getRedisPool().getResource()) {
            jedis.del(id);
        }
    }

    @Override
    public List<Action> findAll() {
        LOG.info("findAll");
        List<Action> actions = new ArrayList<>();
        try (Jedis jedis = redisClientConfiguration.getRedisPool().getResource()) {
            Set<String> ids = jedis.keys("*");
            if (ids!=null){
                for (String id : ids){
                    try (Jedis jedis2 = redisClientConfiguration.getRedisPool().getResource()) {
                        actions.add(GeneralHelper.deserialize(jedis2.get(id)));
                    }
                }
            }
            return actions;
        }
    }
}
