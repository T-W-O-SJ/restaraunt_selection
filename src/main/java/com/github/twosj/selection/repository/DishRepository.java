package com.github.twosj.selection.repository;

import com.github.twosj.selection.error.DataConflictException;
import com.github.twosj.selection.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DishRepository extends BaseRepository<Dish> {


    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.localDate =current_date")
    List<Dish> getAllByRestaurantToday(int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id = :id and d.restaurant.id = :restaurantId")
    Optional<Dish> get(int id, int restaurantId);

    default Dish checkBelong(int id, int restaurantId) {
        return get(id, restaurantId).orElseThrow(
                () -> new DataConflictException("Dish id=" + id + " doesn't belong to Restaurant id=" + restaurantId));
    }
}