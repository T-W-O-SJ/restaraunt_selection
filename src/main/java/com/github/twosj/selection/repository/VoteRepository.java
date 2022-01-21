package com.github.twosj.selection.repository;

import com.github.twosj.selection.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v  WHERE v.user.id =:userId and v.localDate =:localDate")
    Optional<Vote> getByDate(int userId, LocalDate localDate);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId")
    List<Vote> getAllForRestaurant(int restaurantId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant")
    List<Vote> getAllHistory();

    @Query("SELECT v FROM Vote v WHERE v.localDate=:localDate")
    List<Vote> getAllByLocalDate(LocalDate localDate);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId AND v.localDate >= :startDate AND v.localDate < :endDate ORDER BY v.localDate DESC")
    List<Vote> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId);


}


