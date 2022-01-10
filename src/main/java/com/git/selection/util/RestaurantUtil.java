package com.git.selection.util;

import com.git.selection.model.Restaurant;
import com.git.selection.to.RestaurantTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RestaurantUtil {
    public static Restaurant createNewFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName(), restaurantTo.getEmail().toLowerCase(),
                restaurantTo.getDescription(), restaurantTo.getPhone());
    }

    public static Restaurant updateFromTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        restaurant.setEmail(restaurantTo.getEmail().toLowerCase());
        restaurant.setDescription(restaurantTo.getDescription());
        restaurant.setPhone(restaurantTo.getPhone());
        return restaurant;
    }
}
