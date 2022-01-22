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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminMenuController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";

    DishRepository repository;
    RestaurantRepository restaurantRepository;


    @GetMapping(value = "/")
    @Operation(summary = "Get a menu of restaurant for selected day  by restaurant id and date")
    public List<Dish> getAllByRestaurantAndDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate, @PathVariable int restaurantId) {
        log.info("Get all dishes for restaurant{} for {} date ",restaurantId,localDate);
        return repository.getAllByRestaurantAndDate(localDate,restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a dish by its id")
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete {} for restaurant{}", id, restaurantId);
        repository.checkBelong(id, restaurantId);
        repository.deleteExisted(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a dish")
    public Dish create(@Valid @RequestBody DishTo dishTo, @PathVariable int restaurantId) {
        log.info("create {} for restaurant{}", dishTo, restaurantId);
        Dish dish = DishUtil.createNewFromTo(dishTo);
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        Assert.notNull(dish, "must not be null");
        return repository.save(dish);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a dish by it's id and restaurant id ")
    public Dish get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("Get dish{} for restaurant{}", id,restaurantId);
        return repository.get(id, restaurantId).orElseThrow(() -> new NotFoundException("not found"));
    }
}