package com.github.twosj.selection.util;

import com.github.twosj.selection.model.Vote;
import com.github.twosj.selection.to.VoteTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class VoteUtil {

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::createTo)
                .collect(Collectors.toList());
    }

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getLocalDate(), vote.getRestaurant().getId());
    }
}
