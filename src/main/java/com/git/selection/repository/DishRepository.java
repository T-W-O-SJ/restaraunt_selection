package com.git.selection.repository;

import com.git.selection.error.DataConflictException;
import com.git.selection.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Transactional
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.id = ?1 and d.restaurant.id = ?2")
    Optional<Dish>getWithRestaurant(int id, int restaurantId);

    List<Dish> getAllByLocalDateAndId(LocalDate localdate, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id = :id and d.restaurant.id = :restaurantId")
    Optional<Dish> get(int id, int restaurantId);

    default Dish checkBelong(int id, int restaurantId) {
        return get(id, restaurantId).orElseThrow(
                () -> new DataConflictException("Meal id=" + id + " doesn't belong to User id=" + restaurantId));
    }
}