package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Meal;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.javaapp.votesystem.RestaurantTestData.restaurant1;

public class MealTestData {

    public static final Meal meal1 = new Meal(10, "meal1", LocalDate.of(2020, Month.JANUARY, 30), 10, restaurant1);
    public static final Meal meal2 = new Meal(11, "meal2", LocalDate.of(2020, Month.JANUARY, 30), 11, restaurant1);
    public static final Meal meal3 = new Meal(12, "meal3", LocalDate.of(2020, Month.JANUARY, 31), 12, restaurant1);

    public static final List<Meal> meals = Arrays.asList(meal1, meal2, meal3);
}
