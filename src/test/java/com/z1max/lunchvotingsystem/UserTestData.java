package com.z1max.lunchvotingsystem;

import com.z1max.lunchvotingsystem.model.Role;
import com.z1max.lunchvotingsystem.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static com.z1max.lunchvotingsystem.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public final static User USER = new User(USER_ID,"user", "user@gmail.com", "userpassword", Role.ROLE_USER);
    public final static User ADMIN = new User(ADMIN_ID,"admin", "admin@gmail.com", "adminpassword", Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected){
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected){
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected){
        assertMatch(actual, Arrays.asList(expected));
    }

}
