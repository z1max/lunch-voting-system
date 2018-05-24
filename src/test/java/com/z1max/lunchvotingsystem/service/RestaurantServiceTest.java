package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.Dish;
import com.z1max.lunchvotingsystem.model.Restaurant;
import com.z1max.lunchvotingsystem.to.RestaurantWithVotes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.z1max.lunchvotingsystem.RestaurantTestData.*;
import static com.z1max.lunchvotingsystem.VoteTestData.VOTE1;
import static com.z1max.lunchvotingsystem.VoteTestData.VOTE2;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void get() {
        Restaurant restaurant = service.get(HARD_ROCK_CAFE_ID);
        assertMatchIgnoreMenuAndVotes(restaurant, HARD_ROCK_CAFE);
    }

    @Test
    public void getAll() {
        List<Restaurant> actual = service.getAll();
        assertMatchIgnoreMenuAndVotes(actual, HARD_ROCK_CAFE, PIZZAMANIA, BURGER_KING);
    }

    @Test
    public void create() {
        Restaurant restaurant = new Restaurant(null, "New Restaurant", Collections.emptyList());
        Restaurant created = service.save(restaurant);
        assertMatchIgnoreMenuAndVotes(service.getAll(), HARD_ROCK_CAFE, PIZZAMANIA, BURGER_KING, created);
    }

    @Test
    public void update() {
        Restaurant restaurant = new Restaurant(HARD_ROCK_CAFE_ID, "Updated", new ArrayList<>());
        Restaurant updated = service.save(restaurant);
        assertMatchIgnoreMenuAndVotes(service.getAll(), updated, PIZZAMANIA, BURGER_KING);
    }

    @Test
    public void delete() {
        service.delete(HARD_ROCK_CAFE_ID);
        assertMatchIgnoreMenuAndVotes(service.getAll(), PIZZAMANIA, BURGER_KING);
    }

    @Test
    public void getAllWithMenu() {
        List<Restaurant> actual = service.getAllWithMenu();
        assertMatchIgnoreMenuAndVotes(actual, HARD_ROCK_CAFE, PIZZAMANIA, BURGER_KING);

        List<Dish> actualMenu = actual.get(0).getMenu();
        List<Dish> expectedMenu = HARD_ROCK_CAFE.getMenu();
        expectedMenu.forEach(dish -> dish.setRestaurant(HARD_ROCK_CAFE));

        assertMatchMenu(actualMenu, expectedMenu);

        actualMenu = actual.get(1).getMenu();
        expectedMenu = PIZZAMANIA.getMenu();
        expectedMenu.forEach(dish -> dish.setRestaurant(PIZZAMANIA));

        assertMatchMenu(actualMenu, expectedMenu);

        actualMenu = actual.get(2).getMenu();
        expectedMenu = BURGER_KING.getMenu();
        expectedMenu.forEach(dish -> dish.setRestaurant(BURGER_KING));

        assertMatchMenu(actualMenu, expectedMenu);
    }

    @Test
    public void getWithMenu() {
        Restaurant actual = service.getWithMenu(HARD_ROCK_CAFE_ID);
        assertMatchIgnoreMenuAndVotes(actual, HARD_ROCK_CAFE);

        List<Dish> actualMenu = actual.getMenu();
        List<Dish> expectedMenu = HARD_ROCK_CAFE.getMenu();
        expectedMenu.forEach(dish -> dish.setRestaurant(HARD_ROCK_CAFE));

        assertMatchMenu(actualMenu, expectedMenu);
    }

    @Test
    public void getWithVotes() {
        Restaurant actual = service.getWithVotesByDate(HARD_ROCK_CAFE_ID, LocalDate.of(2018, 5, 3));
        assertMatchIgnoreMenuAndVotes(actual, HARD_ROCK_CAFE);

        assertThat(actual.getVotes().get(0)).isEqualToComparingFieldByField(VOTE1);
        assertThat(actual.getVotes().get(1)).isEqualToComparingFieldByField(VOTE2);
    }

    @Test
    public void getAllWithVotesByDate(){
        List<RestaurantWithVotes> actual = service.getAllWithVotesByDate(LocalDate.of(2018, 5, 3));
        actual.forEach(System.out::println);
    }

    private void assertMatchMenu(List<Dish> actualMenu, List<Dish> expectedMenu) {
        assertThat(actualMenu.size()).isEqualTo(expectedMenu.size());

        assertThat(actualMenu.get(0)).isEqualToComparingFieldByField(expectedMenu.get(0));
        assertThat(actualMenu.get(1)).isEqualToComparingFieldByField(expectedMenu.get(1));
    }
}