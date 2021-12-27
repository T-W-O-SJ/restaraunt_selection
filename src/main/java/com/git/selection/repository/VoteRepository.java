package com.git.selection.repository;

import com.git.selection.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote, int userId, int restId);

    Vote get(int userId,int voteId);

    Vote getTodayVote(int userId);

    List<Vote> getAll();

    List<Vote> getAllByLocalDate(LocalDate date);

    List<Vote> getAllByRestaurantId(int restId);

    List<Vote> getBetweenDates(int userid, LocalDate startDate, LocalDate endDate);

}
