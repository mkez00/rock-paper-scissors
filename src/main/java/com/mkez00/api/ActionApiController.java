package com.mkez00.api;

import com.mkez00.helper.GeneralHelper;
import com.mkez00.model.Action;

import com.mkez00.service.ActionService;
import com.mkez00.service.ActionServiceImpl;
import com.mkez00.service.ServerProcessService;
import com.mkez00.service.ServerProcessServiceImpl;
import io.swagger.annotations.*;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.constraints.*;
import javax.validation.Valid;

@RestController(value="/rest/v1/action")
public class ActionApiController {
    private static final Logger LOG = Logger.getLogger( ActionApiController.class.getName() );

    @Autowired
    ActionService actionService;

    @RequestMapping(method = RequestMethod.POST, consumes="application/json",produces = "application/json")
    public Action actionPost(@RequestBody Action body) {
        LOG.info("actionPost");
        body = actionService.save(body);
        body = actionService.process(body);
        return body;
    }

    @RequestMapping(method = RequestMethod.GET, consumes="application/json",produces = "application/json")
    public Action actionGet(@RequestParam("id") String id) {
        return actionService.findById(id);
    }

}
