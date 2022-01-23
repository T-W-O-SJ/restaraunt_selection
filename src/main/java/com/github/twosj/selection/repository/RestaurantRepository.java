package com.github.twosj.selection.repository;

import com.github.twosj.selection.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE r.id=:id AND d.localDate= current_date ORDER BY r.name ASC , r.id ASC")
    Optional<Restaurant> getWithDishesToday(int id);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r JOIN  r.dishes d WHERE d.localDate= current_date ORDER BY r.name ASC , r.id ASC")
    List<Restaurant> getAllWithDishesToday();

}
