package com.git.selection.repository;

import com.git.selection.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudRepository;
    private final CrudRestaurantRepository crudRestRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository, CrudRestaurantRepository crudRestRepository) {
        this.crudRepository = crudRepository;
        this.crudRestRepository = crudRestRepository;
    }

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    public void deleteExisted(int id) {
    crudRepository.deleteExisted(id);
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return crudRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User getWithVotes(int id) {
        return crudRepository.getWithVotes(id);
    }

}
