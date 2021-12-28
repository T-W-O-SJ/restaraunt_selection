package com.git.selection.service;

import com.git.selection.model.Restaurant;
import com.git.selection.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.git.selection.util.ValidationUtil.checkNotFound;
import static com.git.selection.util.ValidationUtil.checkNotFoundWithId;
@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository repository;

    @CacheEvict(value = "restaurants",allEntries = true)
   public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant,"must not be null");
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants",allEntries = true)
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant,"must not be null");
        checkNotFoundWithId(repository.save(restaurant),restaurant.id());
    }

    @CacheEvict(value = "restaurants",allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

   public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id),id);
    }

    @Cacheable("restaurants")
   public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAllWithDishes() {
        return repository.getAllWithDishes();
    }
}
