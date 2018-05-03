package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getAll(int restaurantId);

    Dish getDishByRestaurant(int dishId, int restaurantId);

    void delete(int dishId, int restaurantId);

    void deleteAllByRestaurant(int restaurantId);

    Dish save(Dish dish, int restaurantId);
}
