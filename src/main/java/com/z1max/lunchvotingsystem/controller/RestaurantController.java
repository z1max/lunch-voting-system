package com.z1max.lunchvotingsystem.controller;

import com.z1max.lunchvotingsystem.model.Restaurant;
import com.z1max.lunchvotingsystem.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.z1max.lunchvotingsystem.util.Util.assureIdConsistent;
import static com.z1max.lunchvotingsystem.util.Util.checkNew;

@RestController
@RequestMapping(RestaurantController.REST_URL)
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";
    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll(@RequestParam(required = false, name = "withMenu") boolean withMenu) {
        logger.info("Get all, withMenu = {}", withMenu);
        if (withMenu){
            return service.getAllWithMenu();
        } else {
            return service.getAll();
        }
    }

    @GetMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int restaurantId,
                          @RequestParam(required = false, name = "withMenu") boolean withMenu) {
        logger.info("Get with id = {}, withMenu = {}", restaurantId, withMenu);
        if (withMenu){
            return service.getWithMenu(restaurantId);
        } else {
            return service.get(restaurantId);
        }
    }

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId){
        logger.info("Delete id = {}", restaurantId);
        service.delete(restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant){
        logger.info("Create {}", restaurant);
        checkNew(restaurant);
        return service.save(restaurant);
    }

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant update(@RequestBody Restaurant restaurant, @PathVariable int restaurantId){
        logger.info("Update {} with id = {}", restaurant, restaurantId);
        assureIdConsistent(restaurant, restaurantId);
        return service.save(restaurant);
    }
}
