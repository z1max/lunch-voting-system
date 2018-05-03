package com.z1max.lunchvotingsystem.repository;

import com.z1max.lunchvotingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findAllByRestaurantId(int restaurantId);
    
    void deleteAllByRestaurantId(int restaurantId);
}
