package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Meal;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT1;
import static com.javaapp.votesystem.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID1 = START_SEQ + 17;
    public static final int MEAL_ID2 = START_SEQ + 18;
    public static final int MEAL_ID3 = START_SEQ + 19;

    public static final Meal meal1 = new Meal(MEAL_ID1, "meal1", LocalDate.of(2020, Month.JANUARY, 30),
            10, RESTAURANT1);
    public static final Meal meal2 = new Meal(MEAL_ID2, "meal2", LocalDate.of(2020, Month.JANUARY, 30),
            11, RESTAURANT1);
    public static final Meal meal3 = new Meal(MEAL_ID3, "meal3", LocalDate.of(2020, Month.JANUARY, 31),
            12, RESTAURANT1);

    public static final List<Meal> meals = Arrays.asList(meal1, meal2, meal3);
}
