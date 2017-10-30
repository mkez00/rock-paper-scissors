package com.mkez00.service;

import com.mkez00.model.Action;

import java.util.List;

/**
 * Created by michaelkezele on 2017-10-25.
 */
public interface ActionService {

    Action save(Action action);

    Action findById(String id);

    Action process(Action action);

    List<Action> findAll();
}
