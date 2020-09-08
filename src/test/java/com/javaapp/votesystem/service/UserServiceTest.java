package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.Role;
import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.javaapp.votesystem.UserTestData.*;
import static org.junit.Assert.assertThrows;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() {
        User newUser = getNew();
        User created = service.create(new User(newUser));
        Integer newId = created.getId();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () -> service.create(new User(null, "Duplicate", "user1@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void delete() {
        service.delete(USER_ID1);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID1));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        User user = service.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID1), getUpdated());
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, ADMIN, USER_1, USER_2, USER_3);
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(() -> service.create(new User(null, "", "mail@yandex.ru", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)), ConstraintViolationException.class);
    }

//    @Test
//    public void getWithVotes() {
//        User admin = service.getWithVotes(ADMIN_ID);
//        USER_MATCHER.assertMatch(admin, ADMIN);
//        VoteTestData.MEAL_MATCHER.assertMatch(admin.getMeals(), MealTestData.ADMIN_MEAL2, MealTestData.ADMIN_MEAL1);
//    }

//    @Test
//    public void getWithVotesNotFound() throws Exception {
//        assertThrows(NotFoundException.class, () ->
//                service.getWithMeals(1));
//    }
}