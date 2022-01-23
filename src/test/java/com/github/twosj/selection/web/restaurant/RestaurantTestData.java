package com.github.twosj.selection.web.restaurant;

import com.github.twosj.selection.model.Dish;
import com.github.twosj.selection.model.Restaurant;
import com.github.twosj.selection.to.RestaurantTo;
import com.github.twosj.selection.util.RestaurantUtil;
import com.github.twosj.selection.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static com.github.twosj.selection.web.vote.VoteTestData.votesForPushkin;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "votes", "dishes");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class);
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER_WITH_DISHES = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "votes", "dishes.restaurant");
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final int RESTAURANT3_ID = 3;
    public static final int DISH1_ID = 1;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Pushkin", "pushkin@yandex.ru", "элитный ресторан", "8917111222");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Baikal", "baikal@yandex.ru", "ресторан с хорошим видом", "8917111223");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT3_ID, "DodoPizza", "dodo@gmail.com", "ресторан быстрого питания", "8917111241");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT1_ID + 3, "VeganOnly", "vegi@yandex.ru", "ресторан для веганов", "8914444423");
    public static final Restaurant restaurant1Today = new Restaurant(RESTAURANT1_ID, "Pushkin", "pushkin@yandex.ru", "элитный ресторан", "8917111222");
    public static final Restaurant restaurant2Today = new Restaurant(RESTAURANT1_ID + 1, "Baikal", "baikal@yandex.ru", "ресторан с хорошим видом", "8917111223");

    public static final Dish dish1 = new Dish(DISH1_ID, LocalDate.of(2020, 1, 30), "Мясо по строгановски", 1000);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, LocalDate.of(2020, 1, 30), "Торт мечта Зайки", 2000);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, LocalDate.of(2020, 1, 30), "Кофе", 200);
    public static final Dish dish4 = new Dish(DISH1_ID + 3, LocalDate.of(2020, 1, 31), "Тёплый салат", 200);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, LocalDate.of(2020, 1, 31), "Борщ", 300);
    public static final Dish dish6 = new Dish(DISH1_ID + 5, LocalDate.now(), "Мясо", 400);
    public static final Dish dish7 = new Dish(DISH1_ID + 6, LocalDate.now(), "Почки", 200);
    public static final Dish dish8 = new Dish(DISH1_ID + 7, LocalDate.now(), "Шашлык", 200);
    public static final Dish dish9 = new Dish(DISH1_ID + 8, LocalDate.now(), "Торт", 300);
    public static final Dish dish10 = new Dish(DISH1_ID + 9, LocalDate.now(), "Суп Харчо", 400);
    public static final Dish dish11 = new Dish(DISH1_ID + 10, LocalDate.now(), "Салат Цезарь", 200);
    public static final Dish dish12 = new Dish(DISH1_ID + 11, LocalDate.now(), "Пицца Милано", 300);
    public static final Dish dish13 = new Dish(DISH1_ID + 12, LocalDate.now(), "Пицца 3 сыра", 400);

    public static final List<RestaurantTo> restaurants = RestaurantUtil.getTos(List.of(restaurant2, restaurant3, restaurant1, restaurant4));
    public static final List<Restaurant> restaurantsToday = List.of(restaurant2Today, restaurant3, restaurant1Today);
    public static final List<Dish> dishes = List.of(dish11, dish12, dish13);

    static {
        restaurant1.setVotes(votesForPushkin);
        restaurant1Today.setDishes(List.of(dish6, dish7));
        restaurant2Today.setDishes(List.of(dish8, dish9, dish10));
        restaurant1.setDishes(List.of(dish1, dish2, dish3, dish6, dish7));
        restaurant2.setDishes(List.of(dish4, dish5, dish8, dish9, dish10));
        restaurant3.setDishes(List.of(dish11, dish12, dish13));
    }

    public static RestaurantTo getNewRestaurant() {
        return new RestaurantTo(null, "Trianon", "trianon@yandex.ru", "ресторан-бар", "8927111444");
    }

    public static Dish getNewDish() {
        return new Dish(null, LocalDate.now(), "Пицца Маргарита", 350);
    }

    public static RestaurantTo getUpdated() {
        return new RestaurantTo(RESTAURANT1_ID, restaurant1.getName(), restaurant1.getEmail(), "ресторан с высокими ценами", "89151149432");
    }

    public static Dish getUpdatedDish() {
        return new Dish(DISH1_ID, LocalDate.now(), "Карамельный торт", 900);
    }
}

