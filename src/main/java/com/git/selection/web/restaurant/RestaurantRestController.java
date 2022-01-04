package com.git.selection.web.restaurant;

import com.git.selection.model.Restaurant;
import com.git.selection.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.git.selection.util.validation.ValidationUtil.checkNew;
import static com.git.selection.util.validation.ValidationUtil.checkNotFoundWithId;


@RestController
@CacheConfig(cacheNames = "restaurants")
@Slf4j
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/api/admin/restaurants";

    RestaurantRepository repository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return  checkNotFoundWithId(repository.getById(id),id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void delete(@PathVariable int id) {
        repository.delete(id);
    }

    @Cacheable
    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping(value = "/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithDishes() {
        return repository.getAllWithDishes();
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(@Valid @RequestBody Restaurant restaurant) {
        Assert.notNull(restaurant, "must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
        ;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Assert.notNull(restaurant, "must not be null");
        return repository.save(restaurant);
    }
}
