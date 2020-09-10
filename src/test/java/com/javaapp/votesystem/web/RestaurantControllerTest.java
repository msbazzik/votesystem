package com.javaapp.votesystem.web;

import com.javaapp.votesystem.UserTestData;
import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javaapp.votesystem.TestUtil.readFromJson;
import static com.javaapp.votesystem.UserTestData.USER_MATCHER;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest extends AbstractControllerTest{

    @Test
    void createWithLocation() {
        User newUser = UserTestData.getNew();
//        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(newUser)))
//                .andExpect(status().isCreated());
//
//        User created = readFromJson(action, User.class);
//        int newId = created.id();
//        newUser.setId(newId);
//        USER_MATCHER.assertMatch(created, newUser);
//        USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void get() {
    }

    @Test
    void getByDate() {
    }

    @Test
    void getAllWithMenuByDate() {
    }

    @Test
    void getAllWithVotesByDate() {
    }

    @Test
    void createMealWithLocation() {
    }

    @Test
    void updateMeal() {
    }

    @Test
    void deleteMeal() {
    }
}