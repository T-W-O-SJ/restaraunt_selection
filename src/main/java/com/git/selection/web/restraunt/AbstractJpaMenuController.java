package com.git.selection.web.restraunt;

import com.git.selection.model.Dish;
import com.git.selection.repository.datajpa.DataJpaDishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractJpaMenuController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataJpaDishRepository dishRepository;

    Dish save(Dish dish, int restaurantId){
        return null;
    }

    // false if not found
    boolean delete(int id, int restaurantId){
        return  false;
    }

    // null if not found
    Dish get(int id,int restaurantId){
        return  null;
    }

    List<Dish> getAll(int restaurantId){
        return null;
    }
    List<Dish> getAllByDate(int restaurantId){
        return null;
    }
}
