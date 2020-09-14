package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.repository.UserRepository;
import com.javaapp.votesystem.to.UserTo;
import com.javaapp.votesystem.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.javaapp.votesystem.util.ValidationUtil.checkNotFound;
import static com.javaapp.votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
//      checkNotFoundWithId : check works only for JDBC, disabled
        userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        User updatedUser = UserUtil.updateFromTo(user, userTo);
        userRepository.save(updatedUser);   // !! need only for JDBC implementation
    }


    public User getWithVotes(int id) {
        return checkNotFoundWithId(userRepository.getWithVotes(id), id);
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

//    @Override
//    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = repository.getByEmail(email.toLowerCase());
//        if (user == null) {
//            throw new UsernameNotFoundException("User " + email + " is not found");
//        }
//        return new AuthorizedUser(user);
//    }
//
//    private User prepareAndSave(User user) {
//        return repository.save(prepareToSave(user, passwordEncoder));
//    }
}