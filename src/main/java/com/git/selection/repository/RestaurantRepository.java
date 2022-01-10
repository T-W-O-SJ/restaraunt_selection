package com.git.selection.repository;

import com.git.selection.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE r.id=:id AND d.localDate= current_date")
    Optional<Restaurant> getWithDishes(int id);

    @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> get(int id);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r JOIN  r.dishes d WHERE d.localDate= current_date")
    List<Restaurant> getAllWithDishesToday();

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r  WHERE r.id=?1")
    Optional<Restaurant> getWithVotes(int id);
}
