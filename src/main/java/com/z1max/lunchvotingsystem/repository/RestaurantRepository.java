package com.z1max.lunchvotingsystem.repository;

import com.z1max.lunchvotingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT DISTINCT restaurant FROM Restaurant restaurant JOIN FETCH restaurant.menu")
    List<Restaurant> getAllWithMenu();

    @Query("SELECT restaurant FROM Restaurant restaurant JOIN FETCH restaurant.menu WHERE restaurant.id = :id")
    Restaurant getWithMenu(@Param("id") int id);

    @Query("SELECT restaurant FROM Restaurant restaurant JOIN FETCH restaurant.votes votes WHERE restaurant.id = :id AND votes.date = :date")
    Restaurant getWithVotes(@Param("id") int id, @Param("date") LocalDate date);

    @Query("SELECT DISTINCT restaurant FROM Restaurant restaurant JOIN FETCH restaurant.votes votes WHERE votes.date = :date")
    List<Restaurant> getAllWithVotesByDate(@Param("date") LocalDate date);
}
