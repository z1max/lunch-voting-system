package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Vote;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void getByDate() {
        List<Vote> votes = service.getByDate(LocalDate.of(2018, 5, 3));
        System.out.println(votes);
    }
}