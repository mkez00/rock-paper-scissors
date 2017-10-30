package com.mkez00.service;

import com.mkez00.model.Action;

import java.util.List;

/**
 * Created by michaelkezele on 2017-10-30.
 */
public interface ServerProcessService {

    void processActions();

    void purgeStaleGames();

    void processAction(Action action1, Action action2);

    Action findClosestMatch(Action actionIn, List<Action> actionList);

    Action.ResponseEnum result(Action.ActionEnum action, Action.ActionEnum compare);
}
