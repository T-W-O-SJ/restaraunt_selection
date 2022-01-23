package com.github.twosj.selection.util;

import com.github.twosj.selection.model.Restaurant;
import com.github.twosj.selection.to.RestaurantTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {
    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurant) {
        return restaurant.stream()
                .map(RestaurantUtil::createTo)
                .collect(Collectors.toList());
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getEmail(), restaurant.getDescription(), restaurant.getPhone());
    }

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
