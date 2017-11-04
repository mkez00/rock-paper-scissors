package com.mkez00.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

/**
 * Created by michaelkezele on 2017-11-04.
 */

@Controller
public class HomeController {
    private static final Logger LOG = Logger.getLogger( HomeController.class.getName() );

    @RequestMapping(value="/", produces = "text/html", method = RequestMethod.GET)
    public String home(){
        LOG.info("home");
        return "index.html";
    }
}
