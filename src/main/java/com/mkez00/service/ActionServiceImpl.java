package com.mkez00.service;

import com.mkez00.helper.GeneralHelper;
import com.mkez00.model.Action;
import com.mkez00.repository.ActionRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by michaelkezele on 2017-10-25.
 */
@Service
public class ActionServiceImpl implements ActionService {
    private static final Logger LOG = Logger.getLogger( ActionServiceImpl.class.getName() );

    @Autowired
    ActionRepository actionRepository;

    @Override
    public Action process(Action action) {
        LOG.info("process");
        int iterations = 0;
        action = this.findById(action.getId());
        while (!actionProcessed(action, iterations)){
            GeneralHelper.sleep(1000);
            iterations++;
            action = this.findById(action.getId());
        }
        action = postProcess(action);
        LOG.info("Returning");
        return action;
    }

    private Action postProcess(Action action){
        if (!actionProcessed(action)){
            action.setResponse(Action.ResponseEnum.NOOPPONENT);
            return this.save(action);
        }
        return action;
    }

    private boolean actionProcessed(Action action) {
        if (action.getResponse()!=null){
            return true;
        } else {
            return false;
        }
    }

    private boolean actionProcessed(Action action, int iterations){
        if (iterations>=10){
            return true;
        }
        return this.actionProcessed(action);
    }

    @Override
    public Action findById(String id) {
        return actionRepository.findByKey(id);
    }



    @Override
    public Action save(Action action) {
        if (action.getId()==null || action.getId().isEmpty()) action.setId(UUID.randomUUID().toString());
        if (action.getActionDate()==null) action.setActionDate(GeneralHelper.getCurrentTime());
        actionRepository.put(action);
        return action;
    }

    @Override
    public List<Action> findAll() {
        return actionRepository.findAll();
    }
}
