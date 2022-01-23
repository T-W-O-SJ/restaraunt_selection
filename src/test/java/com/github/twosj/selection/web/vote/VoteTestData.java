package com.github.twosj.selection.web.vote;

import com.github.twosj.selection.model.Vote;
import com.github.twosj.selection.to.VoteTo;
import com.github.twosj.selection.web.MatcherFactory;
import com.github.twosj.selection.web.restaurant.RestaurantTestData;

import java.time.LocalDate;
import java.util.List;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class);

    public static final int VOTE_ID = 1;
    public static final VoteTo voteTo1 = new VoteTo(VOTE_ID, LocalDate.of(2020, 1, 31), 1);
    public static final VoteTo voteTo2 = new VoteTo(VOTE_ID + 1, LocalDate.of(2020, 1, 31), 1);
    public static final VoteTo voteTo3 = new VoteTo(VOTE_ID + 2, LocalDate.now(), 1);
    public static final VoteTo voteTo4 = new VoteTo(VOTE_ID + 3, LocalDate.now(), 1);
    public static final VoteTo voteTo5 = new VoteTo(VOTE_ID + 4, LocalDate.now(), 3);
    public static final Vote vote1 = new Vote(VOTE_ID, LocalDate.of(2020, 1, 31));
    public static final Vote vote2 = new Vote(VOTE_ID + 1, LocalDate.of(2020, 1, 31));
    public static final Vote vote3 = new Vote(VOTE_ID + 2, LocalDate.now());
    public static final Vote vote4 = new Vote(VOTE_ID + 3, LocalDate.now());
    public static final Vote vote5 = new Vote(VOTE_ID + 4, LocalDate.now());
    public static final Vote newVote = new Vote(VOTE_ID + 5, LocalDate.now());
    public static final Vote updateVote = new Vote(VOTE_ID + 2, LocalDate.now(), RestaurantTestData.restaurant2);

    public static final List<VoteTo> votesTo = List.of(voteTo1, voteTo2, voteTo3, voteTo4, voteTo5);
    public static final List<VoteTo> votesToByDate = List.of(voteTo1, voteTo2);
    public static final List<VoteTo> votesToForPushkin = List.of(voteTo1, voteTo2, voteTo3, voteTo4);
    public static final List<Vote> votesForPushkin = List.of(vote3, vote4, vote1, vote2);
    public static final List<Vote> votesOfUser = List.of(vote1, vote3);
    public static final List<Vote> votesOfAdmin = List.of(vote2);

}
