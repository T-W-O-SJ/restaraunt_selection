package com.github.twosj.selection.web.restaurant;

import com.github.twosj.selection.error.NotFoundException;
import com.github.twosj.selection.model.Dish;
import com.github.twosj.selection.repository.DishRepository;
import com.github.twosj.selection.repository.RestaurantRepository;
import com.github.twosj.selection.to.DishTo;
import com.github.twosj.selection.util.DishUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminMenuController {
    static final String REST_URL = "/api/admin/dishes";

    DishRepository repository;
    RestaurantRepository restaurantRepository;

    @DeleteMapping("/{restaurantId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a dish by its id")
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete {} for restaurant{}", id, restaurantId);
        Dish dish = repository.checkBelong(id, restaurantId);
        repository.delete(dish);
    }

    @PutMapping(value = "/{restaurantId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @Operation(summary = "Update a dish")
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("update{} for restaurant{}", id, restaurantId);
        repository.checkBelong(id, restaurantId);
        Assert.notNull(dishTo, "must not be null");
        Dish dish = repository.get(id, restaurantId).orElseThrow(() -> new NotFoundException("no dish for update"));
        repository.save(DishUtil.updateFromTo(dish, dishTo));
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a dish")
    public Dish create(@Valid @RequestBody DishTo dishTo, @PathVariable int restaurantId) {
        log.info("create {} for restaurant{}", dishTo, restaurantId);
        Dish dish = DishUtil.createNewFromTo(dishTo);
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        Assert.notNull(dish, "must not be null");
        return repository.save(dish);
    }
}