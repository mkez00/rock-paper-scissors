package com.mkez00.repository;

import com.mkez00.configuration.RedisClientConfiguration;
import com.mkez00.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by michaelkezele on 2017-11-10.
 */
@Service("ActionRepositoryRedis")
public class ActionRepositoryRedis implements ActionRepository {

    @Autowired
    RedisClientConfiguration redisClientConfiguration;

    @Override
    public Action findByKey(String id) {
        return null;
    }

    @Override
    public void put(Action action) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Action> findAll() {
        return null;
    }
}
