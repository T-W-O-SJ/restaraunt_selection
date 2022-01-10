package com.git.selection.util;

import com.git.selection.model.Dish;
import com.git.selection.to.DishTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DishUtil {
    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream()
                .map(DishUtil::createTo)
                .collect(Collectors.toList());
    }

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
