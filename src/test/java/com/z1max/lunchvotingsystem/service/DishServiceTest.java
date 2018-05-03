package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Dish;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

import java.util.Collections;
import java.util.List;

import static com.z1max.lunchvotingsystem.DishTestData.*;
import static com.z1max.lunchvotingsystem.DishTestData.assertMatch;
import static com.z1max.lunchvotingsystem.RestaurantTestData.*;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService dishService;

    @Test
    public void getAll() {
        List<Dish> actual = dishService.getAll(HARD_ROCK_CAFE_ID);
        assertMatch(actual, HARD_ROCK_BURGER, HARD_ROCK_PASTA);
    }

    @Test
    public void getDishByRestaurant() {
        Dish actual = dishService.getDishByRestaurant(PIZZA_PEPPERONI_ID, PIZZAMANIA_ID);
        assertMatch(actual, PIZZA_PEPPERONI);
    }

    @Test
    public void getDishByWrongRestaurant() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Dish with id = " + PIZZA_PEPPERONI_ID +
                " does not belong to Restaurant with id = " + BURGER_KING_ID);
        dishService.getDishByRestaurant(PIZZA_PEPPERONI_ID, BURGER_KING_ID);
    }

    @Test
    public void delete() {
        dishService.delete(BIG_KING_ID, BURGER_KING_ID);
        assertMatch(dishService.getAll(BURGER_KING_ID), FRENCH_FRIES);
    }

    @Test
    public void deleteWithWrongRestaurant() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Dish with id = " + PIZZA_PEPPERONI_ID +
                " does not belong to Restaurant with id = " + BURGER_KING_ID);
        dishService.delete(PIZZA_PEPPERONI_ID, BURGER_KING_ID);
    }

    @Test
    public void deleteAllByRestaurant() {
        dishService.deleteAllByRestaurant(HARD_ROCK_CAFE_ID);
        assertMatch(dishService.getAll(HARD_ROCK_CAFE_ID), Collections.emptyList());
    }

    @Test
    public void create() {
        Dish created = new Dish(null, "Created", 600L);
        dishService.save(created, BURGER_KING_ID);
        assertMatch(dishService.getAll(BURGER_KING_ID), BIG_KING, FRENCH_FRIES, created);
    }

    @Test
    public void createConstraintViolation() {
        thrown.expect(TransactionSystemException.class);
        Dish created = new Dish(null, "q", 0L);
        dishService.save(created, BURGER_KING_ID);
    }

    @Test
    public void update() {
        Dish updated = new Dish(BIG_KING_ID, "Updated Big King", 777L);
        dishService.save(updated, BURGER_KING_ID);
        assertMatch(dishService.getAll(BURGER_KING_ID), FRENCH_FRIES, updated);
    }
}