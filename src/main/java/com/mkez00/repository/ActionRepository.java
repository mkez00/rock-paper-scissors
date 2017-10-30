package com.mkez00.repository;

import com.mkez00.model.Action;

import java.util.List;

/**
 * Created by michaelkezele on 2017-10-25.
 */
public interface ActionRepository {
    Action findByKey(String id);

    void put(Action action);

    void delete(String id);

    List<Action> findAll();
}
