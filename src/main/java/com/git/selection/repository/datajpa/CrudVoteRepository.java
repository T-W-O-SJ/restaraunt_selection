package com.git.selection.repository.datajpa;

import com.git.selection.model.Dish;
import com.git.selection.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CrudVoteRepository extends JpaRepository<Vote,Integer> {

    @Query("SELECT v FROM Vote v  WHERE v.user.id = ?1 and v.id =?2")
    Vote getVote ( int userId ,int voteId);

    @Query("SELECT v FROM Vote v  WHERE v.user.id = ?1 and v.localDate =?2")
    Vote getVoteByDate (int userId , LocalDate localDate);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId")
    List<Vote> getAllVotesForRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.localDate=:date")
    List<Vote> getAllByLocalDate(@Param("date") LocalDate date);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId" +
            "AND v.localDate >= :startDate AND v.localDate < :endDate ORDER BY v.localDate DESC")
    List <Vote> getBetweenDates(@Param("userId")int userid,
                                @Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate);

}


