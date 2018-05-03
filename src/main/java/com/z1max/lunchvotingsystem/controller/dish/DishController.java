package com.z1max.lunchvotingsystem.controller.dish;

import com.z1max.lunchvotingsystem.model.Dish;
import com.z1max.lunchvotingsystem.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.z1max.lunchvotingsystem.util.Util.assureIdConsistent;
import static com.z1max.lunchvotingsystem.util.Util.checkNew;

@RestController
@RequestMapping(DishController.REST_URL)
public class DishController {
    static final String REST_URL = "/api/restaurants/{restaurantId}/dishes";

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable int restaurantId) {
        return dishService.getAll(restaurantId);
    }

    @GetMapping(value = "/{dishId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable int restaurantId, @PathVariable int dishId){
        return dishService.getDishByRestaurant(restaurantId, dishId);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable int restaurantId){
        dishService.deleteAllByRestaurant(restaurantId);
    }

    @DeleteMapping("/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int dishId){
        dishService.delete(dishId, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish create(@PathVariable int restaurantId, @RequestBody Dish dish){
        checkNew(dish);
        return dishService.save(dish, restaurantId);
    }

    @PutMapping(value = "/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish update(@PathVariable int restaurantId,
                       @PathVariable int dishId,
                       @RequestBody Dish dish) {
        assureIdConsistent(dish, dishId);
        return dishService.save(dish, restaurantId);
    }
}
