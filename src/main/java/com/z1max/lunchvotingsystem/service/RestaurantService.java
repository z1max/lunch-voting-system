package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    void delete(int id);

    List<Restaurant> getAllWithMenu();

    Restaurant getWithMenu(int id);

    Restaurant getWithVotes(int id);
}
