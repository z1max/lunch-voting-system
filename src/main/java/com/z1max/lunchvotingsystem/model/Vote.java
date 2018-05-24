package com.z1max.lunchvotingsystem.model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @NotNull
    @Column(name = "user_id")
    private Integer userId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    public Vote(){}

    public Vote(Integer id, Integer userId, Restaurant restaurant, LocalDate date) {
        super(id);
        this.userId = userId;
        this.restaurant = restaurant;
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", restaurant=").append(Hibernate.isInitialized(restaurant) ? restaurant.getId() : null);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
