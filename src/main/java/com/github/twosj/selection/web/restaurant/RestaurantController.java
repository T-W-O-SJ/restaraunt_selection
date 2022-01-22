package com.github.twosj.selection.web.restaurant;

import com.github.twosj.selection.error.NotFoundException;
import com.github.twosj.selection.model.Restaurant;
import com.github.twosj.selection.repository.RestaurantRepository;
import com.github.twosj.selection.to.RestaurantTo;
import com.github.twosj.selection.util.RestaurantUtil;
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
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";

    RestaurantRepository repository;

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant")
    public RestaurantTo get(@PathVariable int id) {
        log.info("Get restaurant{} ",id);
        return RestaurantUtil.createTo(repository.get(id).orElseThrow(()->new NotFoundException("not found")));
    }

    @GetMapping("/{id}/with-dishes-today")
    @Operation(summary = "Get restaurant with its menu for today ")
    public ResponseEntity<Restaurant> getWithDishes(@PathVariable int id) {
        log.info("Get restaurant{} with dishes today",id);
        return ResponseEntity.of(repository.getWithDishes(id));
    }

    @Cacheable
    @GetMapping()
    @Operation(summary = "Get all restaurants")
    public List<RestaurantTo> getAll() {
        log.info("Get all restaurants");
        return RestaurantUtil.getTos(repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    @GetMapping(value = "/with-dishes-today")
    @Operation(summary = "Get all restaurants with a menu for today ")
    public List<RestaurantTo> getAllWithDishesToday() {
        log.info("Get  all restaurants with dishes today");
        return RestaurantUtil.getTos(repository.getAllWithDishesToday());
    }

}
