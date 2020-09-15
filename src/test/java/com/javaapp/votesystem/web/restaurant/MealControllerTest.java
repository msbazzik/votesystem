package com.javaapp.votesystem.web.restaurant;

import com.javaapp.votesystem.MealTestData;
import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.service.MealService;
import com.javaapp.votesystem.util.exception.NotFoundException;
import com.javaapp.votesystem.web.AbstractControllerTest;
import com.javaapp.votesystem.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javaapp.votesystem.MealTestData.MEAL_ID1;
import static com.javaapp.votesystem.MealTestData.MEAL_MATCHER;
import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT_ID1;
import static com.javaapp.votesystem.TestUtil.readFromJson;
import static com.javaapp.votesystem.TestUtil.userHttpBasic;
import static com.javaapp.votesystem.UserTestData.ADMIN;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MealControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantControllerTest.REST_URL + RESTAURANT_ID1 + "/dishes" + '/';

    @Autowired
    private MealService mealService;

    @Test
    void createWithLocation() throws Exception {
        Meal newMeal = MealTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated());

        Meal created = readFromJson(action, Meal.class);
        int newId = created.id();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(mealService.get(newId, RESTAURANT_ID1), newMeal);

    }

    @Test
    void update() throws Exception {
        Meal updated = MealTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + MEAL_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        MEAL_MATCHER.assertMatch(mealService.get(MEAL_ID1, RESTAURANT_ID1), updated);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MEAL_ID1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID1, RESTAURANT_ID1));
    }
}