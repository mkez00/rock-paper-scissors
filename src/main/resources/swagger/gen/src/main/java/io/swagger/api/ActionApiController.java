package io.swagger.api;

import io.swagger.model.Action;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-25T22:11:07.354-04:00")

@Controller
public class ActionApiController implements ActionApi {



    public ResponseEntity<Action> actionPost(@ApiParam(value = "The action performed by the player" ,required=true )  @Valid @RequestBody Action body) {
        // do some magic!
        return new ResponseEntity<Action>(HttpStatus.OK);
    }

}
