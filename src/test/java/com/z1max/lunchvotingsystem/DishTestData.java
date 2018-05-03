package com.z1max.lunchvotingsystem;

import com.z1max.lunchvotingsystem.model.Dish;

import java.util.Arrays;

import static com.z1max.lunchvotingsystem.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class DishTestData {

    public static final int HARD_ROCK_BURGER_ID = START_SEQ + 5;
    public static final int HARD_ROCK_PASTA_ID = START_SEQ + 6;
    public static final int PIZZA_ROMEO_ID = START_SEQ + 7;
    public static final int PIZZA_PEPPERONI_ID = START_SEQ + 8;
    public static final int BIG_KING_ID = START_SEQ + 9;
    public static final int FRENCH_FRIES_ID = START_SEQ + 10;

    public static final Dish HARD_ROCK_BURGER = new Dish(HARD_ROCK_BURGER_ID, "Hard Rock Burger", 1000L);
    public static final Dish HARD_ROCK_PASTA = new Dish(HARD_ROCK_PASTA_ID, "Hard Rock Pasta", 700L);
    public static final Dish PIZZA_ROMEO = new Dish(PIZZA_ROMEO_ID, "Pizza Romeo", 800L);
    public static final Dish PIZZA_PEPPERONI = new Dish(PIZZA_PEPPERONI_ID, "Pizza Pepperoni", 600L);
    public static final Dish BIG_KING = new Dish(BIG_KING_ID, "Big King", 500L);
    public static final Dish FRENCH_FRIES = new Dish(FRENCH_FRIES_ID, "French Fries", 300L);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected){
        assertMatch(actual, Arrays.asList(expected));
    }
}
