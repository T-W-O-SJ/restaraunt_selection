package com.git.selection.service;

import com.git.selection.model.Vote;
import com.git.selection.repository.VoteRepository;
import com.git.selection.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.git.selection.util.DateTimeUtil.atStartOfDayOrMin;
import static com.git.selection.util.DateTimeUtil.atStartOfNextDayOrMax;
import static com.git.selection.util.validation.ValidationUtil.checkDateConsistent;

@Service
public class VoteService {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote create(Vote vote) {
        int userId = SecurityUtil.authId();
        log.info("create {} for user {}", vote, userId);
        return checkDateConsistent(voteRepository.save(vote, userId, vote.getRestaurant().id()), LocalDateTime.now());
    }

    public Vote update(Vote vote) {
        int userId = SecurityUtil.authId();
        log.info("update {} for user {}", vote, userId);
        ;
        return checkDateConsistent(voteRepository.save(vote, userId, vote.getRestaurant().id()), LocalDateTime.now());
    }

    public Vote get(int voteId) {
        int userId = SecurityUtil.authId();
        return voteRepository.get(userId, voteId);
    }

    public Vote getTodayVote() {
        int userId = SecurityUtil.authId();
        return voteRepository.getTodayVote(userId);
    }

    public List<Vote> getBetweenDates(@Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        int userId = SecurityUtil.authId();
        return voteRepository.getBetweenDates(userId, atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate));
    }

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }


    public List<Vote> getAllByLocalDate(LocalDate date) {
        return voteRepository.getAllByLocalDate(date);
    }


    public List<Vote> getAllByRestaurantId(int restId) {
        return voteRepository.getAllByRestaurantId(restId);
    }

}
