package com.github.twosj.selection.web.restaurant;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.twosj.selection.util.validation.ValidationUtil.getNot_found;

@RestController
@CacheConfig(cacheNames = {"restaurants","restaurantsWithDishes"})
@Slf4j
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";

    RestaurantRepository repository;

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant")
    public RestaurantTo get(@PathVariable int id) {
        log.info("Get restaurant{} ", id);
        return RestaurantUtil.createTo(repository.findById(id).orElseThrow(getNot_found("Restaurant not found")));
    }

    @GetMapping("/{id}/with-dishes-today")
    @Operation(summary = "Get restaurant with its menu for today ")
    public Restaurant getWithDishesToday(@PathVariable int id) {
        return repository.getWithDishesToday(id).orElse(repository.findById(id).orElseThrow(getNot_found("Restaurant not found")));
    }

    @Cacheable("restaurants")
    @GetMapping
    @Operation(summary = "Get all restaurants")
    public List<RestaurantTo> getAll() {
        log.info("Get all restaurants");
        return RestaurantUtil.getTos(repository.findAll(Sort.by(Sort.Direction.ASC, "name", "id")));
    }

    @Cacheable("restaurantsWithDishes")
    @GetMapping(value = "/with-dishes-today")
    @Operation(summary = "Get all restaurants with a menu for today ")
    public List<Restaurant> getAllWithDishesToday() {
        log.info("Get  all restaurants with dishes today");
        return repository.getAllWithDishesToday();
    }
}
