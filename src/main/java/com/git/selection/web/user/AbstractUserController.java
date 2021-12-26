package com.git.selection.web.user;

import com.git.selection.model.AbstractBaseEntity;
import com.git.selection.model.User;
import com.git.selection.model.Vote;
import com.git.selection.repository.datajpa.DataJpaUserRepository;
import com.git.selection.repository.datajpa.DataJpaVoteRepository;
import com.git.selection.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.git.selection.util.DateTimeUtil.atStartOfDayOrMin;
import static com.git.selection.util.DateTimeUtil.atStartOfNextDayOrMax;
import static com.git.selection.util.ValidationUtil.*;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataJpaUserRepository repository;

    @Autowired
    private DataJpaVoteRepository voteRepository;


    @Cacheable("users")
    public List<User> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        Assert.notNull(user, "not found");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        Assert.notNull(user, "not found");
        repository.save(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return repository.getByEmail(email);
    }

    public User getWithVotes(int id) {
        log.info("getWithVotes");
        return checkNotFoundWithId(repository.getWithVotes(id), id);
    }

    public Vote create(Vote vote, int restId, LocalDateTime dateTime) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} for user {}", vote, userId);
        return checkDateConsistent(voteRepository.save(vote, userId, restId), dateTime);
    }

    public Vote update(Vote vote, int restId, LocalDateTime dateTime) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} for user {}", vote, userId);
        ;
        return checkDateConsistent(voteRepository.save(vote, userId, restId), dateTime);
    }

    public Vote get(int restId, int voteId) {
        int userId = SecurityUtil.authUserId();
        return voteRepository.get(userId, restId, voteId);
    }


    public List<Vote> getBetweenDates(int restId, @Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        return voteRepository.getBetweenDates(userId, restId, atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate));
    }

    public List<Vote> getAllVotes() {
        return voteRepository.getAll();
    }


    public List<Vote> getAllByLocalDate(LocalDate date) {
        return voteRepository.getAllByLocalDate(date);
    }


    public List<Vote> getAllByRestaurantId(int restId) {
        return voteRepository.getAllByRestaurantId(restId);
    }

    }
