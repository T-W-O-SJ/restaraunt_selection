package com.github.twosj.selection.util;

import com.github.twosj.selection.model.Dish;
import com.github.twosj.selection.to.DishTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DishUtil {

    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getLocalDate(), dish.getDescription(), dish.getPrice());
    }

    public static Dish createNewFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getLocalDate(), dishTo.getDescription(), dishTo.getPrice());
    }

    public static Dish updateFromTo(Dish dish, DishTo distTo) {
        dish.setLocalDate(distTo.getLocalDate());
        dish.setDescription(distTo.getDescription());
        dish.setPrice(distTo.getPrice());
        return dish;
    }
}
