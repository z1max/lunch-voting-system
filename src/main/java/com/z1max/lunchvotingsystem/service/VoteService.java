package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {
    //TODO
    List<Vote> getByDate(LocalDate date);
}
