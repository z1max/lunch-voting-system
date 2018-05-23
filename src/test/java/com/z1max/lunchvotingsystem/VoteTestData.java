package com.z1max.lunchvotingsystem;

import com.z1max.lunchvotingsystem.model.Vote;

import java.time.LocalDateTime;

import static com.z1max.lunchvotingsystem.RestaurantTestData.HARD_ROCK_CAFE;
import static com.z1max.lunchvotingsystem.UserTestData.ADMIN;
import static com.z1max.lunchvotingsystem.UserTestData.USER;

public class VoteTestData {
    public static final Vote VOTE1 = new Vote(ADMIN, HARD_ROCK_CAFE, LocalDateTime.of(2018,5,3,10,0,0));
    public static final Vote VOTE2 = new Vote(USER, HARD_ROCK_CAFE, LocalDateTime.of(2018,5,3,9,0,0));
}
