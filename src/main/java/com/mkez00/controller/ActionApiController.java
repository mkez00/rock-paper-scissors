package com.mkez00.controller;

import com.mkez00.model.Action;
import com.mkez00.service.ActionService;
import com.mkez00.service.ServerProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class ActionApiController {
    private static final Logger LOG = Logger.getLogger( ActionApiController.class.getName() );

    @Autowired
    ActionService actionService;

    @Autowired
    ServerProcessService serverProcessService;

    @RequestMapping(value="/rest/v1/action", method = RequestMethod.POST, consumes="application/json",produces = "application/json")
    public Action actionPost(@RequestBody Action body) {
        LOG.info("actionPost");
        serverProcessService.wakeup();
        body = actionService.save(body);
        body = actionService.process(body);
        return body;
    }

    @RequestMapping(value="/rest/v1/action", method = RequestMethod.GET, consumes="application/json",produces = "application/json")
    public Action actionGet(@RequestParam("id") String id) {
        return actionService.findById(id);
    }

}
