package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Vote;
import com.z1max.lunchvotingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }


    @Override
    public List<Vote> getByDate(LocalDate date) {
        //TODO
        return null;
    }
}
