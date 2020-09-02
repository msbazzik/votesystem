package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.javaapp.votesystem.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

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
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getWithVotes() {
    }

    @Test
    public void enable() {
    }

//    @Test
//    void getWithMeals() throws Exception {
//        User admin = service.getWithMeals(ADMIN_ID);
//        USER_MATCHERS.assertMatch(admin, ADMIN);
//        MealTestData.MEAL_MATCHERS.assertMatch(admin.getMeals(), MealTestData.ADMIN_MEAL2, MealTestData.ADMIN_MEAL1);
//    }
//
//    @Test
//    void getWithMealsNotFound() throws Exception {
//        assertThrows(NotFoundException.class, () ->
//                service.getWithMeals(1));
//    }
}