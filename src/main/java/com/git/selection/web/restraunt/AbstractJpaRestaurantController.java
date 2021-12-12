package com.git.selection.web.restraunt;

import com.git.selection.model.Restaurant;
import com.git.selection.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

import static com.git.selection.util.ValidationUtil.checkNotFound;
import static com.git.selection.util.ValidationUtil.checkNotFoundWithId;

public class AbstractJpaRestaurantController {

    @Autowired
    RestaurantRepository repository;

    Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant,"must not be null");
        return repository.save(restaurant);
    }

    Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant,"must not be null");
        return checkNotFoundWithId(repository.save(restaurant),restaurant.id());
    }

    void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id),id);
    }

    Restaurant getByEmail(String email) {
        return checkNotFound(repository.getByEmail(email),"email not found");
    }

    List<Restaurant> getAll() {
        return repository.getAll();
    }
}
