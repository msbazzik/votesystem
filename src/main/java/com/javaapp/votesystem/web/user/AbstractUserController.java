package com.javaapp.votesystem.web.user;

import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.service.UserService;
import com.javaapp.votesystem.web.RestaurantController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.javaapp.votesystem.util.ValidationUtil.assureIdConsistent;
import static com.javaapp.votesystem.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private UserService userService;

    public List<User> getAll() {
        LOG.info("getAll");
        return userService.getAll();
    }

    public User get(int id) {
        LOG.info("get {}", id);
        return userService.get(id);
    }

    //    public User create(UserTo userTo) {
//        log.info("create from to {}", userTo);
//        return create(UserUtil.createNewFromTo(userTo));
//    }
//
    public User create(User user) {
        LOG.info("create {}", user);
        checkNew(user);
        return userService.create(user);
    }

    public void delete(int id) {
        LOG.info("delete {}", id);
        userService.delete(id);
    }

    public void update(User user, int id) {
        LOG.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        userService.update(user);
    }

    //    public void update(UserTo userTo, int id) {
//        log.info("update {} with id={}", userTo, id);
//        assureIdConsistent(userTo, id);
//        service.update(userTo);
//    }
//
    public User getByMail(String email) {
        LOG.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        LOG.info(enabled ? "enable {}" : "disable {}", id);
        userService.enable(id, enabled);
    }
}