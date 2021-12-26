package com.git.selection.web.restaurant;

import com.git.selection.model.Dish;
import com.git.selection.repository.datajpa.DataJpaDishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.git.selection.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractJpaMenuController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataJpaDishRepository repository;

   public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "must not be null");
        return repository.save(dish, restaurantId);
    }

   public Dish update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "must not be null");
        return checkNotFoundWithId(repository.save(dish, restaurantId), dish.id());
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

   public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id,restaurantId),id);
    }

   public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public List<Dish> getAllByDate(LocalDate localDate, int restaurantId) {
        return repository.getAllByDate(localDate,restaurantId);
    }
}
