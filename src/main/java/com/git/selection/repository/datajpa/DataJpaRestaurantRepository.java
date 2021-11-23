package com.git.selection.repository.datajpa;

import com.git.selection.model.Restaurant;
import com.git.selection.repository.RestaurantRepository;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DataJpaRestaurantRepository implements RestaurantRepository{
   private static final Sort SORT_BY_NAME = Sort.by("name");
    CrudRestaurantRepository repository;

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return repository.delete(id)!=0;
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Restaurant getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll(SORT_BY_NAME);
    }
}
