package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Restaurant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.z1max.lunchvotingsystem.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void get() {
        Restaurant restaurant = restaurantService.get(HARD_ROCK_CAFE_ID);
        assertMatchIgnoreMenu(restaurant, HARD_ROCK_CAFE);
    }

    @Test
    public void getAll() {
        List<Restaurant> actual = restaurantService.getAll();
        assertMatchIgnoreMenu(actual, HARD_ROCK_CAFE, PIZZAMANIA, BURGER_KING);
    }

    @Test
    public void create() {
        Restaurant restaurant = new Restaurant(null, "New Restaurant", Collections.emptyList());
        Restaurant created = restaurantService.save(restaurant);
        assertMatchIgnoreMenu(restaurantService.getAll(), HARD_ROCK_CAFE, PIZZAMANIA, BURGER_KING, created);
    }

    @Test
    public void update() {
        Restaurant restaurant = new Restaurant(HARD_ROCK_CAFE_ID, "Updated", new ArrayList<>());
        Restaurant updated = restaurantService.save(restaurant);
        assertMatchIgnoreMenu(restaurantService.getAll(), updated, PIZZAMANIA, BURGER_KING);
    }

    @Test
    public void delete() {
        restaurantService.delete(HARD_ROCK_CAFE_ID);
        assertMatchIgnoreMenu(restaurantService.getAll(), PIZZAMANIA, BURGER_KING);
    }

    @Test
    public void getAllWithMenu() {
        List<Restaurant> actual = restaurantService.getAllWithMenu();
        assertMatch(actual, HARD_ROCK_CAFE, PIZZAMANIA, BURGER_KING);
    }

    @Test
    public void getWithMenu() {
        Restaurant actual = restaurantService.getWithMenu(HARD_ROCK_CAFE_ID);
        System.out.println(actual);
        assertMatch(actual, HARD_ROCK_CAFE);
    }
}