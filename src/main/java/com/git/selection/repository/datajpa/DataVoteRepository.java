package com.git.selection.repository.datajpa;

import com.git.selection.model.Vote;
import com.git.selection.repository.VoteRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class DataVoteRepository implements VoteRepository {

    CrudVoteRepository voteRepository;
    CrudUserRepository userRepository;
    CrudRestaurantRepository restaurantRepository;

    public DataVoteRepository(CrudVoteRepository voteRepository, CrudUserRepository userRepository, CrudRestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }
@Transactional
    @Override
    public Vote save(Vote vote, int userId, int restId, LocalDateTime dateTime) {
        if (!vote.isNew() && get(vote.id(),userId,restId) == null) {
            return null;
        }
        vote.setUser(userRepository.getById(userId));
        vote.setRestaurant(restaurantRepository.getById(restId));
        return voteRepository.save(vote);
    }

    @Override
    public Vote get(int userId, int restId, int voteId) {
        return voteRepository.getVote(userId,restId,voteId);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @Override
    public List<Vote> getAllByLocalDate(LocalDate date) {
        return voteRepository.getAllByLocalDate(date);
    }

    @Override
    public List<Vote> getAllByRestaurantId(int restId) {
        return voteRepository.getAllVotesForRestaurant(restId);
    }
}
