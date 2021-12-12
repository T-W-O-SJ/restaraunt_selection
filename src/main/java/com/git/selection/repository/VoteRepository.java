package com.git.selection.repository;

import com.git.selection.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    Vote voteForRestaurant (Vote vote, int userId, int restId);

    Vote getVote (int userId , int restId, LocalDateTime dateTime);

    List<Vote> votes (int restId);
}
