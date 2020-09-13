package com.javaapp.votesystem.web;

import com.javaapp.votesystem.MealTestData;
import com.javaapp.votesystem.RestaurantTestData;
import com.javaapp.votesystem.TestUtil;
import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.service.RestaurantService;
import com.javaapp.votesystem.util.exception.NotFoundException;
import com.javaapp.votesystem.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.javaapp.votesystem.MealTestData.*;
import static com.javaapp.votesystem.RestaurantTestData.*;
import static com.javaapp.votesystem.TestUtil.readFromJson;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest extends AbstractControllerTest {

    public static final String REST_URL = RestaurantController.REST_URL + '/';

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated());

        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);

    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT_ID1));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT_ID1), updated);
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID1))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1));
    }

    @Test
    void getByDate() throws Exception {
        MvcResult mvcResult = perform((MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID1 + "?date=2020-08-20")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1)).andReturn();
        Restaurant restaurant = TestUtil.readFromJsonMvcResult(mvcResult, Restaurant.class);
        MEAL_MATCHER.assertMatch(restaurant.getMeals(), MEAL1, MEAL2, MealTestData.MEAL3);
    }

    @Test
    void getAllWithMenuByDate() throws Exception {
        MvcResult mvcResult = perform((MockMvcRequestBuilders.get(REST_URL + "dishes" + "?date=2020-08-20")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1, RESTAURANT2, RESTAURANT3)).andReturn();
        List<Restaurant> restaurants = TestUtil.readListFromJsonMvcResult(mvcResult, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurants, RESTAURANT1, RESTAURANT2, RESTAURANT3);
        List<Meal> meals = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            meals.addAll(restaurant.getMeals());
        }
        MealTestData.MEAL_MATCHER.assertMatch(meals, MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6);
    }

    @Test
    void getAllWithVotesByDate() throws Exception {
        perform((MockMvcRequestBuilders.get(REST_URL + "votes" + "?date=2020-08-21")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_VOTE_MATCHER.contentJson(RESTAURANT_TO1, RESTAURANT_TO2));
    }
}