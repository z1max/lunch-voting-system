package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Restaurant;
import com.z1max.lunchvotingsystem.repository.RestaurantRepository;
import com.z1max.lunchvotingsystem.util.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find restaurant with id = " + id));
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    @Override
    @Transactional
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<Restaurant> getAllWithMenu() {
        return restaurantRepository.getAllWithMenu();
    }

    @Override
    public Restaurant getWithMenu(int id) {
        return restaurantRepository.getWithMenu(id);
    }
}
