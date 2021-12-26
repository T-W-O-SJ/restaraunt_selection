package com.git.selection.repository;

import com.git.selection.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    Vote save (Vote vote, int userId, int restId, LocalDateTime dateTime);

    Vote get (int userId , int restId, int voteId);

    List<Vote> getAll ();

    List<Vote> getAllByLocalDate(LocalDate date);

    List <Vote> getAllByRestaurantId(int restId);

    List <Vote> getBetweenDates(int userid, int restId, LocalDate startDate,LocalDate endDate);

}
