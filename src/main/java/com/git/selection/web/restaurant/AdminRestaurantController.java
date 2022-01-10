package com.git.selection.web.restaurant;

import com.git.selection.error.NotFoundException;
import com.git.selection.model.Restaurant;
import com.git.selection.repository.RestaurantRepository;
import com.git.selection.to.RestaurantTo;
import com.git.selection.util.RestaurantUtil;
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

import static com.git.selection.util.validation.ValidationUtil.checkNew;

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
        repository.delete(id);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "restaurant", allEntries = true)
    @Operation(summary = "Update a restaurant ")
    public void update(@Valid @RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
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
        checkNew(restaurantTo);
        Assert.notNull(restaurantTo, "must not be null");
        Restaurant restaurant = RestaurantUtil.createNewFromTo(restaurantTo);
        Restaurant created = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}/with_votes")
    @Operation(summary = "Get a restaurant with votes")
    public ResponseEntity<Restaurant> getWithVotes(@PathVariable int id) {
        return ResponseEntity.of(repository.getWithVotes(id));
    }
}
