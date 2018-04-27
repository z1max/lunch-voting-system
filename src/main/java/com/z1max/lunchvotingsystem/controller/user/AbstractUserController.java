package com.z1max.lunchvotingsystem.controller.user;

import com.z1max.lunchvotingsystem.model.User;
import com.z1max.lunchvotingsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.z1max.lunchvotingsystem.util.Util.assureIdConsistent;
import static com.z1max.lunchvotingsystem.util.Util.checkNew;

public abstract class AbstractUserController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @Autowired
    public AbstractUserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAll(){
        logger.info("Get all");
        return userService.getAll();
    }

    public User get(int id){
        logger.info("Get by id = {}", id);
        return userService.get(id);
    }

    public User getByEmail(String email){
        logger.info("Get by email = {}", email);
        return userService.getByEmail(email);
    }

    public User create(User user){
        logger.info("Create {}", user);
        checkNew(user);
        return userService.create(user);
    }

    public void update(User user, int id){
        logger.info("Update {} with id = {}", user, id);
        assureIdConsistent(user, id);
        userService.update(user);
    }

    public void delete(int id){
        logger.info("Delete user with id = {}", id);
        userService.delete(id);
    }
}
