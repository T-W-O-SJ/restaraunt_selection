package com.git.selection.repository.datajpa;

import com.git.selection.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface CrudVoteRepository extends JpaRepository<Vote,Integer> {

    @Query("SELECT v FROM Vote v  WHERE  and v.restaurant.id = ?2 and v.user.id = ?3")
    Vote getTodayVote(LocalDate localDate, int restaurantId, int userId);
}


