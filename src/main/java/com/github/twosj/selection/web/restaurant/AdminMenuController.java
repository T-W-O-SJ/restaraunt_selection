package com.github.twosj.selection.web.restaurant;

import com.github.twosj.selection.model.Dish;
import com.github.twosj.selection.repository.DishRepository;
import com.github.twosj.selection.repository.RestaurantRepository;
import com.github.twosj.selection.to.DishTo;
import com.github.twosj.selection.util.DishUtil;
import com.github.twosj.selection.util.validation.ValidationUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static com.github.twosj.selection.util.validation.ValidationUtil.assureIdConsistent;
import static com.github.twosj.selection.util.validation.ValidationUtil.notFound;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminMenuController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";

    DishRepository repository;
    RestaurantRepository restaurantRepository;

    @GetMapping
    @Operation(summary = "Get a menu of restaurant for selected day  by restaurant id and date")
    public List<Dish> getAllByRestaurantAndDate(@RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate, @PathVariable int restaurantId) {
        log.info("Get all dishes for restaurant{} for {} date ", restaurantId, localDate);
        return repository.getAllByRestaurantAndDate(localDate, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a dish by its id")
    @CacheEvict(value = "restaurantsWithDishes", allEntries = true)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete {} for restaurant{}", id, restaurantId);
        repository.checkBelong(id, restaurantId);
        repository.deleteExisted(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @Operation(summary = "Update a dish")
    @CacheEvict(value = "restaurantsWithDishes", allEntries = true)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("update{} for restaurant{}", id, restaurantId);
        assureIdConsistent(dishTo, id);
        repository.checkBelong(id, restaurantId);
        Dish dish = repository.get(id, restaurantId).orElseThrow(notFound("No dish for update"));
        repository.save(DishUtil.updateFromTo(dish, dishTo));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a dish")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "restaurantsWithDishes", allEntries = true)
    public Dish create(@Valid @RequestBody DishTo dishTo, @PathVariable int restaurantId) {
        log.info("create {} for restaurant{}", dishTo, restaurantId);
        ValidationUtil.checkNew(dishTo);
        Dish dish = DishUtil.createNewFromTo(dishTo);
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        return repository.save(dish);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a dish by it's id and restaurant id ")
    public Dish get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("Get dish{} for restaurant{}", id, restaurantId);
        return repository.get(id, restaurantId).orElseThrow(notFound("Dish not found"));
    }
}