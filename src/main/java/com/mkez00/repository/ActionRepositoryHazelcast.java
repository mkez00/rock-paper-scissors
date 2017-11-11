package com.mkez00.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.mkez00.configuration.HazelcastConfiguration;
import com.mkez00.helper.GeneralHelper;
import com.mkez00.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by michaelkezele on 2017-10-25.
 */
@Service("ActionRepositoryHazelcast")
public class ActionRepositoryHazelcast implements ActionRepository {

    @Autowired
    HazelcastConfiguration hazelcastConfiguration;

    @Override
    public Action findByKey(String id) {
        return GeneralHelper.deserialize(getMap().get(id));
    }

    @Override
    public void put(Action action) {
        getMap().put(action.getId(), GeneralHelper.serialize(action));
    }

    @Override
    public void delete(String id) {
        getMap().remove(id);
    }

    @Override
    public List<Action> findAll() {
        List<Action> actions = new ArrayList<>();
        if (getMap()!=null){
            for (Map.Entry<String,String> entry : getMap().entrySet()){
                actions.add(GeneralHelper.deserialize(entry.getValue()));
            }
        }
        return actions;
    }

    private Map<String,String> getMap(){
        return hazelcastConfiguration.getHazelcast().getMap("actions");
    }
}
