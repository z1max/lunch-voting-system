package com.z1max.lunchvotingsystem;

import com.z1max.lunchvotingsystem.model.Restaurant;

import java.util.Arrays;

import static com.z1max.lunchvotingsystem.DishTestData.*;
import static com.z1max.lunchvotingsystem.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final int HARD_ROCK_CAFE_ID = START_SEQ + 2;
    public static final int PIZZAMANIA_ID = START_SEQ + 3;
    public static final int BURGER_KING_ID = START_SEQ + 4;

    public static final Restaurant HARD_ROCK_CAFE = new Restaurant(HARD_ROCK_CAFE_ID, "Hard Rock Cafe",
            Arrays.asList(HARD_ROCK_BURGER, HARD_ROCK_PASTA));
    public static final Restaurant PIZZAMANIA = new Restaurant(PIZZAMANIA_ID, "Pizzamania",
            Arrays.asList(PIZZA_ROMEO, PIZZA_PEPPERONI));
    public static final Restaurant BURGER_KING = new Restaurant(BURGER_KING_ID, "Burger King",
            Arrays.asList(BIG_KING, FRENCH_FRIES));

    public static void assertMatchIgnoreMenu(Restaurant actual, Restaurant expected){
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"menu");
    }

    public static void assertMatchIgnoreMenu(Iterable<Restaurant> actual, Iterable<Restaurant> expected){
        assertThat(actual).usingElementComparatorIgnoringFields("menu").isEqualTo(expected);
    }

    public static void assertMatchIgnoreMenu(Iterable<Restaurant> actual, Restaurant... expected){
        assertMatchIgnoreMenu(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Restaurant actual, Restaurant expected){
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected){
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected){
        assertMatch(actual, Arrays.asList(expected));
    }
}
