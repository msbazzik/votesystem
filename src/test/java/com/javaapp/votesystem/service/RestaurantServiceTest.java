package com.javaapp.votesystem.service;

import com.javaapp.votesystem.MealTestData;
import com.javaapp.votesystem.RestaurantTestData;
import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.javaapp.votesystem.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void create() {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        Restaurant created = service.create(newRestaurant);
        Integer newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    void delete() {
        service.delete(RESTAURANT_ID1);
        assertThrows(NotFoundException.class, () -> service.delete(RESTAURANT_ID1));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }


    @Test
    void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT_ID1), getUpdated());
    }

    @Test
    void get() {
        Restaurant restaurant = service.get(RESTAURANT_ID1);
        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getByDate() {
        Restaurant restaurant = service.getByDate(RESTAURANT_ID1, LocalDate.of(2020, Month.AUGUST, 20));
        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT1);
        MealTestData.MEAL_MATCHER.assertMatch(restaurant.getMeals(), MealTestData.MEAL1, MealTestData.MEAL2, MealTestData.MEAL3);
    }

    @Test
    void getNotFoundByRestaurantId() {
        assertThrows(NotFoundException.class, () -> service.getByDate(NOT_FOUND, LocalDate.of(2020, Month.AUGUST, 20)));
    }

    @Test
    void getNotFoundByDate() {
        assertThrows(NotFoundException.class, () -> service.getByDate(RESTAURANT_ID1, LocalDate.of(2020, Month.AUGUST, 19)));
    }

    @Test
    void getAllByDate() {
        List<Restaurant> all = service.getAllByDate(LocalDate.of(2020, Month.AUGUST, 20));
        RESTAURANT_MATCHER.assertMatch(all, RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    void createWithException() {
        validateRootCause(() -> service.create(new Restaurant(null, " ")), ConstraintViolationException.class);
    }
}