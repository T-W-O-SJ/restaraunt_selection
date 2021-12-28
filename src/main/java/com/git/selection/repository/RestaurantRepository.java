package com.git.selection.repository;

import com.git.selection.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getWithVotes(int id);

    List<Restaurant> getAllWithDishes();

    Restaurant getWithDishes(int id);
}