package com.git.selection.repository;

import com.git.selection.model.Restaurant;
import com.git.selection.model.User;
import com.git.selection.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    // null if not found
    Restaurant getByEmail(String email);

    List<Restaurant> getAll();
}