package com.z1max.lunchvotingsystem.model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Range(min = 1)
    @Column(name = "price", nullable = false)
    private Long priceInCents;

    public Dish(){}

    public Dish(Integer id, String name, Long priceInCents) {
        super(id, name);
        this.priceInCents = priceInCents;
    }

    public Long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(Long priceInCents) {
        this.priceInCents = priceInCents;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", restaurant=").append(Hibernate.isInitialized(restaurant) ? restaurant.getId() : null);
        sb.append(", priceInCents=").append(priceInCents);
        sb.append('}');
        return sb.toString();
    }
}
