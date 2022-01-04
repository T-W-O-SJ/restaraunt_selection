package com.git.selection.repository;

import com.git.selection.model.Dish;
import com.git.selection.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface VoteRepository extends BaseRepository<Vote>{

    @Query("SELECT v FROM Vote v  WHERE v.user.id = ?1 and v.id =?2")
    Optional<Vote> get(int userId , int voteId);

    @Query("SELECT v FROM Vote v  WHERE v.user.id = ?1 and v.localDate =?2")
    Optional<Vote> getByDate(int userId , LocalDate localDate);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId")
    List<Vote> getAllForRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.localDate=:date")
    List<Vote> getAllByLocalDate(@Param("date") LocalDate date);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId AND v.localDate >= :startDate AND v.localDate < :endDate ORDER BY v.localDate DESC")
    List <Vote> getBetweenDates(@Param("userId")int userid,
                                @Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate);

}


