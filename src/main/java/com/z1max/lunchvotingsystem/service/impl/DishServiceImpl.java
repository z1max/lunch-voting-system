package com.z1max.lunchvotingsystem.service.impl;

import com.z1max.lunchvotingsystem.model.Dish;
import com.z1max.lunchvotingsystem.model.Restaurant;
import com.z1max.lunchvotingsystem.repository.DishRepository;
import com.z1max.lunchvotingsystem.repository.RestaurantRepository;
import com.z1max.lunchvotingsystem.service.DishService;
import com.z1max.lunchvotingsystem.util.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Dish> getAll(int restaurantId){
        return dishRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public Dish getDishByRestaurant(int dishId, int restaurantId){
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find dish with id = " + dishId));
        if (restaurantId != dish.getRestaurant().getId()){
            throw new IllegalArgumentException("Dish with id = " + dishId +
                    " does not belong to Restaurant with id = " + restaurantId);
        }
        return dish;
    }

    @Override
    @Transactional
    public void delete(int dishId, int restaurantId){
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find dish with id = " + dishId));
        if (restaurantId != dish.getRestaurant().getId()){
            throw new IllegalArgumentException("Dish with id = " + dishId +
                    " does not belong to Restaurant with id = " + restaurantId);
        }
        dishRepository.deleteById(dishId);
    }

    @Override
    @Transactional
    public void deleteAllByRestaurant(int restaurantId){
        dishRepository.deleteAllByRestaurantId(restaurantId);
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId){
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        dish.setRestaurant(restaurant);
        Dish created = dishRepository.save(dish);
        restaurant.addDish(created);
        return created;
    }

}
