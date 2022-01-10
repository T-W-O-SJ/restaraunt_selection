package com.git.selection.web.restaurant;

import com.git.selection.error.NotFoundException;
import com.git.selection.model.Dish;
import com.git.selection.repository.DishRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ProfileMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ProfileMenuController {
    static final String REST_URL = "/api/profile/dishes";
    DishRepository repository;

    @GetMapping(value = "/{restaurantId}")
    @Operation(summary = "Get a menu for today in selected restaurant by restaurant id")
    public List<Dish> getAllByRestaurantToday(@PathVariable int restaurantId) {
        log.info("Get all dishes for restaurant{} today",restaurantId);
        return repository.getAllByRestaurantToday(restaurantId);
    }

    @GetMapping("/{restaurantId}/{id}")
    @Operation(summary = "Get a dish by it's id and restaurant id ")
    public Dish get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("Get dish{} for restaurant{}", id,restaurantId);
        return repository.get(id, restaurantId).orElseThrow(() -> new NotFoundException("not found"));
    }
}
