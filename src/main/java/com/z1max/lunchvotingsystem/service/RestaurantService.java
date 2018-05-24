package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Restaurant;
import com.z1max.lunchvotingsystem.to.RestaurantWithVotes;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    void delete(int id);

    List<Restaurant> getAllWithMenu();

    Restaurant getWithMenu(int id);

    Restaurant getWithVotesByDate(int id, LocalDate date);

    List<RestaurantWithVotes> getAllWithVotesByDate(LocalDate date);
}
