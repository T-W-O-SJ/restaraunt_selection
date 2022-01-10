package com.git.selection.web.restaurant;

import com.git.selection.model.Restaurant;
import com.git.selection.repository.RestaurantRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CacheConfig(cacheNames = "restaurant")
@Slf4j
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProfileRestaurantController {
    static final String REST_URL = "/api/profile/restaurants";

    RestaurantRepository repository;

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("Get restaurant{} ",id);
        return ResponseEntity.of(repository.get(id));
    }

    @GetMapping("/{id}/with_dishes")
    @Operation(summary = "Get restaurant with its menu for today ")
    public ResponseEntity<Restaurant> getWithDishes(@PathVariable int id) {
        log.info("Get restaurant{} with dishes today",id);
        return ResponseEntity.of(repository.getWithDishes(id));
    }

    @Cacheable
    @GetMapping()
    @Operation(summary = "Get all restaurants")
    public List<Restaurant> getAll() {
        log.info("Get  all restaurants");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping(value = "/with_dishes")
    @Operation(summary = "Get all restaurants with a menu for today ")
    public List<Restaurant> getAllWithDishesToday() {
        log.info("Get  all restaurants with dishes today");
        return repository.getAllWithDishesToday();
    }

}
