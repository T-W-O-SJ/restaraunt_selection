package com.git.selection.repository;

import com.git.selection.model.Dish;
import com.git.selection.model.Restaurant;

import java.util.List;

public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);

    // null if not found
    Dish get(int id,int restaurantId);

    List<Dish> getAll(int restaurantId);
}