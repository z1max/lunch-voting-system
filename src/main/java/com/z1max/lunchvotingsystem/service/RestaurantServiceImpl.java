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

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find restaurant with id = " + id));
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }


    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithMenu();
    }

    @Override
    public Restaurant getWithMenu(int id) {
        return repository.getWithMenu(id);
    }

    @Override
    public Restaurant getWithVotes(int id) {
        return repository.getWithVotes(id);
    }
}
