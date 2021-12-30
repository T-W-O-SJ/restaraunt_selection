package com.git.selection.repository;

import com.git.selection.model.User;
import com.git.selection.model.Vote;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    List<User> getAll();

   User getWithVotes(int id) ;

    Optional<User> findByEmailIgnoreCase(String email);
}