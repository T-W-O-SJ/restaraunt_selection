package com.git.selection.repository;

import com.git.selection.model.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    private final CrudDishRepository crudDishRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.id(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getById(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudDishRepository.delete(id, userId) != 0;
    }

    @Override
    public List<Dish> getAllByDate(LocalDate localDate, int restaurantId) {
        return crudDishRepository.getAllByLocalDateAndId(localDate,restaurantId);
    }

    @Override
    public Dish get(int id, int userId) {
        return crudDishRepository.findById(id)
                .filter(meal -> meal.getRestaurant().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll(int userId) {
        return crudDishRepository.getAll(userId);
    }
}
