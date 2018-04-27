package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.config.AppConfig;
import com.z1max.lunchvotingsystem.config.PersistenceConfig;
import com.z1max.lunchvotingsystem.model.Role;
import com.z1max.lunchvotingsystem.model.User;
import com.z1max.lunchvotingsystem.util.exception.UserNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static com.z1max.lunchvotingsystem.UserTestData.*;

@ContextConfiguration(classes = {PersistenceConfig.class, AppConfig.class})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/insert-data.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger("result");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    UserService service;

    @Test
    public void get() {
        User actual = service.get(USER_ID);
        assertMatch(actual, USER);
    }

    @Test
    public void getWrongId(){
        int wrongId = 1;
        thrown.expect(UserNotFoundException.class);
        thrown.expectMessage("Cannot find user with id = " + wrongId);
        service.get(wrongId);
    }


    @Test
    public void getByEmail() {
        User actual = service.getByEmail(USER.getEmail());
        assertMatch(actual, USER);
    }

    @Test
    public void getWrongEmail(){
        String wrongEmail = "wrong@gmail.com";
        thrown.expect(UserNotFoundException.class);
        thrown.expectMessage("Cannot find user with email = " + wrongEmail);
        service.getByEmail(wrongEmail);
    }

    @Test
    public void getAll() {
        List<User> actual = service.getAll();
        assertMatch(actual, USER, ADMIN);
    }

    @Test
    public void create() {
        User newUser = new User(null, "newuser", "newuser@gmail.com", "newuserpassword", Role.ROLE_USER);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), USER, ADMIN, newUser);
    }

    @Test
    public void update(){
        User toUpdate = new User(USER.getId(), "updated", "updated@gmail.com", "updatedpassword", USER.getRoles());
        User updated = service.update(toUpdate);
        assertMatch(service.getAll(), updated, ADMIN);
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }
}