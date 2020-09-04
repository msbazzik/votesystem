package com.javaapp.votesystem.service;

import com.javaapp.votesystem.MealTestData;
import com.javaapp.votesystem.model.Meal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.javaapp.votesystem.MealTestData.MEAL_MATCHER;

public class MealServiceTest extends AbstractServiceTest{

    @Autowired
    private MealService service;

    @Test
    public void createMeal() {
        Meal newMeal = MealTestData.getNew();
        Meal created = service.createMeal(newMeal, newMeal.getRestaurant().getId());
        newMeal.setId(created.getId());
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(service.get(created.getId(), created.getRestaurant().getId()), newMeal);
    }

    @Test
    public void updateMeal() {
        Meal meal = MealTestData.getUpdated();
        service.updateMeal(meal, meal.getRestaurant().getId());
        MEAL_MATCHER.assertMatch(service.get(meal.getId(), meal.getRestaurant().getId()), meal);
    }

    @Test
    public void deleteMeal() {
    }

    @Test
    public void get() {
    }
}