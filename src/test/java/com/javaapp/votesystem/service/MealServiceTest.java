package com.javaapp.votesystem.service;

import com.javaapp.votesystem.MealTestData;
import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

import static com.javaapp.votesystem.MealTestData.*;
import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT1;
import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT_ID1;
import static com.javaapp.votesystem.UserTestData.NOT_FOUND;
import static org.junit.Assert.assertThrows;

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void createMeal() {
        Meal newMeal = MealTestData.getNew();
        Meal created = service.createMeal(newMeal, RESTAURANT_ID1);
        Integer newId = created.getId();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(service.get(created.getId(), created.getRestaurant().getId()), newMeal);
    }

    @Test
    public void duplicateNameDateIdCreate() {
        Meal meal = new Meal(null, "meal1_1", LocalDate.of(2020, Month.AUGUST, 20),
                50);
        meal.setRestaurant(RESTAURANT1);
        assertThrows(DataAccessException.class, () -> service.createMeal(meal, RESTAURANT_ID1));
    }

    @Test
    public void updateMeal() {
        Meal meal = MealTestData.getUpdated();
        service.updateMeal(meal, meal.getRestaurant().getId());
        MEAL_MATCHER.assertMatch(service.get(meal.getId(), meal.getRestaurant().getId()), meal);
    }

    @Test
    public void deleteMeal() {
        service.deleteMeal(MEAL_ID1, RESTAURANT_ID1);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID1, RESTAURANT_ID1));
    }

    @Test
    public void deletedMealNotFound() {
        assertThrows(NotFoundException.class, () -> service.deleteMeal(NOT_FOUND, RESTAURANT_ID1));
    }

    @Test
    public void deletedMealNotOwn() {
        assertThrows(NotFoundException.class, () -> service.deleteMeal(MEAL_ID1, NOT_FOUND));
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID1, RESTAURANT_ID1);
        MEAL_MATCHER.assertMatch(meal, MEAL1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, RESTAURANT_ID1));
    }

    @Test
    public void getNotFoundByRestaurantId() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID1, NOT_FOUND));
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(() -> service.createMeal(new Meal(null, "  ", LocalDate.of(2020, Month.AUGUST, 20), 1), RESTAURANT_ID1), ConstraintViolationException.class);
        validateRootCause(() -> service.createMeal(new Meal(null, "Meal1", null, 1), RESTAURANT_ID1), ConstraintViolationException.class);
        validateRootCause(() -> service.createMeal(new Meal(null, "Meal1", LocalDate.of(2020, Month.AUGUST, 20), 0), RESTAURANT_ID1), ConstraintViolationException.class);
    }
}