package com.git.selection.web.restraunt;

import com.git.selection.model.Restaurant;

import java.util.List;

public class AbstractJpaRestaurantController {

    Restaurant create(Restaurant restaurant) {
        return null;
    }

    Restaurant update(Restaurant restaurant) {
        return null;
    }

    // false if not found
    void delete(int id) {
    }

    // null if not found
    Restaurant get(int id) {
        return null;
    }

    // null if not found
    Restaurant getByEmail(String email) {
        return null;
    }

    List<Restaurant> getAll() {
        return null;
    }
}
