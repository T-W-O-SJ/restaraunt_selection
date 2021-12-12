package com.git.selection.web.user;

import com.git.selection.model.User;
import com.git.selection.model.Vote;
import com.git.selection.repository.datajpa.DataJpaUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

import static com.git.selection.util.ValidationUtil.*;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataJpaUserRepository repository;


    public List<User> getAll() {
        log.info("getAll");
        return  repository.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id),id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        Assert.notNull(user,"not found");
        return repository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id),id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        Assert.notNull(user,"not found");
        repository.save(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return repository.getByEmail(email);
    }

}