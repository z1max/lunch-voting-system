package com.z1max.lunchvotingsystem.controller.user;

import com.z1max.lunchvotingsystem.model.User;
import com.z1max.lunchvotingsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProfileUserController.REST_URL)
public class ProfileUserController extends AbstractUserController{

    static final String REST_URL = "api/profile";

    public ProfileUserController(UserService userService) {
        super(userService);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(){
        //TODO
        return null;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(){
        //TODO
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(){
        //TODO
    }

}
