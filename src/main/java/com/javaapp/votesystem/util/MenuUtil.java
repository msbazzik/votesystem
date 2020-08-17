package com.javaapp.votesystem.util;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.to.MenuTo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MenuUtil {
    public static void main(String[] args) {
        Restaurant restaurant1 = new Restaurant(1, "Restaurant_01");

        Meal meal1 = new Meal(10, "meal1", LocalDate.of(2020, Month.JANUARY, 30), 10, restaurant1);
        Meal meal2 = new Meal(11, "meal2", LocalDate.of(2020, Month.JANUARY, 30), 11, restaurant1);
        Meal meal3 = new Meal(12, "meal3", LocalDate.of(2020, Month.JANUARY, 31), 12, restaurant1);

        List<Meal> meals = Arrays.asList(meal1, meal2, meal3);

//        MenuTo menu = getMenu(meals, restaurant1, LocalDate.of(2020, Month.JANUARY, 30));
//        System.out.println(menu.getMeals());
    }

//    public static MenuTo getMenu(List<Meal> meals, Restaurant restaurant, LocalDate date) {
//        List<Meal> menu = meals.stream()
//                .filter(meal -> date.isEqual(meal.getDate()))
//                .collect(Collectors.toList());
//        return new MenuTo(date, restaurant, menu);
//    }
}
