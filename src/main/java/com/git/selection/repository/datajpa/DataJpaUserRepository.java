package com.git.selection.repository.datajpa;

import com.git.selection.model.User;
import com.git.selection.model.Vote;
import com.git.selection.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudRepository;
    private final CrudVoteRepository crudVoteRepository;
    private final CrudRestaurantRepository crudRestRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository, CrudVoteRepository crudVoteRepository, CrudRestaurantRepository crudRestRepository) {
        this.crudRepository = crudRepository;
        this.crudVoteRepository = crudVoteRepository;
        this.crudRestRepository = crudRestRepository;
    }


    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public Vote voteForRestaurant(Vote vote, int userId,int restId) {
        if (!vote.isNew() && crudVoteRepository.findById(vote.id()).orElse(null) == null) {
            return null;
        }
        vote.setUser(crudRepository.getById(userId));
        vote.setRestaurant(crudRestRepository.getById(restId));
        return crudVoteRepository.save(vote);

    }

    @Override
    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }

    public interface CrudVoteRepository extends JpaRepository<Vote,Integer> {
    }

}
