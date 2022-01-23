package com.github.twosj.selection.util;

import com.github.twosj.selection.model.Dish;
import com.github.twosj.selection.to.DishTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishUtil {

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
