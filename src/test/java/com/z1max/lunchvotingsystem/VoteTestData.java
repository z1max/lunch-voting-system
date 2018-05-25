package com.z1max.lunchvotingsystem;

import com.z1max.lunchvotingsystem.model.Vote;

import java.time.LocalDate;

import static com.z1max.lunchvotingsystem.RestaurantTestData.HARD_ROCK_CAFE;
import static com.z1max.lunchvotingsystem.UserTestData.ADMIN_ID;
import static com.z1max.lunchvotingsystem.UserTestData.USER_ID;
import static com.z1max.lunchvotingsystem.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE1_ID = START_SEQ + 11;
    public static final int VOTE2_ID = START_SEQ + 12;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, USER_ID, HARD_ROCK_CAFE, LocalDate.of(2018,5,3));
    public static final Vote VOTE2 = new Vote(VOTE2_ID, ADMIN_ID, HARD_ROCK_CAFE, LocalDate.of(2018,5,3));
}
