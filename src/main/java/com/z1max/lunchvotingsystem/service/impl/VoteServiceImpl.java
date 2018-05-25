package com.z1max.lunchvotingsystem.service.impl;

import com.z1max.lunchvotingsystem.model.Restaurant;
import com.z1max.lunchvotingsystem.model.Vote;
import com.z1max.lunchvotingsystem.repository.RestaurantRepository;
import com.z1max.lunchvotingsystem.repository.VoteRepository;
import com.z1max.lunchvotingsystem.service.VoteService;
import com.z1max.lunchvotingsystem.util.exception.VotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class VoteServiceImpl implements VoteService {

    private final static LocalTime VOTING_END_TIME = LocalTime.of(11, 0, 0);

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @Override
    public Vote vote(int userId, int restaurantId){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = currentDateTime.toLocalDate();
        LocalTime currentTime = currentDateTime.toLocalTime();

        if (currentTime.isAfter(VOTING_END_TIME)){
            throw new VotingException("Voting is ended.");
        }

        Vote vote = voteRepository.findByUserIdAndDate(userId, currentDate).orElse(new Vote());
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);

        if (vote.isNew()){
            vote = new Vote(userId, restaurant, currentDate);
        } else {
            vote.setRestaurant(restaurant);
        }
        return voteRepository.save(vote);
   }
}
