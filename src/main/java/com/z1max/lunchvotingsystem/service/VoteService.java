package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Vote;

public interface VoteService {
    Vote vote(int userId, int restaurantId);
}
