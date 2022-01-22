package com.github.twosj.selection.web.restaurant;

import com.github.twosj.selection.error.NotFoundException;
import com.github.twosj.selection.model.Restaurant;
import com.github.twosj.selection.repository.RestaurantRepository;
import com.github.twosj.selection.to.RestaurantTo;
import com.github.twosj.selection.util.RestaurantUtil;
import com.github.twosj.selection.util.validation.ValidationUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

@RestController
@CacheConfig(cacheNames = "restaurant")
@Slf4j
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AdminRestaurantController {
    RestaurantRepository repository;

    static final String REST_URL = "/api/admin/restaurants";

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Delete a restaurant by its id")
    public void delete(@PathVariable int id) {
        repository.deleteExisted(id);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "restaurant", allEntries = true)
    @Operation(summary = "Update a restaurant ")
    public void update(@Valid @RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        log.info("Update {} for restaurant {}", restaurantTo,id);
        Assert.notNull(restaurantTo, "must not be null");
        Restaurant updateRest = repository.get(id).orElseThrow(() -> new NotFoundException("No restaurant for update"));
        repository.save(RestaurantUtil.updateFromTo(updateRest, restaurantTo));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "restaurant", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a restaurant")
    public ResponseEntity<Restaurant> create(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        ValidationUtil.checkNew(restaurantTo);
        Assert.notNull(restaurantTo, "must not be null");
        Restaurant restaurant = RestaurantUtil.createNewFromTo(restaurantTo);
        Restaurant created = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
