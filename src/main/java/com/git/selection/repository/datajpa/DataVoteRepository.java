package com.git.selection.repository.datajpa;

import com.git.selection.model.Vote;
import com.git.selection.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DataVoteRepository implements VoteRepository {
    CrudVoteRepository voteRepository;

    @Override
    public Vote voteForRestaurant(Vote vote, int userId, int restId) {
        return null;
    }

    @Override
    public Vote getVote(int userId, int restId, LocalDateTime dateTime) {
        return null;
    }

    @Override
    public List<Vote> votes(int restId) {
        return null;
    }

}
