package com.z1max.lunchvotingsystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "restaurants", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> menu;

    public Restaurant() {}

    public Restaurant(Integer id, String name, List<Dish> menu) {
        super(id, name);
        this.menu = menu;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public void addDish(Dish dish){
        menu.add(dish);
        dish.setRestaurant(this);
    }

    public void removeDish(Dish dish){
        menu.remove(dish);
        dish.setRestaurant(null);
    }
}
