package com.git.selection.web.restaurant;

import com.git.selection.error.NotFoundException;
import com.git.selection.model.Dish;
import com.git.selection.repository.DishRepository;
import com.git.selection.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
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

import static com.git.selection.util.validation.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class MenuRestController {
    static final String REST_URL = "/api/admin/dishes";

    DishRepository repository;
    RestaurantRepository restaurantRepository;


    @GetMapping("/{restaurantId}/{id}")
    public Dish get(@PathVariable int id, @PathVariable int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id).orElseThrow(() -> new NotFoundException("not found"));
    }
    @GetMapping("/{restaurantId}/{id}/with-restaurant")
    public Dish getWithRestaurant(@PathVariable int id, @PathVariable int restaurantId) {
        return checkNotFoundWithId(repository.getWithRestaurant(id, restaurantId), id).orElseThrow(() -> new NotFoundException("not found"));
    }

    @DeleteMapping("/{restaurantId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete {} for restaurant{}", id, restaurantId);
Dish dish = repository.checkBelong(id, restaurantId);
repository.delete(dish);
    }

    @GetMapping(value = "/{restaurantId}")
    public List<Dish> getAllByLocalDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate, @PathVariable int restaurantId) {
        return repository.getAllByLocalDateAndId(localDate, restaurantId);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int id) {
        Assert.notNull(dish, "must not be null");
        checkNotFoundWithId(repository.save(dish), dish.id());
    }

    @Transactional
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish create(@Valid @RequestBody Dish dish, @PathVariable int id) {
        dish.setRestaurant(restaurantRepository.getById(id));
        repository.checkBelong(id,dish.getRestaurant().getId() );
        Assert.notNull(dish, "must not be null");
        return repository.save(dish);
    }
}
