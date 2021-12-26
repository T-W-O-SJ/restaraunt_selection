package com.git.selection.repository.datajpa;

import com.git.selection.model.Dish;
import com.git.selection.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CrudVoteRepository extends JpaRepository<Vote,Integer> {

    @Query("SELECT v FROM Vote v  WHERE v.user.id = ?1 and v.restaurant.id = ?2 and v.id =?3")
    Vote getVote (int userId , int restId, int voteId);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId")
    List<Vote> getAllVotesForRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.localDate=:date")
    List<Vote> getAllByLocalDate(@Param("date") LocalDate date);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId AND v.restaurant.id=:restaurantId " +
            "AND v.localDate >= :startDate AND v.localDate < :endDate ORDER BY v.localDate DESC")
    List <Vote> getBetweenDates(@Param("userId")int userid,
                                @Param("restaurantId") int restaurantId,
                                @Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate);

}


