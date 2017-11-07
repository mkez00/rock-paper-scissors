package com.mkez00.service;

import com.mkez00.helper.GeneralHelper;
import com.mkez00.model.Action;
import com.mkez00.repository.ActionRepository;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by michaelkezele on 2017-10-30.
 */
@Service
public class ServerProcessServiceImpl implements ServerProcessService {
    private static final Logger LOG = Logger.getLogger( ServerProcessServiceImpl.class.getName() );

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    ServletContext servletContext;

    @Override
    @Async
    public void wakeup() {
         if (!isProcessing()){
             setProcessing(true);
             for (int x = 0; x <=20; x++) {
                 processActions();
                 GeneralHelper.sleep(500);
             }
             setProcessing(false);
         }
    }

    private void setProcessing(boolean processing){
        servletContext.setAttribute("processing", processing);
    }

    private boolean isProcessing(){
        if (servletContext.getAttribute("processing")==null ||
                !Boolean.valueOf(servletContext.getAttribute("processing").toString())){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public void processActions() {
        LOG.info("processActions");
        List<Action> actions = actionRepository.findAll();
        if (actions!=null){
            LOG.info("Action list has entries: " + actions.size());
            for (Action action : actions){
                if (action.getResponse()==null || action.getResponse().equals(Action.ResponseEnum.NIL)){
                    LOG.info("Action is not NIL");
                    Action match = findClosestMatch(action, actions);
                    if (match!=null){
                        LOG.info("Found match, processing");
                        processAction(action, match);
                    }
                }
            }
        }
    }

    @Override
    public void processAction(Action action1, Action action2) {
        LOG.info("processAction");
        if (action1!=null && action2!=null && action1.getAction()!=null && action2.getAction()!=null){
            action1.setResponse(result(action1.getAction(), action2.getAction()));
            action2.setResponse(result(action2.getAction(), action1.getAction()));
            actionRepository.put(action1);
            actionRepository.put(action2);
        }
    }

    @Override
    public Action findClosestMatch(Action actionIn, List<Action> actionList) {
        LOG.info("findClosestMatch: " + actionIn.getId());
        if (actionList!=null){
            LOG.info("Action list has values");
            double lat1 = Double.valueOf(actionIn.getLatitude());
            double lng1 = Double.valueOf(actionIn.getLongitude());
            for (Action action : actionList){
                LOG.info("Action: " + action.getId());
                if (GeneralHelper.absoluteMillisecondsBetweenDates(actionIn.getActionDate(), action.getActionDate())<=10000){
                    LOG.info("Less than 10 seconds between requests");
                    if (action.getResponse()==null || action.getResponse().equals(Action.ResponseEnum.NIL)) {
                        LOG.info("Only process actions with NIL or empty responses");
                        if (!action.getId().equals(actionIn.getId())){
                            LOG.info("Do not process against own action");
                            double lat2 = Double.valueOf(action.getLatitude());
                            double lng2 = Double.valueOf(action.getLongitude());
                            double calculated = GeneralHelper.distanceBetweenGeoPoints(lat1, lng1, lat2, lng2);
                            if (calculated<1){
                                LOG.info("Actions are within 1km of one another: " + calculated);
                                return action;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Action.ResponseEnum result(Action.ActionEnum action, Action.ActionEnum compare){
        switch (action) {
            case ROCK:
                switch (compare){
                    case ROCK:
                        return Action.ResponseEnum.TIE;
                    case PAPER:
                        return Action.ResponseEnum.LOSER;
                    case SCISSORS:
                        return Action.ResponseEnum.WINNER;
                }
                break;
            case PAPER:
                switch (compare){
                    case ROCK:
                        return Action.ResponseEnum.WINNER;
                    case PAPER:
                        return Action.ResponseEnum.TIE;
                    case SCISSORS:
                        return Action.ResponseEnum.LOSER;
                }
                break;
            case SCISSORS:
                switch (compare){
                    case ROCK:
                        return Action.ResponseEnum.LOSER;
                    case PAPER:
                        return Action.ResponseEnum.WINNER;
                    case SCISSORS:
                        return Action.ResponseEnum.TIE;
                }
                break;
        }
        return null;
    }

    @Override
    @Scheduled(fixedRate = 20000)
    public void purgeStaleGames() {
        LOG.info("purgeStaleGames");
        List<Action> actions = actionRepository.findAll();
        if (actions!=null){
            for (Action action : actions){
                if (action.getActionDate()<GeneralHelper.addSecondsToCurrentTime(-20)){
                    actionRepository.delete(action.getId());
                }
            }
        }
    }
}
