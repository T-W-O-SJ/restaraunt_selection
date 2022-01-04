package com.git.selection.repository;

import com.git.selection.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @EntityGraph(attributePaths = {"restaurants", "dishes"})
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE r.id=?1 AND d.localDate=: current_date")
    Restaurant getWithDishes(int restaurantId);

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithVotes(int id);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r JOIN FETCH Dish  d WHERE d.localDate=: current_date")
    List<Restaurant> getAllWithDishes();
}
