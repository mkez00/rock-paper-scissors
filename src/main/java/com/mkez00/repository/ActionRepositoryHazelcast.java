package com.mkez00.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.mkez00.configuration.HazelcastConfiguration;
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
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(getMap().get(id), Action.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(Action action) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            getMap().put(action.getId(),objectMapper.writeValueAsString(action));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        getMap().remove(id);
    }

    @Override
    public List<Action> findAll() {
        List<Action> actions = new ArrayList<>();
        if (getMap()!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            for (Map.Entry<String,String> entry : getMap().entrySet()){
                try {
                    actions.add(objectMapper.readValue(entry.getValue(), Action.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return actions;
    }

    private Map<String,String> getMap(){
        return hazelcastConfiguration.getHazelcast().getMap("actions");
    }
}
