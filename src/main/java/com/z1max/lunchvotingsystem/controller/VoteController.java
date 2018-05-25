package com.z1max.lunchvotingsystem.controller;

import com.z1max.lunchvotingsystem.service.RestaurantService;
import com.z1max.lunchvotingsystem.service.VoteService;
import com.z1max.lunchvotingsystem.to.RestaurantWithVotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(VoteController.REST_URL)
public class VoteController {
    static final String REST_URL = "/api/votes";

    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);

    private final VoteService voteService;
    private final RestaurantService restaurantService;

    @Autowired
    public VoteController(VoteService voteService, RestaurantService restaurantService) {
        this.voteService = voteService;
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public void vote(@RequestParam int userId, @RequestParam int restaurantId){
        //TODO check userID
        logger.info("Vote userId = {}, restaurantId = {}", userId, restaurantId);
        voteService.vote(userId, restaurantId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithVotes> result(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        logger.info("Result date = {}", date);
        return restaurantService.getAllWithVotesByDate(checkDate(date));
    }

    //Problem if we have two restaurants with the same number of votes
    @GetMapping(value = "/winner", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantWithVotes getWinner(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        logger.info("Winner date = {}", date);
        List<RestaurantWithVotes> restaurantList = restaurantService.getAllWithVotesByDate(checkDate(date));
        return restaurantList.stream().max(Comparator.comparing(RestaurantWithVotes::getVotes)).get();
    }

    private LocalDate checkDate(LocalDate date) {
        return date == null ? LocalDate.now() : date;
    }
}
