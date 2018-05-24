package com.z1max.lunchvotingsystem.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Dish> menu;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Vote> votes;

    public Restaurant() {}

    public Restaurant(Integer id, String name){
        super(id, name);
        this.menu = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public Restaurant(Integer id, String name, List<Dish> menu) {
        super(id, name);
        this.menu = menu;
        this.votes = new ArrayList<>();
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public void addDish(Dish dish){
        menu.add(dish);
        if (dish.getRestaurant() != this) {
            dish.setRestaurant(this);
        }
    }

    public void removeDish(Dish dish){
        menu.remove(dish);
        dish.setRestaurant(null);
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void addVote(Vote vote){
        votes.add(vote);
        if (vote.getRestaurant() != this){
            vote.setRestaurant(this);
        }
    }

    public void removeVote(Vote vote){
        votes.remove(vote);
        vote.setRestaurant(null);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", menu=").append(Hibernate.isInitialized(menu) ? menu : null);
        sb.append(", votes=").append(Hibernate.isInitialized(votes) ? votes : null);
        sb.append('}');
        return sb.toString();
    }
}
